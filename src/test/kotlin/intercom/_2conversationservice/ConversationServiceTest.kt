package intercom._2conversationservice

import intercom._2conversationservice.solution.ConversationService
import intercom._2conversationservice.solution.FIN_ID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ConversationServiceTest {

    private fun exampleConversation(): Pair<ConversationService, String> {
        val service = ConversationService()
        val id = service.createConversation("cust-42")
        service.sendMessage(id, "cust-42", "Hello, I need help with my payment.")
        service.sendMessage(id, FIN_ID, "I can help you with that.")
        service.addParticipant(id, "alice")
        service.sendMessage(id, "alice", "Hi, I'm Alice.")
        service.addParticipant(id, "bob")
        service.sendMessage(id, "bob", "Bob from Billing.")
        return service to id
    }

    @Test
    fun `customer and Fin are initial participants`() {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        assertEquals(listOf("cust-1", FIN_ID), service.participants(id))
    }

    @Test
    fun `getMessages returns latest N in chronological order`() {
        val (service, id) = exampleConversation()
        assertEquals(listOf("cust-42", FIN_ID, "alice", "bob"), service.getMessages(id, 10).map { it.senderId })
        assertEquals(listOf("alice", "bob"), service.getMessages(id, 2).map { it.senderId })
        assertTrue(service.getMessages(id, 0).isEmpty())
    }

    @Test
    fun `messages are delivered to other current participants only`() {
        val (service, id) = exampleConversation()
        assertEquals(listOf(2, 3, 4), service.getInbox("cust-42").map { it.sequence })
        assertEquals(listOf(1, 3, 4), service.getInbox(FIN_ID).map { it.sequence })
        assertEquals(listOf(4), service.getInbox("alice").map { it.sequence })
        assertTrue(service.getInbox("bob").isEmpty())
        assertEquals(id, service.getInbox("alice").single().conversationId)
    }

    @Test
    fun `non participants and leavers cannot send`() {
        val (service, id) = exampleConversation()
        assertFailsWith<IllegalArgumentException> { service.sendMessage(id, "eve", "hi") }
        service.removeParticipant(id, "bob")
        assertFailsWith<IllegalArgumentException> { service.sendMessage(id, "bob", "hi") }
        service.sendMessage(id, "alice", "after bob left")
        assertTrue(service.getInbox("bob").isEmpty())
        assertFailsWith<IllegalArgumentException> { service.removeParticipant(id, "cust-42") }
    }

    @Test
    fun `internal notes are hidden from the customer`() {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        service.addParticipant(id, "alice")
        service.sendMessage(id, "cust-1", "Where is my refund?")
        service.addNote(id, "alice", "Approved internally")
        service.sendMessage(id, "alice", "On its way")
        assertEquals(listOf(1, 3), service.getMessages(id, 10, viewerId = "cust-1").map { it.sequence })
        assertEquals(listOf(3), service.getMessages(id, 1, viewerId = "cust-1").map { it.sequence })
        assertEquals(listOf(1, 2, 3), service.getMessages(id, 10).map { it.sequence })
        assertEquals(listOf(3), service.getInbox("cust-1").map { it.sequence })
        assertFailsWith<IllegalArgumentException> { service.addNote(id, "cust-1", "x") }
    }

    @Test
    fun `history pagination walks backwards with a cursor`() {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        (1..6).forEach { service.sendMessage(id, "cust-1", "m$it") }

        val first = service.getHistory(id, 4)
        assertEquals(listOf(3, 4, 5, 6), first.messages.map { it.sequence })
        val second = service.getHistory(id, 4, first.nextCursor)
        assertEquals(listOf(1, 2), second.messages.map { it.sequence })
        assertNull(second.nextCursor)
    }

    @Test
    fun `adding a participant is idempotent and validates ids`() {
        val service = ConversationService()
        val id = service.createConversation("cust-1")
        service.addParticipant(id, "alice")
        service.addParticipant(id, "alice")
        assertEquals(listOf("cust-1", FIN_ID, "alice"), service.participants(id))
        assertFailsWith<IllegalArgumentException> { service.addParticipant(id, "cust-1") }
        assertFailsWith<IllegalArgumentException> { service.addParticipant("missing", "bob") }
    }
}
