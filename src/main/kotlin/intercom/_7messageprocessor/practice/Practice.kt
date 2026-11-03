package intercom._7messageprocessor.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario

/*
 * Problem 7 – Real-time message processor. Read ../README.md first.
 *
 *  - Messages may arrive out of order: return them sorted by timestamp.
 *  - The same message (same id) may be delivered more than once: keep it once.
 *  - Identical timestamps: order by id.
 *  - Conversations are independent.
 *  - Follow-ups: latest N, pagination.
 */

data class IncomingMessage(val id: String, val conversationId: String, val timestamp: Long, val content: String)

class MessageProcessor {

    /** Returns false if the message was a duplicate. */
    fun receive(message: IncomingMessage): Boolean {
        TODO("receive")
    }

    fun getConversationMessages(conversationId: String): List<IncomingMessage> {
        TODO("getConversationMessages")
    }

    // Follow-up
    fun getLatestMessages(conversationId: String, n: Int): List<IncomingMessage> {
        TODO("getLatestMessages (follow-up)")
    }
}

private fun List<IncomingMessage>.ids() = map { it.id }

fun main() {
    scenario("Out-of-order delivery") {
        val processor = MessageProcessor()
        processor.receive(IncomingMessage("m3", "c1", 300, "Any update?"))
        processor.receive(IncomingMessage("m1", "c1", 100, "Hi, my invoice is wrong"))
        processor.receive(IncomingMessage("m2", "c1", 200, "It shows 2 seats, we have 1"))
        expect("c1", processor.getConversationMessages("c1").ids(), listOf("m1", "m2", "m3"))
    }

    scenario("Duplicates") {
        val processor = MessageProcessor()
        expect("first", processor.receive(IncomingMessage("m1", "c1", 100, "Hi")), true)
        expect("again", processor.receive(IncomingMessage("m1", "c1", 100, "Hi")), false)
        expect("count", processor.getConversationMessages("c1").size, 1)
    }

    scenario("Same timestamp + isolation") {
        val processor = MessageProcessor()
        processor.receive(IncomingMessage("m5", "c1", 400, "b"))
        processor.receive(IncomingMessage("m4", "c1", 400, "a"))
        processor.receive(IncomingMessage("x1", "c2", 50, "other"))
        expect("c1", processor.getConversationMessages("c1").ids(), listOf("m4", "m5"))
        expect("c2", processor.getConversationMessages("c2").ids(), listOf("x1"))
    }

    scenario("Follow-up – latest N") {
        val processor = MessageProcessor()
        listOf(300L to "m3", 100L to "m1", 200L to "m2").forEach { (t, id) -> processor.receive(IncomingMessage(id, "c1", t, id)) }
        expect("latest 2", processor.getLatestMessages("c1", 2).ids(), listOf("m2", "m3"))
    }

    Harness.summary()
}
