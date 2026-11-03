package intercom._8ratelimiter.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.concurrent.ConcurrentHashMap

/*
 * Problem 8 – Rate limiter (e.g. Intercom's public REST API: N requests per minute per app).
 *
 * Timestamps are passed in explicitly (milliseconds) so behaviour is deterministic and testable;
 * production code would read an injected Clock. Timestamps are assumed non-decreasing per client.
 *
 * Concurrency: one state object per client in a ConcurrentHashMap, mutated under its own monitor,
 * so different clients never contend with each other.
 */

interface RateLimiter {
    fun allow(clientId: String, timestampMillis: Long): Boolean
}

/**
 * Fixed window: count requests in [k·window, (k+1)·window).
 * O(1) time, O(1) space per client. Weakness: up to 2× the limit across a window boundary.
 */
class FixedWindowRateLimiter(private val maxRequests: Int, private val windowMillis: Long) : RateLimiter {
    private class Window(var start: Long, var count: Int)

    private val windows = ConcurrentHashMap<String, Window>()

    override fun allow(clientId: String, timestampMillis: Long): Boolean {
        val windowStart = timestampMillis - Math.floorMod(timestampMillis, windowMillis)
        val window = windows.computeIfAbsent(clientId) { Window(windowStart, 0) }
        synchronized(window) {
            if (window.start != windowStart) {
                window.start = windowStart
                window.count = 0
            }
            if (window.count >= maxRequests) return false
            window.count++
            return true
        }
    }
}

/**
 * Sliding window log: remember the timestamp of every accepted request in the last window.
 * Exact. O(1) amortised time, O(maxRequests) space per client.
 */
class SlidingWindowLogRateLimiter(private val maxRequests: Int, private val windowMillis: Long) : RateLimiter {
    private val logs = ConcurrentHashMap<String, ArrayDeque<Long>>()

    override fun allow(clientId: String, timestampMillis: Long): Boolean {
        val log = logs.computeIfAbsent(clientId) { ArrayDeque() }
        synchronized(log) {
            // Window is (now - window, now]: anything at or before now - window has expired.
            while (log.isNotEmpty() && log.first() <= timestampMillis - windowMillis) log.removeFirst()
            if (log.size >= maxRequests) return false
            log.addLast(timestampMillis)
            return true
        }
    }
}

/**
 * Sliding window counter: estimate = previous window count × overlap fraction + current count.
 * O(1) time and space; a smooth approximation of the sliding log.
 */
class SlidingWindowCounterRateLimiter(private val maxRequests: Int, private val windowMillis: Long) : RateLimiter {
    private class Counters(var windowStart: Long, var current: Int, var previous: Int)

    private val counters = ConcurrentHashMap<String, Counters>()

    override fun allow(clientId: String, timestampMillis: Long): Boolean {
        val windowStart = timestampMillis - Math.floorMod(timestampMillis, windowMillis)
        val c = counters.computeIfAbsent(clientId) { Counters(windowStart, 0, 0) }
        synchronized(c) {
            if (windowStart != c.windowStart) {
                c.previous = if (windowStart - c.windowStart == windowMillis) c.current else 0
                c.current = 0
                c.windowStart = windowStart
            }
            val elapsedFraction = (timestampMillis - windowStart).toDouble() / windowMillis
            val estimated = c.previous * (1 - elapsedFraction) + c.current
            if (estimated + 1 > maxRequests) return false
            c.current++
            return true
        }
    }
}

/**
 * Token bucket: holds up to `capacity` tokens, refilled continuously; each request costs one.
 * Allows bursts up to `capacity`, then a steady `refillTokensPerSecond`. O(1) time and space.
 */
class TokenBucketRateLimiter(private val capacity: Int, private val refillTokensPerSecond: Double) : RateLimiter {
    private class Bucket(var tokens: Double, var lastRefillMillis: Long)

    private val buckets = ConcurrentHashMap<String, Bucket>()

    override fun allow(clientId: String, timestampMillis: Long): Boolean {
        val bucket = buckets.computeIfAbsent(clientId) { Bucket(capacity.toDouble(), timestampMillis) }
        synchronized(bucket) {
            val elapsedMillis = timestampMillis - bucket.lastRefillMillis
            if (elapsedMillis > 0) {
                bucket.tokens = minOf(capacity.toDouble(), bucket.tokens + elapsedMillis / 1000.0 * refillTokensPerSecond)
                bucket.lastRefillMillis = timestampMillis
            }
            if (bucket.tokens < 1.0) return false
            bucket.tokens -= 1.0
            return true
        }
    }
}

private fun RateLimiter.replay(vararg timestamps: Long, clientId: String = "app-1") = timestamps.map { allow(clientId, it) }

fun main() {
    scenario("Fixed window – 3 requests per 1000 ms") {
        val limiter = FixedWindowRateLimiter(3, 1_000)
        expect("t=0,100,200,300", limiter.replay(0, 100, 200, 300), listOf(true, true, true, false))
        expect("t=1000 new window", limiter.replay(1_000), listOf(true))
        expect("other client unaffected", limiter.allow("app-2", 300), true)

        note("Boundary burst: 6 requests accepted within 103 ms")
        val burst = FixedWindowRateLimiter(3, 1_000)
        expect("t=997..1002", burst.replay(997, 998, 999, 1_000, 1_001, 1_002), List(6) { true })
    }

    scenario("Sliding window log – exact") {
        val limiter = SlidingWindowLogRateLimiter(3, 1_000)
        expect("t=0,100,200,300", limiter.replay(0, 100, 200, 300), listOf(true, true, true, false))
        expect("t=1000 (t=0 expired)", limiter.replay(1_000), listOf(true))
        expect("t=1050 (100,200,1000 in window)", limiter.replay(1_050), listOf(false))
        expect("t=1100 (t=100 expired)", limiter.replay(1_100), listOf(true))

        val burst = SlidingWindowLogRateLimiter(3, 1_000)
        expect("boundary burst blocked", burst.replay(997, 998, 999, 1_000, 1_001, 1_002), listOf(true, true, true, false, false, false))
    }

    scenario("Sliding window counter – weighted estimate") {
        val limiter = SlidingWindowCounterRateLimiter(3, 1_000)
        expect("t=0,100,200,300", limiter.replay(0, 100, 200, 300), listOf(true, true, true, false))
        note("t=1500: 3 × 0.5 + 0 = 1.5 -> allow; t=1600: 3 × 0.4 + 1 = 2.2 -> +1 > 3 deny; t=1800: 0.6 + 1 -> allow")
        expect("t=1500,1600,1800", limiter.replay(1_500, 1_600, 1_800), listOf(true, false, true))
    }

    scenario("Token bucket – capacity 3, 1 token/second") {
        val limiter = TokenBucketRateLimiter(3, 1.0)
        expect("burst of 4 at t=0", limiter.replay(0, 0, 0, 0), listOf(true, true, true, false))
        expect("t=500 (0.5 tokens)", limiter.replay(500), listOf(false))
        expect("t=1000 (1.0 token)", limiter.replay(1_000), listOf(true))
        expect("t=1000 again", limiter.replay(1_000), listOf(false))
        expect("t=5000 refilled but capped at 3", limiter.replay(5_000, 5_000, 5_000, 5_000), listOf(true, true, true, false))
    }

    Harness.summary()
}
