package intercom._2conversationservice.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.note
import intercom.Harness.scenario

/*
 * Problem 2 – Conversation service (+ "Design a conversation history system").
 *
 * One customer + Fin (AI agent, always present from the start) + any number of employees who
 * join mid-conversation. Only current participants can send. Every message is appended to the
 * conversation log (source of truth, ordered) and fanned out to each other participant's inbox.
 */

const val FIN_ID = "fin"

enum class Role { CUSTOMER, AI_AGENT, EMPLOYEE }

/** PUBLIC is seen by everyone; INTERNAL_NOTE is teammates-only (Intercom "notes"). */
enum class Visibility { PUBLIC, INTERNAL_NOTE }

data class Message(
    val id: Long,
    val conversationId: String,
    /** 1-based position inside its conversation: total order + O(1) cursor lookup. */
    val sequence: Int,
    val senderId: String,
    val text: String,
    val visibility: Visibility = Visibility.PUBLIC,
)

/** A page of history in chronological order; pass [nextCursor] as `beforeSequence` for older messages. */
data class Page(val messages: List<Message>, val nextCursor: Int?)

/**
 * - createConversation / addParticipant / removeParticipant: O(1)
 * - sendMessage: O(p) fan-out to p participants
 * - getMessages(limit): O(limit) for the team view, O(limit + skipped notes) for the customer view
 * - getHistory(pageSize): O(pageSize)
 * - space: O(m · p) because each message is copied by reference into each inbox
 */
class ConversationService {

    private class Conversation(val id: String, val customerId: String) {
        val roles = HashMap<String, Role>()          // everyone who has ever joined
        val activeParticipants = LinkedHashSet<String>()
        val messages = ArrayList<Message>()           // append-only log, index = sequence - 1

        fun join(participantId: String, role: Role) {
            roles[participantId] = role
            activeParticipants += participantId
        }
    }

    private val conversations = HashMap<String, Conversation>()
    private val inboxes = HashMap<String, MutableList<Message>>()
    private var nextConversationNumber = 1
    private var nextMessageId = 1L

    fun createConversation(customerId: String): String {
        require(customerId.isNotBlank()) { "customerId must not be blank" }
        require(customerId != FIN_ID) { "'$FIN_ID' is reserved for the AI agent" }
        val id = "conv-${nextConversationNumber++}"
        conversations[id] = Conversation(id, customerId).apply {
            join(customerId, Role.CUSTOMER)
            join(FIN_ID, Role.AI_AGENT)
        }
        return id
    }

    /** Idempotent: adding someone who is already in the conversation is a no-op. */
    fun addParticipant(conversationId: String, employeeId: String) {
        val conversation = conversation(conversationId)
        require(employeeId.isNotBlank()) { "employeeId must not be blank" }
        require(employeeId != conversation.customerId && employeeId != FIN_ID) {
            "$employeeId cannot be added as an employee"
        }
        conversation.join(employeeId, Role.EMPLOYEE)
    }

    fun removeParticipant(conversationId: String, participantId: String) {
        val conversation = conversation(conversationId)
        require(participantId != conversation.customerId) { "The customer cannot leave their own conversation" }
        conversation.activeParticipants -= participantId
    }

    fun participants(conversationId: String): List<String> = conversation(conversationId).activeParticipants.toList()

    fun sendMessage(conversationId: String, senderId: String, message: String): Message =
        append(conversationId, senderId, message, Visibility.PUBLIC)

    /** Internal note: visible to teammates (and Fin), never delivered to the customer. */
    fun addNote(conversationId: String, senderId: String, note: String): Message {
        val conversation = conversation(conversationId)
        require(conversation.roles[senderId] != Role.CUSTOMER) { "Customers cannot write internal notes" }
        return append(conversationId, senderId, note, Visibility.INTERNAL_NOTE)
    }

    /**
     * Latest [limit] messages in chronological order.
     * [viewerId] = null is the team view (everything); the customer never sees internal notes.
     */
    fun getMessages(conversationId: String, limit: Int, viewerId: String? = null): List<Message> {
        require(limit >= 0) { "limit must be >= 0" }
        val conversation = conversation(conversationId)
        if (viewerId != null) {
            require(viewerId in conversation.roles) { "$viewerId has never been part of $conversationId" }
        }
        val hideNotes = viewerId != null && conversation.roles[viewerId] == Role.CUSTOMER
        val result = ArrayList<Message>(minOf(limit, conversation.messages.size))
        // Walk backwards so we only touch the messages we return.
        for (i in conversation.messages.indices.reversed()) {
            if (result.size == limit) break
            val message = conversation.messages[i]
            if (hideNotes && message.visibility == Visibility.INTERNAL_NOTE) continue
            result += message
        }
        return result.asReversed().toList()
    }

