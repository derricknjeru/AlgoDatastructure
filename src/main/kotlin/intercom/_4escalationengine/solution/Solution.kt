package intercom._4escalationengine.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario
import intercom._4escalationengine.solution.ConversationStatus.ASSIGNED
import intercom._4escalationengine.solution.ConversationStatus.BOT_ACTIVE
import intercom._4escalationengine.solution.ConversationStatus.NEEDS_HUMAN
import intercom._4escalationengine.solution.ConversationStatus.RESOLVED

/*
 * Problem 4 – Human escalation engine (state machine).
 *
 *   BOT_ACTIVE --(high risk | asks for human | conf < 0.5 | 3 unsuccessful)--> NEEDS_HUMAN
 *   BOT_ACTIVE / NEEDS_HUMAN --agentJoined--> ASSIGNED
 *   any non-resolved --resolve--> RESOLVED
 *   RESOLVED --reopen (explicit only)--> BOT_ACTIVE
 *
 * Messages only drive transitions while the bot owns the conversation (BOT_ACTIVE). In every other
 * state they are no-ops, which is what makes escalation idempotent.
 */

enum class ConversationStatus { BOT_ACTIVE, NEEDS_HUMAN, ASSIGNED, RESOLVED }

enum class EscalationReason { HIGH_RISK, CUSTOMER_ASKED_FOR_HUMAN, LOW_CONFIDENCE, REPEATED_UNSUCCESSFUL_ATTEMPTS }

data class Escalation(val conversationId: String, val reason: EscalationReason, val triggeringMessage: String)

/**
 * An answer with confidence in [escalateBelow, successfulAt) is an "unsuccessful attempt";
 * [maxUnsuccessfulAttempts] consecutive ones escalate. A confident answer resets the streak.
 *
 * All operations are O(1) (plus O(message length) for the human-request check).
 */
class EscalationEngine(
    private val escalateBelow: Double = 0.5,
    private val successfulAt: Double = 0.7,
    private val maxUnsuccessfulAttempts: Int = 3,
) {
    init {
        require(escalateBelow <= successfulAt) { "escalateBelow must be <= successfulAt" }
        require(maxUnsuccessfulAttempts >= 1) { "maxUnsuccessfulAttempts must be >= 1" }
    }

    private class State {
        var status = BOT_ACTIVE
        var unsuccessfulStreak = 0
        var assignee: String? = null
    }

    private val states = HashMap<String, State>()
    private val escalations = mutableListOf<Escalation>()

    fun processMessage(conversationId: String, message: String, confidence: Double, isHighRisk: Boolean): ConversationStatus {
        require(confidence in 0.0..1.0) { "confidence must be within [0, 1], was $confidence" }
        val state = states.getOrPut(conversationId) { State() }
        if (state.status != BOT_ACTIVE) return state.status

        val reason = when {
            isHighRisk -> EscalationReason.HIGH_RISK
            asksForHuman(message) -> EscalationReason.CUSTOMER_ASKED_FOR_HUMAN
            confidence < escalateBelow -> EscalationReason.LOW_CONFIDENCE
            confidence < successfulAt && ++state.unsuccessfulStreak >= maxUnsuccessfulAttempts ->
                EscalationReason.REPEATED_UNSUCCESSFUL_ATTEMPTS
            else -> {
                if (confidence >= successfulAt) state.unsuccessfulStreak = 0
                null
            }
        }
        if (reason != null) {
            state.status = NEEDS_HUMAN
            escalations += Escalation(conversationId, reason, message)
        }
        return state.status
    }

    /** A teammate takes over (also allowed proactively while the bot is still active). */
    fun agentJoined(conversationId: String, agentId: String): ConversationStatus {
        val state = stateOrThrow(conversationId)
        check(state.status != RESOLVED) { "$conversationId is resolved; reopen it first" }
        state.status = ASSIGNED
        state.assignee = agentId
        return state.status
    }

    /** Idempotent. */
    fun resolve(conversationId: String): ConversationStatus {
        stateOrThrow(conversationId).status = RESOLVED
        return RESOLVED
    }

    /** Explicit only – a resolved conversation is never reopened automatically by a message. */
    fun reopen(conversationId: String): ConversationStatus {
        val state = stateOrThrow(conversationId)
        check(state.status == RESOLVED) { "Only resolved conversations can be reopened" }
        state.status = BOT_ACTIVE
        state.unsuccessfulStreak = 0
        state.assignee = null
        return state.status
    }

    fun status(conversationId: String): ConversationStatus? = states[conversationId]?.status

    fun assignee(conversationId: String): String? = states[conversationId]?.assignee

    fun escalationsFor(conversationId: String): List<Escalation> = escalations.filter { it.conversationId == conversationId }

    private fun stateOrThrow(conversationId: String) =
        requireNotNull(states[conversationId]) { "Unknown conversation: $conversationId" }

    private companion object {
        val HUMAN_REQUEST = Regex("""\b(human|real person|agent|representative|someone real)\b""", RegexOption.IGNORE_CASE)

        fun asksForHuman(message: String) = HUMAN_REQUEST.containsMatchIn(message)
    }
}

