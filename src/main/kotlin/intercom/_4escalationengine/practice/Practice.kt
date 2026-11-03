package intercom._4escalationengine.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario

/*
 * Problem 4 – Human escalation engine (state machine). Read ../README.md first.
 *
 *  - High risk -> escalate immediately.
 *  - confidence < 0.5 -> escalate.
 *  - 3 unsuccessful attempts (0.5 <= confidence < 0.7) in a row -> escalate.
 *  - Escalation is idempotent (don't escalate twice).
 *  - A resolved conversation is not reopened by a new message.
 */

enum class ConversationStatus { BOT_ACTIVE, NEEDS_HUMAN, ASSIGNED, RESOLVED }

class EscalationEngine {

    fun processMessage(conversationId: String, message: String, confidence: Double, isHighRisk: Boolean): ConversationStatus {
        TODO("processMessage")
    }

    fun agentJoined(conversationId: String, agentId: String): ConversationStatus {
        TODO("agentJoined")
    }

    fun resolve(conversationId: String): ConversationStatus {
        TODO("resolve")
    }
}

fun main() {
    scenario("Example – confident answer, low confidence, Alice joins") {
        val engine = EscalationEngine()
        expect("conf 0.95", engine.processMessage("c1", "How do I reset my password?", 0.95, false), ConversationStatus.BOT_ACTIVE)
        expect("conf 0.35", engine.processMessage("c1", "It still says my account is locked", 0.35, false), ConversationStatus.NEEDS_HUMAN)
        expect("alice joins", engine.agentJoined("c1", "alice"), ConversationStatus.ASSIGNED)
    }

    scenario("High risk escalates immediately") {
        val engine = EscalationEngine()
        expect("fraud", engine.processMessage("c2", "There is a fraudulent charge on my card", 0.99, true), ConversationStatus.NEEDS_HUMAN)
    }

    scenario("Three unsuccessful attempts") {
        val engine = EscalationEngine()
        expect("0.60", engine.processMessage("c3", "Why was I charged twice?", 0.60, false), ConversationStatus.BOT_ACTIVE)
        expect("0.65", engine.processMessage("c3", "That didn't answer my question", 0.65, false), ConversationStatus.BOT_ACTIVE)
        expect("0.55", engine.processMessage("c3", "Still confused", 0.55, false), ConversationStatus.NEEDS_HUMAN)
    }

    scenario("Resolved is not auto-reopened") {
        val engine = EscalationEngine()
        engine.processMessage("c4", "question", 0.9, false)
        expect("resolve", engine.resolve("c4"), ConversationStatus.RESOLVED)
        expect("message after resolve", engine.processMessage("c4", "thanks!", 0.2, false), ConversationStatus.RESOLVED)
    }

    Harness.summary()
}
