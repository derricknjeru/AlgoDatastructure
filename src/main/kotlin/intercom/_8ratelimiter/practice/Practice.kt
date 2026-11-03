package intercom._8ratelimiter.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario

/*
 * Problem 8 – Rate limiter. Read ../README.md first.
 *
 * Implement allow(clientId, timestampMillis) for each strategy. Limits are per client.
 * Start with whichever you can explain best (token bucket or sliding log), then compare.
 */

interface RateLimiter {
    fun allow(clientId: String, timestampMillis: Long): Boolean
}

/** At most [maxRequests] in each window [k·windowMillis, (k+1)·windowMillis). */
class FixedWindowRateLimiter(private val maxRequests: Int, private val windowMillis: Long) : RateLimiter {
    override fun allow(clientId: String, timestampMillis: Long): Boolean = TODO("fixed window")
}

/** At most [maxRequests] in any window (now - windowMillis, now]. */
class SlidingWindowLogRateLimiter(private val maxRequests: Int, private val windowMillis: Long) : RateLimiter {
    override fun allow(clientId: String, timestampMillis: Long): Boolean = TODO("sliding window log")
}

/** Bucket starts full with [capacity] tokens, refills continuously; each request costs 1 token. */
class TokenBucketRateLimiter(private val capacity: Int, private val refillTokensPerSecond: Double) : RateLimiter {
    override fun allow(clientId: String, timestampMillis: Long): Boolean = TODO("token bucket")
}

private fun RateLimiter.replay(vararg timestamps: Long) = timestamps.map { allow("app-1", it) }

fun main() {
    scenario("Fixed window – 3 per 1000 ms") {
        val limiter = FixedWindowRateLimiter(3, 1_000)
        expect("t=0,100,200,300", limiter.replay(0, 100, 200, 300), listOf(true, true, true, false))
        expect("t=1000", limiter.replay(1_000), listOf(true))
        expect("other client", limiter.allow("app-2", 300), true)
    }

    scenario("Sliding window log – 3 per 1000 ms") {
        val limiter = SlidingWindowLogRateLimiter(3, 1_000)
        expect("t=0,100,200,300", limiter.replay(0, 100, 200, 300), listOf(true, true, true, false))
        expect("t=1000, 1050, 1100", limiter.replay(1_000, 1_050, 1_100), listOf(true, false, true))
    }

    scenario("Token bucket – capacity 3, 1 token/s") {
        val limiter = TokenBucketRateLimiter(3, 1.0)
        expect("t=0 x4", limiter.replay(0, 0, 0, 0), listOf(true, true, true, false))
        expect("t=500, 1000, 1000", limiter.replay(500, 1_000, 1_000), listOf(false, true, false))
    }

    Harness.summary()
}
