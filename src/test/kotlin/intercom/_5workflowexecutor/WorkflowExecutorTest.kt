package intercom._5workflowexecutor

import intercom._5workflowexecutor.solution.FakeRefundGateway
import intercom._5workflowexecutor.solution.Step
import intercom._5workflowexecutor.solution.StepStatus.FAILED
import intercom._5workflowexecutor.solution.StepStatus.NOT_EXECUTED
import intercom._5workflowexecutor.solution.StepStatus.SUCCESS
import intercom._5workflowexecutor.solution.TransientStepException
import intercom._5workflowexecutor.solution.WorkflowExecutor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WorkflowExecutorTest {

    @Test
    fun `failed step skips its dependents`() {
        val result = WorkflowExecutor().run(
            listOf(
                Step("retrieve_order"),
                Step("check_eligibility", listOf("retrieve_order")),
                Step("request_refund", listOf("check_eligibility")) { error("declined") },
                Step("notify_customer", listOf("request_refund")),
                Step("resolve", listOf("notify_customer")),
            ),
        )
        assertFalse(result.succeeded)
        assertEquals(
            listOf(SUCCESS, SUCCESS, FAILED, NOT_EXECUTED, NOT_EXECUTED),
            result.statuses.values.toList(),
        )
        assertEquals(listOf("retrieve_order", "check_eligibility", "request_refund"), result.executionOrder)
    }

    @Test
    fun `runs in dependency order regardless of input order`() {
        val order = mutableListOf<String>()
        val ok = WorkflowExecutor().execute(
            listOf(
                Step("d", listOf("b", "c")) { order += "d" },
                Step("c", listOf("a")) { order += "c" },
                Step("b", listOf("a")) { order += "b" },
                Step("a") { order += "a" },
            ),
        )
        assertTrue(ok)
        assertEquals("a", order.first())
        assertEquals("d", order.last())
        assertEquals(4, order.size)
    }

    @Test
    fun `transient failures are retried up to maxAttempts`() {
        var calls = 0
        val flaky = Step("flaky", maxAttempts = 3) { if (++calls < 3) throw TransientStepException("timeout") }
        val result = WorkflowExecutor().run(listOf(flaky))
        assertTrue(result.succeeded)
        assertEquals(3, result.attempts["flaky"])

        val alwaysDown = Step("down", maxAttempts = 2) { throw TransientStepException("503") }
        val failed = WorkflowExecutor().run(listOf(alwaysDown))
        assertEquals(FAILED, failed.statuses["down"])
        assertEquals(2, failed.attempts["down"])
    }

    @Test
    fun `non transient failures are not retried`() {
        var calls = 0
        val result = WorkflowExecutor().run(listOf(Step("x", maxAttempts = 5) { calls++; error("bad input") }))
        assertEquals(FAILED, result.statuses["x"])
        assertEquals(1, calls)
    }

    @Test
    fun `idempotency key is stable across retries so refund happens once`() {
        val gateway = FakeRefundGateway(timeoutsAfterProcessing = 2)
        val keys = mutableListOf<String>()
        val step = Step("request_refund", maxAttempts = 3) { ctx ->
            keys += ctx.idempotencyKey
            gateway.refund("o1", ctx.idempotencyKey)
        }
        val result = WorkflowExecutor { "run-9" }.run(listOf(step))
        assertTrue(result.succeeded)
        assertEquals(List(3) { "run-9:request_refund" }, keys)
        assertEquals(1, gateway.refundCount)
    }

    @Test
    fun `invalid workflows are rejected before running anything`() {
        var ran = false
        assertFailsWith<IllegalArgumentException> {
            WorkflowExecutor().execute(listOf(Step("ok") { ran = true }, Step("a", listOf("b")), Step("b", listOf("a"))))
        }
        assertFalse(ran)
        assertFailsWith<IllegalArgumentException> { WorkflowExecutor().execute(listOf(Step("a", listOf("a")))) }
        assertFailsWith<IllegalArgumentException> { WorkflowExecutor().execute(listOf(Step("a", listOf("zzz")))) }
        assertFailsWith<IllegalArgumentException> { WorkflowExecutor().execute(listOf(Step("a"), Step("a"))) }
    }

    @Test
    fun `empty workflow succeeds`() {
        assertTrue(WorkflowExecutor().execute(emptyList()))
    }
}
