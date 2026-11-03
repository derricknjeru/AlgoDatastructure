# Intercom – Product Engineer II (Dublin) mock interviews

Practice pack for Intercom's **practical coding / pair-programming** round (45–60 min, real code you run,
judged on clarity, correctness, tests and trade-off discussion – not puzzles). All code is Kotlin.

Each problem folder contains:

| File | What it is |
|------|------------|
| `README.md` | Mock-interview script: prompt, API, examples with expected output, timeline, clarifying questions, progressive hints, complexity, follow-ups, self-review checklist. |
| `practice/Practice.kt` | API skeleton with `TODO()` bodies and a `main` that runs the examples (prints `PASS` / `FAIL` / `TODO`). **Code here.** |
| `solution/Solution.kt` | Reference solution with complexity notes and a `main` that checks the examples. |
| `src/test/kotlin/intercom/<problem>/` | `kotlin.test` unit tests for the reference solution. |

`Harness.kt` is the tiny shared helper (`scenario`, `expect`, `expectThrows`, `summary`) used by every `main`.

---

## Problems by priority

| # | Problem | Priority | Core skills | Folder |
|---|---------|----------|-------------|--------|
| 1 | **Assign conversations to support agents** (limits, preview, close) | 🔴 **VERY HIGH – reported** | Modelling state, comparator tie-breaks, non-mutating simulation, TreeSet/heap | [`_1agentassignment`](_1agentassignment/README.md) |
| 2 | Conversation service (+ conversation history system) | 🟠 HIGH | Domain modelling, permissions, fan-out, cursor pagination | [`_2conversationservice`](_2conversationservice/README.md) |
| 3 | Conversation activity analytics (LeetCode 1348) | 🟠 HIGH | Time bucketing, TreeMap range queries, binary search | [`_3conversationanalytics`](_3conversationanalytics/README.md) |
| 4 | Human escalation engine | 🟡 MEDIUM (predicted) | State machine, rule precedence, idempotency | [`_4escalationengine`](_4escalationengine/README.md) |
| 5 | Multi-step workflow executor (refund flow) | 🟡 MEDIUM (predicted) | Topological sort (Kahn), cycle detection, retries, idempotency keys | [`_5workflowexecutor`](_5workflowexecutor/README.md) |
| 8 | Rate limiter | 🟡 MEDIUM (user-listed) | Fixed / sliding / token bucket, concurrency, Redis design | [`_8ratelimiter`](_8ratelimiter/README.md) |
| 6 | Knowledge article retrieval | 🟢 MEDIUM-LOW (predicted) | Filtering, scoring, inverted index, top-K heap | [`_6knowledgeretrieval`](_6knowledgeretrieval/README.md) |
| 7 | Real-time message processor | 🟢 MEDIUM-LOW (predicted) | Out-of-order + dedupe, sorted sets, pagination, thread-safety | [`_7messageprocessor`](_7messageprocessor/README.md) |

The other examples you listed map onto these: *Customer support ticket assignment* → **1**,
*Process incoming customer messages* → **7**, *Design a conversation history system* → **2** (pagination section).

---

## Suggested practice plan

| Day | Session (timebox 50 min each, then review the solution + README follow-ups) |
|-----|------------------------------------------------------------------------------|
| 1 | **#1 Agent assignment** – cold attempt. Then read the solution and redo it from scratch. |
| 2 | **#1 again** (aim < 30 min incl. `closeConversation`), then **#3 Analytics**. |
| 3 | **#2 Conversation service** + its history/pagination follow-up. |
| 4 | **#4 Escalation engine** and **#5 Workflow executor**. |
| 5 | **#8 Rate limiter** (token bucket + sliding log), then **#7 Message processor**. |
| 6 | **#6 Knowledge retrieval**. Then a full mock of **#1** with a friend reading the README as interviewer. |

How to run a mock:
1. Interviewer reads section **1 (Prompt)** of the README only.
2. Candidate asks clarifying questions (section **3** is the checklist), codes in `practice/Practice.kt`, runs it often.
3. Interviewer gives hints from section **4** only when stuck for > 3 min, in order.
4. Last 10–15 min: interviewer picks 2–3 rows from section **6 (Follow-ups)**.
5. Review against section **7 (Self-review checklist)** and the reference solution.

Interview habits that score well at Intercom: restate the problem, write the given example as a test
first, start simple (O(n) scan) and say when you'd optimise, run code early, name edge cases out loud,
and keep code readable (small functions, good names).

---

## How to run

From the repo root (`./gradlew` – no extra dependencies):

```bash
# Run your practice attempt for a problem
./gradlew -q runMain -PmainClass=intercom._1agentassignment.practice.PracticeKt

# Run the reference solution's examples
./gradlew -q runMain -PmainClass=intercom._1agentassignment.solution.SolutionKt

# Unit tests for all reference solutions (or one problem)
./gradlew test --tests 'intercom.*'
./gradlew test --tests 'intercom._1agentassignment.*'
```

Main classes follow the pattern `intercom.<folder>.practice.PracticeKt` / `intercom.<folder>.solution.SolutionKt`
for `_1agentassignment`, `_2conversationservice`, `_3conversationanalytics`, `_4escalationengine`,
`_5workflowexecutor`, `_6knowledgeretrieval`, `_7messageprocessor`, `_8ratelimiter`.

In IntelliJ you can also click the ▶ gutter icon next to any `fun main()`.

To reset a practice file, `git checkout -- src/main/kotlin/intercom/<folder>/practice/Practice.kt`.
