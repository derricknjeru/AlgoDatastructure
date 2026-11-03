package intercom._8ratelimiter

import intercom._8ratelimiter.solution.FixedWindowRateLimiter
import intercom._8ratelimiter.solution.RateLimiter
import intercom._8ratelimiter.solution.SlidingWindowCounterRateLimiter
import intercom._8ratelimiter.solution.SlidingWindowLogRateLimiter
import intercom._8ratelimiter.solution.TokenBucketRateLimiter
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RateLimiterTest {

    private fun RateLimiter.replay(vararg timestamps: Long, client: String = "c") = timestamps.map { allow(client, it) }

    @Test
    fun `fixed window resets each window and allows boundary bursts`() {
        val limiter = FixedWindowRateLimiter(3, 1_000)
        assertEquals(listOf(true, true, true, false, true), limiter.replay(0, 100, 200, 300, 1_000))
        assertEquals(List(6) { true }, FixedWindowRateLimiter(3, 1_000).replay(997, 998, 999, 1_000, 1_001, 1_002))
    }

    @Test
    fun `sliding log is exact`() {
        val limiter = SlidingWindowLogRateLimiter(3, 1_000)
        assertEquals(
            listOf(true, true, true, false, true, false, true),
            limiter.replay(0, 100, 200, 300, 1_000, 1_050, 1_100),
        )
        assertEquals(
            listOf(true, true, true, false, false, false),
            SlidingWindowLogRateLimiter(3, 1_000).replay(997, 998, 999, 1_000, 1_001, 1_002),
        )
    }

    @Test
    fun `sliding counter weights the previous window`() {
        val limiter = SlidingWindowCounterRateLimiter(3, 1_000)
        assertEquals(listOf(true, true, true, false), limiter.replay(0, 100, 200, 300))
        assertEquals(listOf(true, false, true), limiter.replay(1_500, 1_600, 1_800))
        // Two windows later the old window no longer counts.
        assertEquals(listOf(true, true, true, false), SlidingWindowCounterRateLimiter(3, 1_000).replay(0, 0, 0, 0))
        val idle = SlidingWindowCounterRateLimiter(3, 1_000)
        idle.replay(0, 0, 0)
        assertEquals(listOf(true, true, true), idle.replay(2_000, 2_000, 2_000))
    }

    @Test
    fun `token bucket bursts then refills`() {
        val limiter = TokenBucketRateLimiter(3, 1.0)
        assertEquals(listOf(true, true, true, false), limiter.replay(0, 0, 0, 0))
        assertEquals(listOf(false, true, false), limiter.replay(500, 1_000, 1_000))
        assertEquals(listOf(true, true, true, false), limiter.replay(5_000, 5_000, 5_000, 5_000))
    }

    @Test
    fun `clients are isolated`() {
        val limiters = listOf(
            FixedWindowRateLimiter(1, 1_000),
            SlidingWindowLogRateLimiter(1, 1_000),
            SlidingWindowCounterRateLimiter(1, 1_000),
            TokenBucketRateLimiter(1, 1.0),
        )
        for (limiter in limiters) {
            assertTrue(limiter.allow("a", 0))
            assertTrue(limiter.allow("b", 0))
            assertEquals(false, limiter.allow("a", 1))
        }
    }

    @Test
    fun `concurrent requests never exceed the limit`() {
        val limiters = listOf(
            FixedWindowRateLimiter(100, 60_000),
            SlidingWindowLogRateLimiter(100, 60_000),
            SlidingWindowCounterRateLimiter(100, 60_000),
            TokenBucketRateLimiter(100, 0.0),
        )
        for (limiter in limiters) {
            val allowed = AtomicInteger()
            val pool = Executors.newFixedThreadPool(8)
            repeat(1_000) { pool.execute { if (limiter.allow("hot", 10)) allowed.incrementAndGet() } }
            pool.shutdown()
            assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS))
            assertEquals(100, allowed.get(), limiter::class.simpleName)
        }
    }
}
