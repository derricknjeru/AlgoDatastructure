package intercom._2conversationservice.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario

/*
 * Problem 2 – Conversation service (45–60 min). Read ../README.md first.
 *
 *  - createConversation(customerId): the customer and Fin ("fin") are participants from the start.
 *  - Employees join mid-conversation via addParticipant.
 *  - Only current participants can send (throw IllegalArgumentException otherwise).
 *  - getMessages returns the latest `limit` messages in chronological order.
 *  - Each message is delivered to every *other* current participant's inbox.
 *  - Follow-ups: removeParticipant, internal notes, history pagination.
 */

const val FIN_ID = "fin"

data class Message(
    val id: Long,
    val conversationId: String,
    val sequence: Int,
    val senderId: String,
    val text: String,
)

class ConversationService {

    fun createConversation(customerId: String): String {
        TODO("createConversation – return ids like conv-1, conv-2 …")
    }

    fun addParticipant(conversationId: String, employeeId: String) {
        TODO("addParticipant")
    }

    fun sendMessage(conversationId: String, senderId: String, message: String): Message {
        TODO("sendMessage")
    }

    fun getMessages(conversationId: String, limit: Int): List<Message> {
        TODO("getMessages")
    }

    fun getInbox(participantId: String): List<Message> {
        TODO("getInbox")
    }

    // Follow-up
    fun removeParticipant(conversationId: String, participantId: String) {
        TODO("removeParticipant (follow-up)")
    }
}

private fun List<Message>.render() = map { "${it.senderId}: ${it.text}" }

fun main() {
    scenario("Example – customer, Fin, then Alice and Bob join") {
        val service = ConversationService()
        val id = service.createConversation("cust-42")
        service.sendMessage(id, "cust-42", "Hello, I need help with my payment.")
        service.sendMessage(id, FIN_ID, "I can help you with that.")
        service.addParticipant(id, "alice")
        service.sendMessage(id, "alice", "Hi, I'm Alice from Support. Let me look into this.")
        service.addParticipant(id, "bob")
        service.sendMessage(id, "bob", "Bob from Billing here - I found the duplicate charge.")

        expect(
            "getMessages(10)", service.getMessages(id, 10).render(),
            listOf(
                "cust-42: Hello, I need help with my payment.",
                "fin: I can help you with that.",
                "alice: Hi, I'm Alice from Support. Let me look into this.",
                "bob: Bob from Billing here - I found the duplicate charge.",
            ),
        )
        expect("getMessages(2) senders", service.getMessages(id, 2).map { it.senderId }, listOf("alice", "bob"))
        expect("customer inbox", service.getInbox("cust-42").map { it.sequence }, listOf(2, 3, 4))
        expect("alice inbox", service.getInbox("alice").map { it.sequence }, listOf(4))
    }

    scenario("Only participants can send") {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        expectThrows("eve sends", IllegalArgumentException::class) { service.sendMessage(id, "eve", "hi") }
    }

    scenario("Follow-up – leaving stops delivery") {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        service.addParticipant(id, "alice")
        service.addParticipant(id, "bob")
        service.removeParticipant(id, "bob")
        service.sendMessage(id, "alice", "Bob has handed this over to me.")
        expect("bob inbox", service.getInbox("bob"), emptyList<Message>())
        expectThrows("bob sends after leaving", IllegalArgumentException::class) { service.sendMessage(id, "bob", "hi") }
    }

    Harness.summary()
}
