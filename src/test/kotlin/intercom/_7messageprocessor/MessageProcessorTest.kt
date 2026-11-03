package intercom._7messageprocessor

import intercom._7messageprocessor.solution.IncomingMessage
import intercom._7messageprocessor.solution.MessageProcessor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MessageProcessorTest {

    private fun msg(id: String, t: Long, conversation: String = "c1") = IncomingMessage(id, conversation, t, "content $id")

    @Test
    fun `sorts out of order messages and dedupes`() {
        val processor = MessageProcessor()
        assertTrue(processor.receive(msg("m3", 300)))
        assertTrue(processor.receive(msg("m1", 100)))
        assertTrue(processor.receive(msg("m2", 200)))
        assertFalse(processor.receive(msg("m1", 100)))
        assertEquals(listOf("m1", "m2", "m3"), processor.getConversationMessages("c1").map { it.id })
    }

    @Test
    fun `same timestamp keeps both ordered by id`() {
        val processor = MessageProcessor()
        processor.receive(msg("b", 10))
        processor.receive(msg("a", 10))
        assertEquals(listOf("a", "b"), processor.getConversationMessages("c1").map { it.id })
    }

    @Test
    fun `conversations are isolated`() {
        val processor = MessageProcessor()
        processor.receive(msg("m1", 10, "c1"))
        processor.receive(msg("x1", 5, "c2"))
        assertEquals(listOf("m1"), processor.getConversationMessages("c1").map { it.id })
        assertEquals(listOf("x1"), processor.getConversationMessages("c2").map { it.id })
        assertTrue(processor.getConversationMessages("nope").isEmpty())
    }

    @Test
    fun `latest n and pagination`() {
        val processor = MessageProcessor()
        (5 downTo 1).forEach { processor.receive(msg("m$it", it * 100L)) }
        assertEquals(listOf("m4", "m5"), processor.getLatestMessages("c1", 2).map { it.id })
        assertEquals(5, processor.getLatestMessages("c1", 50).size)

        val seen = mutableListOf<String>()
        var page = processor.getPage("c1", 2)
        seen += page.messages.map { it.id }
        while (page.nextCursor != null) {
            page = processor.getPage("c1", 2, page.nextCursor)
            seen += page.messages.map { it.id }
        }
        assertEquals(listOf("m1", "m2", "m3", "m4", "m5"), seen)
        assertNull(processor.getPage("c1", 5).nextCursor)
    }

    @Test
    fun `concurrent redeliveries are stored exactly once`() {
        val processor = MessageProcessor()
        val accepted = AtomicInteger()
        val pool = Executors.newFixedThreadPool(8)
        repeat(8) {
            pool.execute {
                (1..500).forEach { i -> if (processor.receive(msg("m$i", i.toLong()))) accepted.incrementAndGet() }
            }
        }
        pool.shutdown()
        assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS))
        assertEquals(500, accepted.get())
        assertEquals((1..500).map { "m$it" }, processor.getConversationMessages("c1").map { it.id })
    }
}
