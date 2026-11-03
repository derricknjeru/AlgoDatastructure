# Problem 3 – Conversation activity analytics

> **Priority: HIGH.** Intercom-flavoured version of **LeetCode 1348 – Tweet Counts Per Frequency**.
> Tests time bucketing, off-by-one discipline and choosing a sorted structure.

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeleton + runnable examples. |
| [`solution/Solution.kt`](solution/Solution.kt) | A) `TreeMap` + `subMap`; B) sorted list + binary search. |
| `src/test/kotlin/intercom/_3conversationanalytics/` | Unit tests. |

```bash
./gradlew -q runMain -PmainClass=intercom._3conversationanalytics.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._3conversationanalytics.solution.SolutionKt
```

---

## 1. Prompt

> We record an event every time something happens in a conversation (message, assignment, reply…).
> Build a service that can answer: *"how many events happened per minute / hour / day between two times?"*

```kotlin
class ConversationAnalytics {
    fun recordEvent(conversationId: String, timestamp: Int)          // seconds
    fun getEventCounts(frequency: String, startTime: Int, endTime: Int): List<Int>  // "minute" | "hour" | "day"
}
```

Chunks are `[start, start+size)`, `[start+size, start+2·size)`, … and the **last chunk is clipped to
`endTime` (inclusive)**. Number of chunks = `(endTime - startTime) / size + 1`.

### Examples

```text
events at 0, 60, 120, 180
getEventCounts("minute", 0, 180)  -> [1, 1, 1, 1]
getEventCounts("hour",   0, 180)  -> [4]
getEventCounts("minute", 0, 59)   -> [1]
getEventCounts("minute", 30, 150) -> [1, 1, 0]     // [30,90) [90,150) [150,150]

LeetCode 1348: events 0, 60, 10 (out of order!)
getEventCounts("minute", 0, 59) -> [2]
getEventCounts("minute", 0, 60) -> [2, 1]
record 120; getEventCounts("hour", 0, 210) -> [4]
```

---

## 2. Suggested timeline (45 min)

| Time | Phase |
|------|-------|
| 0–5 | Clarify bucket boundaries (inclusive/exclusive), ordering, units. Write the examples. |
| 5–10 | Choose structure: sorted map/list → range query. |
| 10–25 | Implement + run examples. Watch the off-by-ones. |
| 25–30 | Edge cases: empty range, `start == end`, unknown frequency, duplicates. |
| 30–45 | Follow-ups: scale, per-customer, rolling window, retention. |

---

## 3. Clarifying questions

1. Timestamp units (seconds? ms?) and range? Can they be negative? *(Seconds, non-negative Int.)*
2. Are both `startTime` and `endTime` inclusive? *(Yes.)*
3. Are the chunks aligned to `startTime` or to wall-clock minutes? *(To `startTime`.)*
4. Can events arrive out of order? Duplicated timestamps? *(Yes, both.)*
5. Per-conversation counts or global? *(Global first; per-conversation is a follow-up.)*
6. Read-heavy or write-heavy? Volume? *(Drives TreeMap vs pre-aggregated buckets.)*

---

## 4. Progressive hints

1. If you store timestamps sorted, a range query is "find first ≥ start, walk until > end".
2. `TreeMap<Int, Int>` (timestamp → count) handles out-of-order inserts and duplicates; `subMap(start, true, end, true)` is the range.
3. Bucket index for an event is `(t - startTime) / size`. Allocate `IntArray((end - start) / size + 1)`.
4. Alternative: sorted `ArrayList` + `lowerBound`/`upperBound`: count in `[a, b]` = `upperBound(b) - lowerBound(a)`.
5. Rolling window = same range query with `(now - window, now]`.

---

## 5. Complexity

| Approach | record | query | Notes |
|----------|--------|-------|-------|
| `TreeMap` + `subMap` | O(log u) | O(log u + e + b) | out-of-order friendly |
| Sorted list + binary search | O(1) append / O(n) out-of-order | O(b · log n) | query cost independent of event volume |
| Pre-aggregated per-minute counters | O(1) | O(b · minutes per bucket) | bounded memory, loses sub-minute precision |

`u` distinct timestamps, `e` events in range, `b` buckets.

---

## 6. Follow-ups

| Follow-up | Strong answer |
|-----------|---------------|
| Out-of-order events | TreeMap is order-agnostic. For a list, binary-insert. In streaming: watermarks + allowed lateness. |
| Millions of events/sec | Don't store raw events: aggregate into per-minute counters (`HashMap<minute, count>`), roll minutes into hours/days. Stream (Kafka → Flink) into a time-series / OLAP store (ClickHouse, Druid). |
| Per-customer / per-team grouping | Map `groupKey → TreeMap`, or composite keys. Watch cardinality. |
| Rolling 24h window | `subMap(now - 86400, false, now, true)`; for O(1): ring buffer of 1440 per-minute counters + running sum. |
| Retention / memory | Evict with `headMap(cutoff).clear()`; downsample old data. |
| Concurrency | `ConcurrentSkipListMap` + `merge`, or `LongAdder` per bucket. |
| Percentiles (e.g. first response time) | t-digest / HDR histogram per bucket. |

---

## 7. Self-review checklist

- [ ] Number of buckets and last-bucket clipping are correct (`minute 30..150 -> [1,1,0]`).
- [ ] Out-of-order and duplicate timestamps counted.
- [ ] Invalid frequency rejected.
- [ ] Explained raw-events vs pre-aggregation trade-off.
