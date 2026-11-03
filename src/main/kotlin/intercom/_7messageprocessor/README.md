# Problem 7 – Real-time message processor

> **Priority: MEDIUM-LOW (predicted).** Also the user-listed *"Example 3: Process incoming customer
> messages"*. Messages arrive via webhooks from many channels: out of order, sometimes twice.

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeleton + runnable examples. |
| [`solution/Solution.kt`](solution/Solution.kt) | Thread-safe: `ConcurrentHashMap` + `ConcurrentSkipListSet`, dedupe, latest-N, cursor pages. |
| `src/test/kotlin/intercom/_7messageprocessor/` | Unit tests, incl. a concurrent redelivery test. |

```bash
./gradlew -q runMain -PmainClass=intercom._7messageprocessor.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._7messageprocessor.solution.SolutionKt
```

---

## 1. Prompt

> We receive customer messages from email, WhatsApp, SMS… through webhooks. Messages can arrive **out
> of order** and webhooks can be **redelivered**. Store them so a conversation always shows messages
> in the right order, exactly once.

```kotlin
data class IncomingMessage(val id: String, val conversationId: String, val timestamp: Long, val content: String)

class MessageProcessor {
    fun receive(message: IncomingMessage)
    fun getConversationMessages(conversationId: String): List<IncomingMessage>
}
```

### Example

```text
receive(m3 @300), receive(m1 @100), receive(m2 @200)
getConversationMessages(c1) -> [m1, m2, m3]

receive(m1 @100) again      -> ignored (returns false)

receive(m5 @400), receive(m4 @400)    // same timestamp
getConversationMessages(c1) -> [..., m4, m5]   (tie-break by id)

getLatestMessages(c1, 2)    -> [m4, m5]
getPage(c1, 2)              -> [m1, m2], cursor=(200, m2)
getPage(c1, 2, cursor)      -> [m3, m4], cursor=(400, m4)
getPage(c1, 2, cursor)      -> [m5],     cursor=null
```

---

## 2. Suggested timeline (45 min)

| Time | Phase |
|------|-------|
| 0–5 | Clarify ordering key, duplicate semantics, scope of ids, read patterns. |
| 5–20 | `HashSet` for seen ids + `Map<conversationId, TreeSet>` with `(timestamp, id)` comparator. |
| 20–28 | Run examples, edge cases (same timestamp, unknown conversation). |
| 28–38 | Latest N, pagination. |
| 38–45 | Concurrency, memory, distributed design. |

---

## 3. Clarifying questions

1. Order by the sender's timestamp or by arrival time? *(Sender timestamp; tie-break by id.)*
2. Are ids globally unique or per conversation? *(Global.)*
3. Same id, different content – duplicate or edit? *(Duplicate here; edits are a follow-up.)*
4. How long must we remember ids for dedupe? *(Forever in memory now; TTL later.)*
5. Read pattern: whole conversation, latest N, or pages?
6. Concurrent webhooks? *(Yes – handlers run in parallel.)*

---

## 4. Progressive hints

1. Dedupe: `if (!seenIds.add(id)) return false` – `add` is check-and-insert in one call.
2. Keep each conversation in a **sorted set** with comparator `(timestamp, id)` – inserts in any order, reads sorted.
3. Don't use only `timestamp` as the key: two messages in the same millisecond would collide and one would be lost.
4. Latest N: iterate the sorted set **descending**, take N, reverse.
5. Pagination: cursor = `(timestamp, id)` of the last item; next page = `tailSet(cursor, exclusive)`.
6. Thread safety: `ConcurrentHashMap.newKeySet()` + `computeIfAbsent` + `ConcurrentSkipListSet`.

---

## 5. Complexity

| Operation | Time |
|-----------|------|
| `receive` | O(log n) |
| `getConversationMessages` | O(n) |
| `getLatestMessages(k)` | O(log n + k) |
| `getPage(size)` | O(log n + size) |
| Space | O(total messages + ids) |

Alternative: append to a list and sort on read – O(1) write, O(n log n) read. Good if reads are rare.

---

## 6. Follow-ups

| Follow-up | Strong answer |
|-----------|---------------|
| Late arrival after a page was served | Event-time order ≠ arrival order. For stable pagination, paginate by a server-assigned **ingestion sequence**, and display by event time; or push "inserted earlier" updates to clients. |
| Unbounded `seenIds` | TTL cache (e.g. 24h – webhook providers retry within a window), Bloom filter + DB unique constraint. |
| Distributed | Partition by `conversationId` (Kafka key) so one consumer owns a conversation's order; DB unique index on message id for idempotent upserts. |
| Edits / deletes | Version field; upsert if newer version; tombstones for deletes. |
| Clock skew across channels | Trust provider timestamps within a tolerance; fall back to arrival time; or hybrid logical clocks. |
| Back-pressure / spikes | Ack webhook fast after durable enqueue; process asynchronously. |
| Memory | Keep only recent messages hot; older in storage with pagination. |

---

## 7. Self-review checklist

- [ ] Out-of-order input returns sorted output.
- [ ] Duplicate delivery ignored and reported (`false`).
- [ ] Same-timestamp messages both kept, ordered by id.
- [ ] Explained thread-safety and the late-arrival pagination caveat.
