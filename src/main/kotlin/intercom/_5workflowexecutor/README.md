# Problem 5 – Multi-step workflow executor

> **Priority: MEDIUM (predicted).** Mirrors Fin "Procedures"/Tasks: an AI agent runs a multi-step
> workflow such as a refund. Tests graphs (topological sort), failure handling and real-world
> reliability thinking (retries + idempotency).

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeleton + runnable examples. |
| [`solution/Solution.kt`](solution/Solution.kt) | Kahn's algorithm, skip-on-failure, retries, idempotency keys, fake gateway. |
| `src/test/kotlin/intercom/_5workflowexecutor/` | Unit tests. |

```bash
./gradlew -q runMain -PmainClass=intercom._5workflowexecutor.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._5workflowexecutor.solution.SolutionKt
```

---

## 1. Prompt

> Fin can resolve a refund request by running several steps:
> retrieve customer → retrieve order → check eligibility → request refund → notify customer → resolve conversation.
> Steps declare their dependencies. Build an executor that runs them in a valid order and reports what happened.

```kotlin
data class Step(val id: String, val dependsOn: List<String>, val action: () -> Unit)
class WorkflowExecutor { fun execute(steps: List<Step>): Boolean }
```

### Example

```text
Retrieve order      SUCCESS
Check eligibility   SUCCESS
Request refund      FAILED        (provider declined)
Notify customer     NOT EXECUTED  (depends on a failed step)
execute(...) -> false
```

---

## 2. Suggested timeline (55 min)

| Time | Phase |
|------|-------|
| 0–5 | Clarify: what's failure, partial success, cycles, retries, parallelism? |
| 5–10 | Model the DAG; choose Kahn's algorithm (gives cycle detection for free). |
| 10–25 | Topological order + validation. |
| 25–35 | Execution with statuses and skip-on-failure. Run the example. |
| 35–45 | Retries for transient failures. |
| 45–55 | Idempotency (double refund!), persistence/resume, parallel execution. |

---

## 3. Clarifying questions

1. Can the dependency graph branch (DAG) or is it always a chain? *(DAG.)*
2. What should happen on a cycle or unknown dependency – fail at validation or at runtime? *(Validation, before any side effect.)*
3. If a step fails, do independent branches keep running? *(Yes; only dependents are skipped.)*
4. Which failures are retryable? Max attempts? Backoff? *(Timeouts/5xx; 3 attempts.)*
5. Do steps have side effects (refunds, emails)? *(Yes → idempotency matters.)*
6. Can steps run in parallel? *(Sequential first; parallel as a follow-up.)*
7. What does `execute` return – Boolean, or per-step statuses? *(Both: Boolean + status map.)*

---

## 4. Progressive hints

1. Build `inDegree[step]` and `dependents[step]`. Steps with in-degree 0 are ready.
2. Kahn: pop ready → append to order → decrement dependents → push those reaching 0.
3. If `order.size < steps.size` there's a **cycle**.
4. While executing, a step runs only if **all** dependencies are `SUCCESS`; otherwise mark `NOT_EXECUTED`. Because you go in topological order, dependency statuses are already final.
5. Distinguish `TransientStepException` (retry) from other exceptions (fail fast).
6. Give every attempt of a step the **same idempotency key** (`runId:stepId`) and pass it to the payment provider. If the first attempt actually refunded but timed out, the retry returns the same refund instead of creating a second one.

---

## 5. Complexity

- Validation + topological sort: **O(V + E)** time, O(V + E) space.
- Execution: O(V + E) bookkeeping + Σ(action cost × attempts).

---

## 6. Follow-ups

| Follow-up | Strong answer |
|-----------|---------------|
| Retries | Only transient errors; exponential backoff + jitter; cap attempts; record attempts per step. |
| Idempotency | Stable key per (run, step); downstream dedupes (Stripe-style). Also make our own steps idempotent (check "already refunded?"). |
| Crash mid-workflow / resume | Persist step statuses after each step (DB / durable workflow engine such as Temporal). On restart, skip `SUCCESS` steps; retry `PENDING`. Idempotency keys make re-running safe. |
| Compensation (saga) | If `notify_customer` fails after a refund, don't undo; if a later required step fails, run compensating actions in reverse order. |
| Parallel execution | Submit all ready steps to an executor; when a step completes, decrement dependents; join at the end. Kahn's ready set *is* the parallel frontier. |
| Timeouts | Per-step timeout → treat as transient. |
| Conditional steps | Steps with a predicate on context (e.g. only refund if amount < £100, else escalate – Problem 4). |
| Passing data between steps | Shared `context` map or typed outputs (`retrieve_order` produces `Order`). |
| Observability | Per-step status, duration, attempts, error → shown to the teammate in the inbox. |

---

## 7. Self-review checklist

- [ ] Example statuses match exactly (SUCCESS, SUCCESS, FAILED, NOT_EXECUTED).
- [ ] Cycle and unknown dependency rejected before any step runs.
- [ ] Independent branch still runs after a failure.
- [ ] Retry happens only for transient failures; refund happens once.
