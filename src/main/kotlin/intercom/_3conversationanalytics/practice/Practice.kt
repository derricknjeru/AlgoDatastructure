package intercom._3conversationanalytics.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario

/*
 * Problem 3 – Conversation activity analytics (LeetCode 1348). Read ../README.md first.
 *
 *  - frequency is "minute" (60s), "hour" (3600s) or "day" (86400s).
 *  - Split [startTime, endTime] into chunks of that size; the last chunk is clipped to endTime.
 *  - Return the number of events in each chunk.
 *  - Events may arrive out of order.
 */
class ConversationAnalytics {

    fun recordEvent(conversationId: String, timestamp: Int) {
        TODO("recordEvent")
    }

    fun getEventCounts(frequency: String, startTime: Int, endTime: Int): List<Int> {
        TODO("getEventCounts")
    }

    // Follow-up: events in (now - windowSeconds, now]
    fun countInWindow(now: Int, windowSeconds: Int): Int {
        TODO("countInWindow (follow-up)")
    }
}

fun main() {
    scenario("Example – one event per minute") {
        val analytics = ConversationAnalytics()
        listOf(0, 60, 120, 180).forEach { analytics.recordEvent("conv-1", it) }
        expect("minute 0..180", analytics.getEventCounts("minute", 0, 180), listOf(1, 1, 1, 1))
        expect("hour 0..180", analytics.getEventCounts("hour", 0, 180), listOf(4))
        expect("minute 30..150", analytics.getEventCounts("minute", 30, 150), listOf(1, 1, 0))
    }

    scenario("LeetCode 1348 example (out of order)") {
        val analytics = ConversationAnalytics()
        analytics.recordEvent("tweet3", 0)
        analytics.recordEvent("tweet3", 60)
        analytics.recordEvent("tweet3", 10)
        expect("minute 0..59", analytics.getEventCounts("minute", 0, 59), listOf(2))
        expect("minute 0..60", analytics.getEventCounts("minute", 0, 60), listOf(2, 1))
        analytics.recordEvent("tweet3", 120)
        expect("hour 0..210", analytics.getEventCounts("hour", 0, 210), listOf(4))
    }

    scenario("Follow-up – rolling 24h window") {
        val analytics = ConversationAnalytics()
        listOf(0, 3_600, 86_400, 90_000).forEach { analytics.recordEvent("conv-1", it) }
        expect("countInWindow(90000, 86400)", analytics.countInWindow(90_000, 86_400), 2)
    }

    Harness.summary()
}
