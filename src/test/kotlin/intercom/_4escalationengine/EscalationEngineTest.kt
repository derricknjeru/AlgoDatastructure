package intercom._4escalationengine

import intercom._4escalationengine.solution.ConversationStatus.ASSIGNED
import intercom._4escalationengine.solution.ConversationStatus.BOT_ACTIVE
import intercom._4escalationengine.solution.ConversationStatus.NEEDS_HUMAN
import intercom._4escalationengine.solution.ConversationStatus.RESOLVED
import intercom._4escalationengine.solution.EscalationEngine
import intercom._4escalationengine.solution.EscalationReason
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EscalationEngineTest {

    @Test
    fun `example sequence`() {
        val engine = EscalationEngine()
        assertEquals(BOT_ACTIVE, engine.processMessage("c1", "How do I reset my password?", 0.95, false))
        assertEquals(NEEDS_HUMAN, engine.processMessage("c1", "It is locked", 0.35, false))
        assertEquals(ASSIGNED, engine.agentJoined("c1", "alice"))
        assertEquals("alice", engine.assignee("c1"))
        assertEquals(ASSIGNED, engine.processMessage("c1", "thanks", 0.1, false))
    }

    @Test
    fun `high risk wins over confidence`() {
        val engine = EscalationEngine()
        assertEquals(NEEDS_HUMAN, engine.processMessage("c", "fraud", 0.99, true))
        assertEquals(EscalationReason.HIGH_RISK, engine.escalationsFor("c").single().reason)
    }

    @Test
    fun `three consecutive unsuccessful attempts escalate and a success resets`() {
        val engine = EscalationEngine()
        assertEquals(List(4) { BOT_ACTIVE }, listOf(0.6, 0.6, 0.9, 0.6).map { engine.processMessage("c", "q", it, false) })
        assertEquals(BOT_ACTIVE, engine.processMessage("c", "q", 0.69, false))
        assertEquals(NEEDS_HUMAN, engine.processMessage("c", "q", 0.5, false))
        assertEquals(EscalationReason.REPEATED_UNSUCCESSFUL_ATTEMPTS, engine.escalationsFor("c").single().reason)
    }

    @Test
    fun `boundary confidence values`() {
        val engine = EscalationEngine()
        assertEquals(BOT_ACTIVE, engine.processMessage("a", "q", 0.5, false))
        assertEquals(NEEDS_HUMAN, engine.processMessage("b", "q", 0.4999, false))
        assertFailsWith<IllegalArgumentException> { engine.processMessage("c", "q", 1.5, false) }
    }

    @Test
    fun `asking for a human escalates`() {
        val engine = EscalationEngine()
        assertEquals(NEEDS_HUMAN, engine.processMessage("c", "Let me speak to an AGENT", 0.95, false))
        assertEquals(BOT_ACTIVE, engine.processMessage("d", "My agenda is empty", 0.95, false))
    }

    @Test
    fun `escalation is idempotent`() {
        val engine = EscalationEngine()
        engine.processMessage("c", "fraud", 0.9, true)
        engine.processMessage("c", "fraud", 0.1, true)
        engine.processMessage("c", "human please", 0.1, false)
        assertEquals(1, engine.escalationsFor("c").size)
    }

    @Test
    fun `resolved is sticky until reopened`() {
        val engine = EscalationEngine()
        engine.processMessage("c", "q", 0.9, false)
        assertEquals(RESOLVED, engine.resolve("c"))
        assertEquals(RESOLVED, engine.processMessage("c", "q", 0.1, true))
        assertFailsWith<IllegalStateException> { engine.agentJoined("c", "bob") }
        assertEquals(BOT_ACTIVE, engine.reopen("c"))
        assertFailsWith<IllegalStateException> { engine.reopen("c") }
        assertFailsWith<IllegalArgumentException> { engine.agentJoined("unknown", "bob") }
    }
}
