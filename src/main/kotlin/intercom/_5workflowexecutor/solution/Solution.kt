package intercom._5workflowexecutor.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.note
import intercom.Harness.scenario
import intercom._5workflowexecutor.solution.StepStatus.FAILED
import intercom._5workflowexecutor.solution.StepStatus.NOT_EXECUTED
import intercom._5workflowexecutor.solution.StepStatus.SUCCESS
import java.util.UUID

/*
 * Problem 5 – Multi-step workflow executor (e.g. Fin "Procedures": refund a customer).
 *
 *  1. Validate (unique ids, known dependencies) and compute a topological order with Kahn's algorithm.
 *     Fewer ordered steps than input steps => cycle => reject before running anything.
 *  2. Run steps in that order. A step runs only if ALL its dependencies succeeded, otherwise NOT_EXECUTED.
 *     Independent branches keep running after a failure.
 *  3. TransientStepException => retry up to maxAttempts. Any other exception => FAILED immediately.
 *  4. Every attempt of a step gets the SAME idempotency key so side effects (refunds!) happen once.
 */

enum class StepStatus { PENDING, SUCCESS, FAILED, NOT_EXECUTED }

/** Throw from a step action for failures worth retrying (timeouts, 503s, rate limits). */
class TransientStepException(message: String) : RuntimeException(message)

class StepContext(val workflowRunId: String, val stepId: String, val attempt: Int) {
    /** Stable across retries of the same step in the same run. */
    val idempotencyKey: String get() = "$workflowRunId:$stepId"
}

data class Step(
    val id: String,
    val dependsOn: List<String> = emptyList(),
    val maxAttempts: Int = 1,
    val action: (StepContext) -> Unit = {},
) {
    init {
        require(maxAttempts >= 1) { "maxAttempts must be >= 1" }
    }
}

data class WorkflowResult(
    /** In the order the steps were given. */
    val statuses: Map<String, StepStatus>,
    val attempts: Map<String, Int>,
    val errors: Map<String, String>,
    /** Steps that were actually attempted, in execution order. */
    val executionOrder: List<String>,
) {
    val succeeded: Boolean get() = statuses.values.all { it == SUCCESS }
}

/**
 * - Validation + topological sort: O(V + E)
 * - Execution: O(V + E) bookkeeping + the cost of the actions × attempts
 */
class WorkflowExecutor(private val newRunId: () -> String = { UUID.randomUUID().toString() }) {

    /** The API asked for in the interview. Throws IllegalArgumentException for an invalid workflow. */
    fun execute(steps: List<Step>): Boolean = run(steps).succeeded

    fun run(steps: List<Step>, workflowRunId: String = newRunId()): WorkflowResult {
        val order = topologicalOrder(steps)
        val byId = steps.associateBy { it.id }
        val statuses = LinkedHashMap<String, StepStatus>().apply { steps.forEach { put(it.id, StepStatus.PENDING) } }
        val attempts = LinkedHashMap<String, Int>()
        val errors = LinkedHashMap<String, String>()
        val executed = mutableListOf<String>()

        for (id in order) {
            val step = byId.getValue(id)
            if (step.dependsOn.any { statuses[it] != SUCCESS }) {
                statuses[id] = NOT_EXECUTED
                continue
            }
            executed += id
            statuses[id] = runWithRetries(step, workflowRunId, attempts, errors)
        }
        return WorkflowResult(statuses, attempts, errors, executed)
    }

    private fun runWithRetries(
        step: Step,
        workflowRunId: String,
        attempts: MutableMap<String, Int>,
        errors: MutableMap<String, String>,
    ): StepStatus {
        for (attempt in 1..step.maxAttempts) {
            attempts[step.id] = attempt
            try {
                step.action(StepContext(workflowRunId, step.id, attempt))
                errors.remove(step.id)
                return SUCCESS
            } catch (e: TransientStepException) {
                errors[step.id] = "transient: ${e.message}"
                // In production: sleep(backoff(attempt)) with exponential backoff + jitter.
            } catch (e: Exception) {
                errors[step.id] = e.message ?: e::class.simpleName.orEmpty()
                return FAILED
            }
        }
        return FAILED
    }

    companion object {
        /** Kahn's algorithm. Ties are broken by input order, so the result is deterministic. */
        fun topologicalOrder(steps: List<Step>): List<String> {
            val ids = steps.map { it.id }
            val idSet = ids.toSet()
            require(idSet.size == ids.size) { "Duplicate step ids: ${ids.groupingBy { it }.eachCount().filterValues { it > 1 }.keys}" }

            val inDegree = HashMap<String, Int>()
            val dependents = HashMap<String, MutableList<String>>()
            for (step in steps) {
                val deps = step.dependsOn.distinct()
                deps.forEach { require(it in idSet) { "Step '${step.id}' depends on unknown step '$it'" } }
                inDegree[step.id] = deps.size
                deps.forEach { dependents.getOrPut(it) { mutableListOf() } += step.id }
            }

            val ready = ArrayDeque(ids.filter { inDegree.getValue(it) == 0 })
            val order = ArrayList<String>(ids.size)
            while (ready.isNotEmpty()) {
                val id = ready.removeFirst()
                order += id
                for (next in dependents[id].orEmpty()) {
                    val remaining = inDegree.getValue(next) - 1
                    inDegree[next] = remaining
                    if (remaining == 0) ready.addLast(next)
                }
            }
            require(order.size == ids.size) { "Cycle detected among steps: ${ids.filter { it !in order.toSet() }}" }
            return order
        }
    }
}

