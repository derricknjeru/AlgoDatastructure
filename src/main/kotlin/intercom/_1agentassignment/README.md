# Problem 1 – Assign conversations to support agents

> **Priority: VERY HIGH** – this exact exercise has been reported for Intercom Product Engineer II (Dublin).
> Format: 45–60 min pair-programming, any language, run your code. Correctness and clear trade-off talk
> matter more than clever data structures.

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeleton with `TODO()`s and runnable examples. Start here. |
| [`solution/Solution.kt`](solution/Solution.kt) | Reference: O(n) scan **and** TreeSet/heap version. |
| `src/test/kotlin/intercom/_1agentassignment/` | Unit tests for both solutions. |

```bash
./gradlew -q runMain -PmainClass=intercom._1agentassignment.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._1agentassignment.solution.SolutionKt
./gradlew test --tests 'intercom._1agentassignment.*'
```

---

## 1. Prompt (what the interviewer reads out)

> In this exercise you'll build a system that assigns conversations to customer support agents.
> Assign each new conversation to the agent with the **fewest active conversations**. If several agents
> are tied, pick the one who has been **waiting longest since their last assignment**. Each agent has a
> **limit** on concurrent conversations (default **2**). Agents at their limit can't receive more work.
> You should also be able to **preview** the next N assignments without changing anything.

```kotlin
class AssignmentSystem(agents: List<String>) {
    fun setLimit(agent: String, limit: Int)
    fun assign(conversationId: Int): String?          // null when nobody has capacity
    fun previewAssignments(count: Int): List<String>  // must not mutate state
    // Extension, asked later:
    fun closeConversation(conversationId: Int)
}
```

### Example (must reproduce exactly)

```text
agents = [Alice, Bob, Charlie]
setLimit(Bob, 4); setLimit(Charlie, 3)            // Alice keeps the default 2

previewAssignments(4)  -> [Alice, Bob, Charlie, Alice]
assign(101) -> Alice
assign(102) -> Bob
assign(103) -> Charlie
assign(104) -> Alice                               // Alice is now 2/2 -> full

previewAssignments(5)  -> [Bob, Charlie, Bob, Charlie, Bob]
```

Why the second preview stops at Bob: Bob 1→2, Charlie 1→2, Bob 2→3, Charlie 2→3 (**full**, limit 3),
Bob 3→4 (**full**, limit 4). Alice was already full.

### Extension example – `closeConversation`

```text
limits: Alice 2, Bob 3, Charlie 2
assign 101 -> Alice (t1), 102 -> Bob (t2), 103 -> Charlie (t3), 104 -> Alice (t4)
closeConversation(101)              // Alice 2 -> 1
assign(105) -> ?
```

**Answer: Bob.** After closing, *everyone* has 1 active conversation, so rule 1 is a three-way tie.
Rule 2 decides: last assignment times are Bob t2, Charlie t3, Alice t4. Bob has waited longest.
(A common mistake is to answer "Alice because she just freed up".)

---

## 2. Suggested timeline (55 min)

| Time | Phase | What good looks like |
|------|-------|----------------------|
| 0–5 | Clarify | Ask the questions below; restate the 3 rules; write the example as a test. |
| 5–10 | Design | Per agent: `active`, `limit`, `lastAssignedAt`, `order`. Logical clock. Say "start with an O(n) scan, optimise later". |
| 10–25 | Code core | `setLimit`, `assign`, `previewAssignments` (copy state then simulate). |
| 25–30 | Test | Run the example, then edge cases: all full, limit 0, preview doesn't mutate. |
| 30–40 | Extension | `closeConversation` + walk through the 105 example out loud. |
| 40–55 | Follow-ups | Scale (TreeSet/heap), lowering limits, offline agents, skills routing, concurrency. |

---

## 3. Clarifying questions you should ask

