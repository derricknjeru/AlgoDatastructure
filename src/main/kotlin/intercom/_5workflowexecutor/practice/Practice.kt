package intercom._5workflowexecutor.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario

/*
 * Problem 5 – Multi-step workflow executor. Read ../README.md first.
 *
 *  - Run steps in dependency order (topological sort).
 *  - Reject workflows with cycles or unknown dependencies (IllegalArgumentException).
 *  - If a step fails, its (transitive) dependents are NOT_EXECUTED; independent steps still run.
 *  - Follow-ups: retries for transient failures, idempotency keys, status tracking.
 */

enum class StepStatus { PENDING, SUCCESS, FAILED, NOT_EXECUTED }

data class Step(
    val id: String,
    val dependsOn: List<String> = emptyList(),
    /** Throw to signal failure. */
    val action: () -> Unit = {},
)

class WorkflowExecutor {

    /** Statuses of the last run, in input order. */
    val statuses: Map<String, StepStatus>
        get() = TODO("statuses")

    fun execute(steps: List<Step>): Boolean {
        TODO("execute")
    }
}

fun main() {
    scenario("Example – refund fails, dependents are not executed") {
        val executor = WorkflowExecutor()
        val ok = executor.execute(
            listOf(
                Step("retrieve_order"),
                Step("check_eligibility", listOf("retrieve_order")),
                Step("request_refund", listOf("check_eligibility")) { error("declined") },
                Step("notify_customer", listOf("request_refund")),
            ),
        )
        expect("execute", ok, false)
        expect(
            "statuses", executor.statuses,
            mapOf(
                "retrieve_order" to StepStatus.SUCCESS,
                "check_eligibility" to StepStatus.SUCCESS,
                "request_refund" to StepStatus.FAILED,
                "notify_customer" to StepStatus.NOT_EXECUTED,
            ),
        )
    }

    scenario("Steps given out of order still run in dependency order") {
        val order = mutableListOf<String>()
        val executor = WorkflowExecutor()
        executor.execute(
            listOf(
                Step("notify", listOf("refund")) { order += "notify" },
                Step("refund", listOf("lookup")) { order += "refund" },
                Step("lookup") { order += "lookup" },
            ),
        )
        expect("order", order, listOf("lookup", "refund", "notify"))
    }

    scenario("Cycle detection") {
        expectThrows("a <-> b", IllegalArgumentException::class) {
            WorkflowExecutor().execute(listOf(Step("a", listOf("b")), Step("b", listOf("a"))))
        }
    }

    Harness.summary()
}
