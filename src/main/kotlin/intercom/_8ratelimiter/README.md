# Problem 8 – Rate limiter

> **Priority: MEDIUM** (user-listed *"Example 2: Implement a rate limiter"*). Intercom's public API limits
> requests per app per minute. Classic practical-coding question: implement one or two algorithms
> cleanly, then compare and scale.

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeletons for 3 strategies + runnable examples. |
| [`solution/Solution.kt`](solution/Solution.kt) | Fixed window, sliding log, sliding counter, token bucket (thread-safe per client). |
| `src/test/kotlin/intercom/_8ratelimiter/` | Unit tests, incl. concurrency. |

```bash
./gradlew -q runMain -PmainClass=intercom._8ratelimiter.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._8ratelimiter.solution.SolutionKt
```

---

## 1. Prompt

> Implement a rate limiter for our REST API. Each client may make at most **N requests per window**.
> `allow(clientId, timestamp)` returns whether the request should be served.

```kotlin
interface RateLimiter { fun allow(clientId: String, timestampMillis: Long): Boolean }
```

### Examples (3 requests / 1000 ms)

```text
Fixed window      t=0,100,200 ✓✓✓  t=300 ✗  t=1000 ✓ (new window)
                  t=997,998,999,1000,1001,1002 -> all ✓   (6 in ~5 ms: boundary burst!)

Sliding log       t=0,100,200 ✓✓✓  t=300 ✗  t=1000 ✓ (t=0 expired)  t=1050 ✗  t=1100 ✓
                  t=997,998,999 ✓✓✓  t=1000,1001,1002 ✗✗✗

Sliding counter   t=0,100,200 ✓✓✓  t=300 ✗  t=1500 ✓ (3×0.5+0)  t=1600 ✗ (3×0.4+1 = 2.2, +1 > 3)  t=1800 ✓

Token bucket (capacity 3, 1 token/s)
                  t=0 ×4 -> ✓✓✓✗   t=500 ✗ (0.5 tokens)   t=1000 ✓   t=1000 ✗   t=5000 ×4 -> ✓✓✓✗ (capped at 3)
```

---

## 2. Suggested timeline (50 min)

| Time | Phase |
|------|-------|
| 0–5 | Clarify: per what key, limit/window, burst tolerance, precision, single node or distributed. |
| 5–10 | Name the algorithms + trade-offs in 60 seconds; pick one. |
| 10–25 | Implement (token bucket or sliding log). Inject time. |
| 25–32 | Tests incl. boundaries (`t = window` exactly), multiple clients. |
| 32–40 | Second algorithm or thread safety. |
| 40–50 | Distributed design (Redis), headers, memory cleanup. |

---

## 3. Clarifying questions

1. Limit per **what**: API key, app, user, IP, endpoint?
2. Limit and window (e.g. 1000/min)? Do we allow bursts?
3. Must it be exact, or is approximate OK?
4. What do rejected requests return (429 + `Retry-After`)? Do they count towards the limit? *(No.)*
5. Single process or many API servers? *(Single first.)*
6. Can timestamps go backwards? *(Assume non-decreasing per client.)*

---

## 4. Progressive hints

1. Pass the time in (`timestampMillis` or an injected `Clock`) – never call `System.currentTimeMillis()` inside the algorithm; it makes tests impossible.
2. Fixed window: `windowStart = t - t % window`; reset the count when it changes.
3. Sliding log: `ArrayDeque` of accepted timestamps; pop from the front while `front <= t - window`.
4. Token bucket: store `tokens` and `lastRefill`; on each call add `elapsed × rate`, cap at capacity, spend 1.
5. Per-client state in `ConcurrentHashMap`; lock the client's state object, not the whole limiter.

---

## 5. Comparison

| Algorithm | Time | Memory / client | Accuracy | Bursts |
|-----------|------|-----------------|----------|--------|
| Fixed window | O(1) | O(1) | Up to 2× at boundaries | Boundary spike |
| Sliding window log | O(1) amortised | O(N) | Exact | None beyond N |
| Sliding window counter | O(1) | O(1) | Approximate (assumes uniform prev window) | Smooth |
| Token bucket | O(1) | O(1) | Exact for its model | Up to capacity, then steady rate |
| Leaky bucket (queue) | O(1) | O(queue) | Smooths output | None – constant outflow |

Typical production choice: **token bucket** (Stripe, AWS) or **sliding window counter** (Cloudflare).

---

## 6. Follow-ups

| Follow-up | Strong answer |
|-----------|---------------|
| Many API servers | Central store: Redis. Fixed window: `INCR key; EXPIRE` on first hit. Sliding log: sorted set `ZREMRANGEBYSCORE` + `ZCARD` + `ZADD`. Token bucket: **Lua script** for atomic read-modify-write. |
| Redis latency / outage | Local token bucket with periodic sync (approximate); fail-open vs fail-closed decision per endpoint. |
| Memory for millions of clients | Evict idle clients (TTL / LRU); counters instead of logs. |
| Different limits per plan / endpoint | Look up a policy by (app, plan, endpoint); multiple limiters (per-second burst + per-minute). |
| Client experience | `429 Too Many Requests` + `Retry-After`, `X-RateLimit-Limit/Remaining/Reset` headers. |
| Weighted requests | `allow(client, t, cost)`: spend `cost` tokens (expensive search = 5). |
| Concurrency | Per-client monitor (as in solution) or `ConcurrentHashMap.compute`; lock-free with `AtomicLong` + CAS for fixed window. |
| Clock skew across servers | Use Redis server time (`TIME`) inside the Lua script. |

---

## 7. Self-review checklist

- [ ] Boundary semantics explicit (`t - window` expired or not).
- [ ] Rejected requests don't consume capacity.
- [ ] Per-client isolation tested.
- [ ] Can explain the fixed-window boundary spike and how sliding/token bucket fix it.