1. How is "waiting longest" measured – time since **last assignment**, or since they last **became free**? *(Reported answer: last assignment.)*
2. How do never-assigned agents compare to assigned ones? *(Never-assigned first, in list order.)*
3. Is "time" real wall-clock or can I use a logical counter? *(Logical counter is fine and deterministic.)*
4. What should `assign` do when nobody has capacity – return null, throw, or queue? *(null.)*
5. What if `setLimit` lowers a limit below an agent's current load? *(Keep existing work, assign nothing new.)*
6. Can the same conversation id be assigned twice? *(I make `assign` idempotent.)*
7. Unknown agent in `setLimit`? Duplicate agent names? *(Throw `IllegalArgumentException`.)*
8. Roughly how many agents/conversations? *(Drives scan vs heap.)*

---

## 4. Progressive hints

1. Store per-agent state: `active`, `limit`, `lastAssignedAt`. Use `-1` for "never".
2. Use a **logical clock** `++clock` on every assignment instead of `System.currentTimeMillis()` – ties become impossible and tests are deterministic.
3. The whole ordering is one comparator: `compareBy({ it.active }, { it.lastAssignedAt }, { it.order })`.
4. Preview = **deep-copy** the agent states (and the clock), then run the same `pickNext` loop on the copies.
5. To close a conversation you need `conversationId -> agent`. Keep that map in `assign`.
6. Faster: keep only agents **with capacity** in a `TreeSet` ordered by the comparator. Every mutation is *remove → mutate → re-add* (never mutate a key while it's inside a sorted set!).

---

## 5. Reference approach & complexity

| Operation | Scan (`AssignmentSystem`) | TreeSet (`TreeSetAssignmentSystem`) |
|-----------|---------------------------|-------------------------------------|
| `assign` | O(n) | O(log n) |
| `previewAssignments(k)` | O(n + k·n) | O(n log n + k log n) |
| `closeConversation` / `setLimit` | O(1) | O(log n) |
| Space | O(n + c) | O(n + c) |

Say this out loud: *"With ~hundreds of agents per team the scan is fast and far easier to get right; I'd
only switch to the ordered set if profiling showed `assign` was hot."*

TreeSet pitfall: the comparator **must** be total (include `order`) or two agents with equal `active` and
`lastAssignedAt` would be treated as duplicates and silently dropped.

---

## 6. Follow-up questions the interviewer may ask

| Follow-up | Strong answer |
|-----------|---------------|
| Lower a limit below current load? | Don't revoke conversations; agent is skipped until `active < limit`. (Tested in the solution.) |
| Remove an agent / agent goes offline? | Offline: skip in selection (`setOnline` in solution). Removed: return their open conversations so they can be re-queued and re-assigned. |
| No capacity – what happens to the conversation? | Put it in a FIFO **unassigned queue**; on `closeConversation`/`setLimit`/agent online, drain the queue. |
| Skills / language / team routing | Filter candidates by predicate first (`agent.skills ⊇ conversation.requires`), then apply the same comparator. Separate pools per team. |
| Priority conversations (VIP, SLA breach) | Priority queue of pending conversations; agents still chosen with the same rules. |
| Weighted load (a chat ≠ an email) | `active` becomes a sum of weights; capacity check is `load + weight <= limit`. |
| Concurrency (many assign calls at once) | Single-writer per team (actor/queue), or `@Synchronized` methods; in a DB, `SELECT … FOR UPDATE SKIP LOCKED` or an atomic conditional update. Avoid double-assigning with idempotency on conversation id. |
| Fairness over a day, not just "now" | Track total assigned today; add as another comparator key. |
| Persistence / restart | State lives in DB; the logical clock becomes a monotonic sequence or `assigned_at` timestamp. |
| How would you test it? | Example-based tests + property tests: preview(k) == the next k assigns; nobody ever exceeds their limit. |

---

## 7. Self-review checklist

- [ ] Reproduced both examples exactly, including `assign(105) -> Bob`.
- [ ] Preview does not mutate (call it twice, same answer; then `assign` still matches).
- [ ] `assign` returns `null` and preview stops early when everyone is full.
- [ ] Limit 0 / offline / unknown agent handled.
- [ ] Explained complexity and when you'd switch to the TreeSet.
