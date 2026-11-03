package intercom._3conversationanalytics

import intercom._3conversationanalytics.solution.ConversationAnalytics
import intercom._3conversationanalytics.solution.SortedListAnalytics
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ConversationAnalyticsTest {

    @Test
    fun `one event per minute`() {
        val analytics = ConversationAnalytics()
        listOf(0, 60, 120, 180).forEach { analytics.recordEvent("conv-1", it) }
        assertEquals(listOf(1, 1, 1, 1), analytics.getEventCounts("minute", 0, 180))
        assertEquals(listOf(4), analytics.getEventCounts("hour", 0, 180))
        assertEquals(listOf(1, 1, 0), analytics.getEventCounts("minute", 30, 150))
    }

    @Test
    fun `leetcode 1348 example`() {
        val analytics = ConversationAnalytics()
        listOf(0, 60, 10).forEach { analytics.recordEvent("tweet3", it) }
        assertEquals(listOf(2), analytics.getEventCounts("minute", 0, 59))
        assertEquals(listOf(2, 1), analytics.getEventCounts("minute", 0, 60))
        analytics.recordEvent("tweet3", 120)
        assertEquals(listOf(4), analytics.getEventCounts("hour", 0, 210))
    }

    @Test
    fun `per conversation counts and duplicates`() {
        val analytics = ConversationAnalytics()
        analytics.recordEvent("a", 5)
        analytics.recordEvent("a", 5)
        analytics.recordEvent("b", 5)
        assertEquals(listOf(2), analytics.getEventCounts("minute", 0, 59, "a"))
        assertEquals(listOf(3), analytics.getEventCounts("MINUTE", 0, 59))
        assertEquals(listOf(0), analytics.getEventCounts("minute", 0, 59, "missing"))
    }

    @Test
    fun `rolling window excludes the lower bound`() {
        val analytics = ConversationAnalytics()
        listOf(0, 3_600, 86_400, 90_000).forEach { analytics.recordEvent("c", it) }
        assertEquals(2, analytics.countInWindow(90_000, 86_400))
        assertEquals(2, analytics.countInWindow(86_400, 86_400)) // (0, 86400]
        assertEquals(3, analytics.countInWindow(86_400, 86_401)) // (-1, 86400]
    }

    @Test
    fun `invalid input is rejected`() {
        val analytics = ConversationAnalytics()
        assertFailsWith<IllegalArgumentException> { analytics.getEventCounts("week", 0, 1) }
        assertFailsWith<IllegalArgumentException> { analytics.getEventCounts("minute", 10, 0) }
    }

    @Test
    fun `both approaches agree on random data`() {
        val random = Random(1348)
        val treeMap = ConversationAnalytics()
        val sortedList = SortedListAnalytics()
        repeat(2_000) {
            val t = random.nextInt(0, 200_000)
            treeMap.recordEvent("c", t)
            sortedList.recordEvent(t)
        }
        for (frequency in listOf("minute", "hour", "day")) {
            repeat(20) {
                val start = random.nextInt(0, 150_000)
                val end = start + random.nextInt(0, 60_000)
                assertEquals(
                    treeMap.getEventCounts(frequency, start, end),
                    sortedList.getEventCounts(frequency, start, end),
                    "$frequency $start..$end",
                )
            }
        }
    }
}
