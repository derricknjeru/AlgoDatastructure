package intercom._3conversationanalytics.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.scenario
import java.util.TreeMap

/*
 * Problem 3 – Conversation activity analytics (LeetCode 1348 "Tweet Counts Per Frequency").
 *
 * getEventCounts splits [startTime, endTime] into chunks of 60 / 3600 / 86400 seconds
 * ([start, start+size), [start+size, start+2·size), …, last chunk clipped to endTime inclusive)
 * and counts events per chunk. Number of chunks = (endTime - startTime) / size + 1.
 */

enum class Frequency(val seconds: Int) {
    MINUTE(60), HOUR(3_600), DAY(86_400);

    companion object {
        fun parse(value: String): Frequency =
            entries.firstOrNull { it.name.equals(value.trim(), ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown frequency '$value' (expected minute|hour|day)")
    }
}

/**
 * Approach A – TreeMap<timestamp, count> (handles out-of-order events for free).
 *
 * - recordEvent: O(log u) where u = distinct timestamps
 * - getEventCounts: O(log u + e + b)  e = events in range, b = number of buckets
 * - countInWindow: O(log u + e)
 * - space: O(u) globally + O(u) per conversation
 */
class ConversationAnalytics {
    private val all = TreeMap<Int, Int>()
    private val byConversation = HashMap<String, TreeMap<Int, Int>>()

    fun recordEvent(conversationId: String, timestamp: Int) {
        all.increment(timestamp)
        byConversation.getOrPut(conversationId) { TreeMap() }.increment(timestamp)
    }

    /** [conversationId] = null aggregates all conversations. */
    fun getEventCounts(frequency: String, startTime: Int, endTime: Int, conversationId: String? = null): List<Int> {
        require(startTime <= endTime) { "startTime must be <= endTime" }
        val size = Frequency.parse(frequency).seconds
        val counts = IntArray((endTime - startTime) / size + 1)
        val events = if (conversationId == null) all else byConversation[conversationId]
        events?.subMap(startTime, true, endTime, true)?.forEach { (timestamp, count) ->
            counts[(timestamp - startTime) / size] += count
        }
        return counts.toList()
    }

    /** Rolling window, e.g. "events in the last 24h": counts timestamps in (now - windowSeconds, now]. */
    fun countInWindow(now: Int, windowSeconds: Int, conversationId: String? = null): Int {
        val events = (if (conversationId == null) all else byConversation[conversationId]) ?: return 0
        return events.subMap(now - windowSeconds, false, now, true).values.sum()
    }

    private fun TreeMap<Int, Int>.increment(key: Int) {
        merge(key, 1) { old, one -> old + one }
    }
}

/**
 * Approach B – sorted ArrayList + binary search (what LC 1348 solutions often do).
 * Great when events mostly arrive in order (append is O(1) amortised); out-of-order inserts cost O(n).
 *
 * - recordEvent: O(log n) search + O(n) worst-case shift
 * - getEventCounts: O(b · log n) – two binary searches per bucket, independent of event volume
 */
class SortedListAnalytics {
    private val timestamps = ArrayList<Int>()

    fun recordEvent(timestamp: Int) {
        if (timestamps.isEmpty() || timestamps.last() <= timestamp) {
            timestamps += timestamp
        } else {
            timestamps.add(upperBound(timestamp), timestamp)
        }
    }

    fun getEventCounts(frequency: String, startTime: Int, endTime: Int): List<Int> {
        require(startTime <= endTime) { "startTime must be <= endTime" }
        val size = Frequency.parse(frequency).seconds
        val buckets = (endTime - startTime) / size + 1
        return List(buckets) { i ->
            val from = startTime + i * size
            val to = minOf(from + size - 1, endTime)
            upperBound(to) - lowerBound(from)
        }
    }

    /** First index with value >= target. */
    private fun lowerBound(target: Int): Int {
        var low = 0
        var high = timestamps.size
        while (low < high) {
            val mid = (low + high) ushr 1
            if (timestamps[mid] < target) low = mid + 1 else high = mid
        }
        return low
    }

    /** First index with value > target. */
    private fun upperBound(target: Int): Int {
        var low = 0
        var high = timestamps.size
        while (low < high) {
            val mid = (low + high) ushr 1
            if (timestamps[mid] <= target) low = mid + 1 else high = mid
        }
        return low
    }
}

fun main() {
    scenario("Example – one event per minute") {
        val analytics = ConversationAnalytics()
        listOf(0, 60, 120, 180).forEach { analytics.recordEvent("conv-1", it) }
        expect("minute 0..180", analytics.getEventCounts("minute", 0, 180), listOf(1, 1, 1, 1))
        expect("hour 0..180", analytics.getEventCounts("hour", 0, 180), listOf(4))
        expect("minute 0..59", analytics.getEventCounts("minute", 0, 59), listOf(1))
        expect("minute 30..150", analytics.getEventCounts("minute", 30, 150), listOf(1, 1, 0))
    }

    scenario("LeetCode 1348 example") {
        val analytics = ConversationAnalytics()
        analytics.recordEvent("tweet3", 0)
        analytics.recordEvent("tweet3", 60)
        analytics.recordEvent("tweet3", 10)
        expect("minute 0..59", analytics.getEventCounts("minute", 0, 59), listOf(2))
        expect("minute 0..60", analytics.getEventCounts("minute", 0, 60), listOf(2, 1))
        analytics.recordEvent("tweet3", 120)
        expect("hour 0..210", analytics.getEventCounts("hour", 0, 210), listOf(4))
    }

    scenario("Out-of-order events + per-conversation counts") {
        val analytics = ConversationAnalytics()
        analytics.recordEvent("conv-1", 300)
        analytics.recordEvent("conv-2", 70)
        analytics.recordEvent("conv-1", 30)
        analytics.recordEvent("conv-2", 10)
        analytics.recordEvent("conv-1", 30)
        expect("all, minute 0..299", analytics.getEventCounts("minute", 0, 299), listOf(3, 1, 0, 0, 0))
        expect("conv-2, minute 0..119", analytics.getEventCounts("minute", 0, 119, "conv-2"), listOf(1, 1))
        expect("unknown conversation", analytics.getEventCounts("minute", 0, 119, "nope"), listOf(0, 0))
        expectThrows("bad frequency", IllegalArgumentException::class) { analytics.getEventCounts("week", 0, 10) }
    }

    scenario("Rolling 24h window") {
        val analytics = ConversationAnalytics()
        listOf(0, 3_600, 86_400, 90_000).forEach { analytics.recordEvent("conv-1", it) }
        expect("last 24h at t=90000 -> (3600, 90000]", analytics.countInWindow(90_000, 86_400), 2)
    }

    scenario("Approach B – sorted list + binary search gives the same answers") {
        val analytics = SortedListAnalytics()
        listOf(120, 0, 60, 10).forEach { analytics.recordEvent(it) }
        expect("minute 0..60", analytics.getEventCounts("minute", 0, 60), listOf(2, 1))
        expect("hour 0..210", analytics.getEventCounts("hour", 0, 210), listOf(4))
        expect("day 0..86400", analytics.getEventCounts("day", 0, 86_400), listOf(4, 0))
    }

    Harness.summary()
}
