package intercom._7messageprocessor.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListSet

/*
 * Problem 7 – Real-time message processor (webhooks from email, WhatsApp, Messenger …).
 *
 *  - Messages can arrive out of order -> keep each conversation sorted by (timestamp, id).
 *  - Webhooks are redelivered -> dedupe by message id (atomic add to a concurrent set).
 *  - Identical timestamps -> tie-break by id so the order is total and deterministic.
 *  - Conversations are isolated -> one sorted set per conversation.
 *
 * Thread-safe without explicit locks: ConcurrentHashMap + ConcurrentSkipListSet.
 */

data class IncomingMessage(val id: String, val conversationId: String, val timestamp: Long, val content: String)

/** Position after which the next page starts (exclusive). */
data class Cursor(val timestamp: Long, val id: String)

data class Page(val messages: List<IncomingMessage>, val nextCursor: Cursor?)

/** Total order inside a conversation. Note: ids compare as strings ("m10" < "m9"). */
private val CHRONOLOGICAL: Comparator<IncomingMessage> =
    compareBy<IncomingMessage>({ it.timestamp }, { it.id })

/**
 * Let n = messages in the conversation.
 * - receive: O(log n)
 * - getConversationMessages: O(n)
 * - getLatestMessages(k): O(log n + k)
 * - getPage(size): O(log n + size)
 */
class MessageProcessor {
    private val seenIds: MutableSet<String> = ConcurrentHashMap.newKeySet()
    private val byConversation = ConcurrentHashMap<String, ConcurrentSkipListSet<IncomingMessage>>()

    /** Returns false for a duplicate delivery. */
    fun receive(message: IncomingMessage): Boolean {
        if (!seenIds.add(message.id)) return false
        byConversation.computeIfAbsent(message.conversationId) { ConcurrentSkipListSet(CHRONOLOGICAL) } += message
        return true
    }

    fun getConversationMessages(conversationId: String): List<IncomingMessage> =
        byConversation[conversationId]?.toList().orEmpty()

    /** Latest [n] messages, oldest first. */
    fun getLatestMessages(conversationId: String, n: Int): List<IncomingMessage> {
        require(n >= 0) { "n must be >= 0" }
        val messages = byConversation[conversationId] ?: return emptyList()
        return messages.descendingIterator().asSequence().take(n).toList().reversed()
    }

    /** Forward pagination (oldest -> newest). Pass the returned cursor to get the next page. */
    fun getPage(conversationId: String, pageSize: Int, after: Cursor? = null): Page {
        require(pageSize > 0) { "pageSize must be > 0" }
        val messages = byConversation[conversationId] ?: return Page(emptyList(), null)
        val tail = if (after == null) messages else messages.tailSet(probe(after), false)
        val window = tail.asSequence().take(pageSize + 1).toList()
        val page = window.take(pageSize)
        val nextCursor = if (window.size > pageSize) page.last().let { Cursor(it.timestamp, it.id) } else null
        return Page(page, nextCursor)
    }

    // The comparator only looks at timestamp and id, so a probe with blank fields is enough.
    private fun probe(cursor: Cursor) = IncomingMessage(cursor.id, "", cursor.timestamp, "")
}

private fun List<IncomingMessage>.ids() = map { it.id }

fun main() {
    scenario("Out-of-order delivery is sorted by timestamp") {
        val processor = MessageProcessor()
        processor.receive(IncomingMessage("m3", "c1", 300, "Any update?"))
        processor.receive(IncomingMessage("m1", "c1", 100, "Hi, my invoice is wrong"))
        processor.receive(IncomingMessage("m2", "c1", 200, "It shows 2 seats, we have 1"))
        expect("c1", processor.getConversationMessages("c1").ids(), listOf("m1", "m2", "m3"))
    }

    scenario("Webhook redelivery is deduplicated") {
        val processor = MessageProcessor()
        expect("first delivery", processor.receive(IncomingMessage("m1", "c1", 100, "Hi")), true)
        expect("redelivery", processor.receive(IncomingMessage("m1", "c1", 100, "Hi")), false)
        expect("count", processor.getConversationMessages("c1").size, 1)
    }

    scenario("Identical timestamps are ordered by id; conversations are isolated") {
        val processor = MessageProcessor()
        processor.receive(IncomingMessage("m5", "c1", 400, "second by id"))
        processor.receive(IncomingMessage("m4", "c1", 400, "first by id"))
        processor.receive(IncomingMessage("x1", "c2", 50, "other conversation"))
        expect("c1", processor.getConversationMessages("c1").ids(), listOf("m4", "m5"))
        expect("c2", processor.getConversationMessages("c2").ids(), listOf("x1"))
        expect("unknown", processor.getConversationMessages("c9"), emptyList<IncomingMessage>())
    }

    scenario("Latest N and cursor pagination") {
        val processor = MessageProcessor()
        listOf(300L to "m3", 100L to "m1", 500L to "m5", 200L to "m2", 400L to "m4")
            .forEach { (t, id) -> processor.receive(IncomingMessage(id, "c1", t, "msg $id")) }
        expect("latest 2", processor.getLatestMessages("c1", 2).ids(), listOf("m4", "m5"))

        val page1 = processor.getPage("c1", 2)
        expect("page 1", page1.messages.ids(), listOf("m1", "m2"))
        val page2 = processor.getPage("c1", 2, page1.nextCursor)
        expect("page 2", page2.messages.ids(), listOf("m3", "m4"))
        val page3 = processor.getPage("c1", 2, page2.nextCursor)
        expect("page 3", page3.messages.ids(), listOf("m5"))
        expect("page 3 cursor", page3.nextCursor, null)

        note("A late message older than the cursor won't appear in later pages -> see README follow-ups")
        processor.receive(IncomingMessage("m0", "c1", 50, "late arrival"))
        expect("full view includes it", processor.getConversationMessages("c1").first().id, "m0")
    }

    Harness.summary()
}