    /**
     * Cursor pagination over the full history, newest page first.
     * Returns messages with `sequence < beforeSequence` (or the newest page when null).
     */
    fun getHistory(conversationId: String, pageSize: Int, beforeSequence: Int? = null): Page {
        require(pageSize > 0) { "pageSize must be > 0" }
        val messages = conversation(conversationId).messages
        val endExclusive = ((beforeSequence ?: (messages.size + 1)) - 1).coerceIn(0, messages.size)
        val start = maxOf(0, endExclusive - pageSize)
        val page = messages.subList(start, endExclusive).toList()
        return Page(page, nextCursor = if (start > 0) start + 1 else null)
    }

    /** Messages delivered to a participant (everything sent by others while they were present). */
    fun getInbox(participantId: String): List<Message> = inboxes[participantId].orEmpty().toList()

    private fun append(conversationId: String, senderId: String, text: String, visibility: Visibility): Message {
        val conversation = conversation(conversationId)
        require(senderId in conversation.activeParticipants) { "$senderId is not a participant of $conversationId" }
        require(text.isNotBlank()) { "message must not be blank" }

        val message = Message(
            id = nextMessageId++,
            conversationId = conversationId,
            sequence = conversation.messages.size + 1,
            senderId = senderId,
            text = text,
            visibility = visibility,
        )
        conversation.messages += message
        for (participant in conversation.activeParticipants) {
            if (participant == senderId) continue
            if (visibility == Visibility.INTERNAL_NOTE && conversation.roles[participant] == Role.CUSTOMER) continue
            inboxes.getOrPut(participant) { mutableListOf() } += message
        }
        return message
    }

    private fun conversation(id: String) = requireNotNull(conversations[id]) { "Unknown conversation: $id" }
}

private fun List<Message>.render() = map { "${it.senderId}: ${it.text}" }

fun main() {
    scenario("Example – customer, Fin, then Alice and Bob join") {
        val service = ConversationService()
        val id = service.createConversation("cust-42")
        expect("conversation id", id, "conv-1")
        expect("initial participants", service.participants(id), listOf("cust-42", FIN_ID))

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
        expect("sequences", service.getMessages(id, 10).map { it.sequence }, listOf(1, 2, 3, 4))

        note("Inbox = messages from others while you were in the conversation")
        expect("customer inbox", service.getInbox("cust-42").map { it.sequence }, listOf(2, 3, 4))
        expect("fin inbox", service.getInbox(FIN_ID).map { it.sequence }, listOf(1, 3, 4))
        expect("alice inbox (joined before #3)", service.getInbox("alice").map { it.sequence }, listOf(4))
        expect("bob inbox (only sent)", service.getInbox("bob").map { it.sequence }, emptyList<Int>())
    }

    scenario("Only participants can send; leaving stops delivery") {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        service.addParticipant(id, "alice")
        service.addParticipant(id, "bob")
        expectThrows("eve sends", IllegalArgumentException::class) { service.sendMessage(id, "eve", "hi") }

        service.removeParticipant(id, "bob")
        service.sendMessage(id, "alice", "Bob has handed this over to me.")
        expect("bob inbox after leaving", service.getInbox("bob"), emptyList<Message>())
        expectThrows("bob sends after leaving", IllegalArgumentException::class) { service.sendMessage(id, "bob", "hi") }
        expectThrows("customer cannot leave", IllegalArgumentException::class) { service.removeParticipant(id, "cust-1") }
        expectThrows("unknown conversation", IllegalArgumentException::class) { service.sendMessage("nope", "alice", "hi") }
    }

    scenario("Internal notes are hidden from the customer") {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        service.addParticipant(id, "alice")
        service.addParticipant(id, "bob")
        service.sendMessage(id, "cust-1", "Where is my refund?")
        service.addNote(id, "alice", "Refund approved, waiting on finance.")
        service.sendMessage(id, "alice", "Your refund is on its way!")

        expect("team view", service.getMessages(id, 10).map { it.sequence }, listOf(1, 2, 3))
        expect("customer view", service.getMessages(id, 10, viewerId = "cust-1").map { it.sequence }, listOf(1, 3))
        expect("customer inbox", service.getInbox("cust-1").map { it.sequence }, listOf(3))
        expect("bob inbox", service.getInbox("bob").map { it.sequence }, listOf(1, 2, 3))
        expectThrows("customer writes note", IllegalArgumentException::class) { service.addNote(id, "cust-1", "x") }
    }

    scenario("History pagination (conversation history system)") {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        (1..6).forEach { service.sendMessage(id, "cust-1", "message $it") }

        val newest = service.getHistory(id, pageSize = 4)
        expect("page 1 sequences", newest.messages.map { it.sequence }, listOf(3, 4, 5, 6))
        expect("page 1 cursor", newest.nextCursor, 3)
        val older = service.getHistory(id, pageSize = 4, beforeSequence = newest.nextCursor)
        expect("page 2 sequences", older.messages.map { it.sequence }, listOf(1, 2))
        expect("page 2 cursor", older.nextCursor, null)
    }

    Harness.summary()
}