fun main() {
    scenario("Example – confident answer, low confidence, Alice joins") {
        val engine = EscalationEngine()
        expect("conf 0.95", engine.processMessage("c1", "How do I reset my password?", 0.95, false), BOT_ACTIVE)
        expect("conf 0.35", engine.processMessage("c1", "It still says my account is locked", 0.35, false), NEEDS_HUMAN)
        expect("alice joins", engine.agentJoined("c1", "alice"), ASSIGNED)
        expect("reason", engine.escalationsFor("c1").map { it.reason }, listOf(EscalationReason.LOW_CONFIDENCE))
    }

    scenario("High risk escalates immediately, even with high confidence") {
        val engine = EscalationEngine()
        expect("fraud", engine.processMessage("c2", "There is a fraudulent charge on my card", 0.99, true), NEEDS_HUMAN)
        expect("reason", engine.escalationsFor("c2").single().reason, EscalationReason.HIGH_RISK)
    }

    scenario("Three unsuccessful attempts in a row") {
        val engine = EscalationEngine()
        expect("0.60", engine.processMessage("c3", "Why was I charged twice?", 0.60, false), BOT_ACTIVE)
        expect("0.65", engine.processMessage("c3", "That didn't answer my question", 0.65, false), BOT_ACTIVE)
        expect("0.55", engine.processMessage("c3", "Still confused", 0.55, false), NEEDS_HUMAN)
        expect("reason", engine.escalationsFor("c3").single().reason, EscalationReason.REPEATED_UNSUCCESSFUL_ATTEMPTS)
    }

    scenario("A confident answer resets the streak") {
        val engine = EscalationEngine()
        val statuses = listOf(0.6, 0.9, 0.6, 0.6).map { engine.processMessage("c4", "question", it, false) }
        expect("0.6, 0.9, 0.6, 0.6", statuses, List(4) { BOT_ACTIVE })
        expect("third unsuccessful", engine.processMessage("c4", "question", 0.6, false), NEEDS_HUMAN)
    }

    scenario("Customer asks for a human") {
        val engine = EscalationEngine()
        expect("ask", engine.processMessage("c5", "Can I talk to a human please?", 0.9, false), NEEDS_HUMAN)
        expect("reason", engine.escalationsFor("c5").single().reason, EscalationReason.CUSTOMER_ASKED_FOR_HUMAN)
    }

    scenario("Escalation is idempotent; resolved is not auto-reopened") {
        val engine = EscalationEngine()
        engine.processMessage("c6", "fraud!", 0.9, true)
        expect("another risky message", engine.processMessage("c6", "fraud again", 0.1, true), NEEDS_HUMAN)
        expect("still one escalation", engine.escalationsFor("c6").size, 1)

        expect("resolve", engine.resolve("c6"), RESOLVED)
        expect("resolve again", engine.resolve("c6"), RESOLVED)
        expect("message after resolve", engine.processMessage("c6", "thanks!", 0.2, false), RESOLVED)
        expectThrows("agent joins resolved", IllegalStateException::class) { engine.agentJoined("c6", "bob") }
        expect("explicit reopen", engine.reopen("c6"), BOT_ACTIVE)
        expectThrows("unknown conversation", IllegalArgumentException::class) { engine.resolve("nope") }
    }

    Harness.summary()
}