/** Fake payment provider that dedupes by idempotency key, like Stripe does. */
class FakeRefundGateway(private var timeoutsAfterProcessing: Int = 0) {
    private val refundsByKey = LinkedHashMap<String, String>()

    val refundCount: Int get() = refundsByKey.size

    fun refund(orderId: String, idempotencyKey: String): String {
        val refundId = refundsByKey.getOrPut(idempotencyKey) { "re_${orderId}_${refundsByKey.size + 1}" }
        if (timeoutsAfterProcessing > 0) {
            timeoutsAfterProcessing--
            // The money moved, but our HTTP call timed out – the dangerous case for double refunds.
            throw TransientStepException("gateway timeout")
        }
        return refundId
    }
}

private fun refundWorkflow(gateway: FakeRefundGateway, log: MutableList<String>, eligible: Boolean = true) = listOf(
    Step("retrieve_customer") { log += "retrieve_customer" },
    Step("retrieve_order", listOf("retrieve_customer")) { log += "retrieve_order" },
    Step("check_eligibility", listOf("retrieve_order")) {
        log += "check_eligibility"
        check(eligible) { "Order is older than 30 days" }
    },
    Step("request_refund", listOf("check_eligibility"), maxAttempts = 3) { ctx ->
        log += "request_refund#${ctx.attempt} -> ${gateway.refund("o42", ctx.idempotencyKey)}"
    },
    Step("notify_customer", listOf("request_refund")) { log += "notify_customer" },
    Step("resolve_conversation", listOf("notify_customer")) { log += "resolve_conversation" },
)

fun main() {
    scenario("Happy path – refund flow") {
        val gateway = FakeRefundGateway()
        val log = mutableListOf<String>()
        val result = WorkflowExecutor { "run-1" }.run(refundWorkflow(gateway, log))
        expect("succeeded", result.succeeded, true)
        expect(
            "execution order", result.executionOrder,
            listOf("retrieve_customer", "retrieve_order", "check_eligibility", "request_refund", "notify_customer", "resolve_conversation"),
        )
        expect("refunds issued", gateway.refundCount, 1)
    }

    scenario("Example – refund fails, dependents are not executed") {
        val steps = listOf(
            Step("retrieve_order"),
            Step("check_eligibility", listOf("retrieve_order")),
            Step("request_refund", listOf("check_eligibility")) { error("Payment provider declined the refund") },
            Step("notify_customer", listOf("request_refund")),
        )
        val result = WorkflowExecutor().run(steps)
        result.statuses.forEach { (id, status) -> note("$id: $status") }
        expect(
            "statuses", result.statuses,
            mapOf("retrieve_order" to SUCCESS, "check_eligibility" to SUCCESS, "request_refund" to FAILED, "notify_customer" to NOT_EXECUTED),
        )
        expect("execute() returns", WorkflowExecutor().execute(steps), false)
        expect("error recorded", result.errors["request_refund"], "Payment provider declined the refund")
    }

    scenario("Transient failure is retried with the same idempotency key – no double refund") {
        val gateway = FakeRefundGateway(timeoutsAfterProcessing = 1)
        val log = mutableListOf<String>()
        val result = WorkflowExecutor { "run-7" }.run(refundWorkflow(gateway, log))
        expect("succeeded", result.succeeded, true)
        expect("request_refund attempts", result.attempts["request_refund"], 2)
        expect("refunds issued", gateway.refundCount, 1)
        expect("log entry for retry", log.single { it.startsWith("request_refund") }, "request_refund#2 -> re_o42_1")
    }

    scenario("Non-retryable failure in eligibility") {
        val gateway = FakeRefundGateway()
        val result = WorkflowExecutor().run(refundWorkflow(gateway, mutableListOf(), eligible = false))
        expect("check_eligibility", result.statuses["check_eligibility"], FAILED)
        expect("resolve_conversation", result.statuses["resolve_conversation"], NOT_EXECUTED)
        expect("refunds issued", gateway.refundCount, 0)
    }

    scenario("Independent branches keep running after a failure") {
        val steps = listOf(
            Step("fetch"),
            Step("refund", listOf("fetch")) { error("declined") },
            Step("tag_conversation", listOf("fetch")),
            Step("notify", listOf("refund")),
        )
        val result = WorkflowExecutor().run(steps)
        expect("tag_conversation", result.statuses["tag_conversation"], SUCCESS)
        expect("notify", result.statuses["notify"], NOT_EXECUTED)
    }

    scenario("Validation – order, cycles, unknown dependencies") {
        val shuffled = listOf(Step("c", listOf("b")), Step("b", listOf("a")), Step("a"))
        expect("topological order", WorkflowExecutor.topologicalOrder(shuffled), listOf("a", "b", "c"))
        expectThrows("cycle", IllegalArgumentException::class) {
            WorkflowExecutor().execute(listOf(Step("a", listOf("b")), Step("b", listOf("a"))))
        }
        expectThrows("unknown dependency", IllegalArgumentException::class) {
            WorkflowExecutor().execute(listOf(Step("a", listOf("ghost"))))
        }
        expectThrows("duplicate id", IllegalArgumentException::class) {
            WorkflowExecutor().execute(listOf(Step("a"), Step("a")))
        }
    }

    Harness.summary()
}
