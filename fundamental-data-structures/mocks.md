# Timed Mock Interviews

Run each mock under real conditions: Kotlin only, no AI tools, no internet search for solutions, thinking out loud (record yourself if you can).

Solutions and a practice exercise for each mock are in [mock-solutions.md](mock-solutions.md). Read them only after your attempt.

## Timing Breakdown (both mocks)

Total: **35 minutes**

| Minutes | Phase | What to do |
|---------|-------|------------|
| 0 to 5 | Clarify requirements and examples | Restate the problem. Ask about input size, duplicates, empty input, and output format. Write two examples, including one edge case. |
| 5 to 10 | Explain approach | Name the data structure and why. Describe the algorithm in plain English. Mention the alternative you rejected. |
| 10 to 30 | Implement in Kotlin | Write clean, named code. Narrate each block. Stop at minute 30 even if unfinished and move to testing. |
| 30 to 35 | Test edge cases and state complexity | Trace one example by hand. Check empty input, one element, duplicates, and ties. State time and space. |

Timer rule: if you are still on the approach at minute 10, pick the simplest correct approach and go.

---

## Mock 1 (35 min): HashMap and Custom Sorting

### Prompt

You receive a list of strings, `words`, and an integer `k`. Return the `k` strings that appear most often in `words`. Sort the result by:

1. Higher frequency first.
2. For equal frequency, lexicographically smaller string first.

Also: return all distinct strings in `words` sorted by increasing frequency, breaking ties by decreasing lexicographic order.

**Constraints to clarify:** `1 <= words.size <= 10^5`, strings are lowercase letters, `1 <= k <= number of distinct words`.

**Interviewer follow-up (ask yourself after you finish):** Given a stream of words that you cannot store, how would you keep only the candidate top `k`?

### Self-Scoring Rubric (score 1 to 5 for each)

| Criterion | 1 (weak) | 3 (adequate) | 5 (strong) |
|-----------|----------|--------------|------------|
| Approach | Jumped into code with no plan | Plan stated, but not compared with alternatives | Plan stated, alternatives compared, trade-offs named |
| DS knowledge | Wrong or unjustified choices | Correct structures, weakly justified | Each structure justified and its operations' costs known |
| Communication | Silent or hard to follow | Narrates some steps | Narrates throughout, names each decision |
| Correctness | Does not run or fails examples | Passes examples, misses an edge case | Passes examples and edge cases, handles ties correctly |
| Complexity | Not stated or wrong | Stated, one part wrong | Time and space stated and justified |

**Total: ___ / 25**

Scoring guide: 20 to 25 ready. 15 to 19 review weak criteria and repeat in 2 days. Below 15 repeat the mock after reviewing Day 1.

---

## Mock 2 (35 min): Heap and Data-Structure Design

### Prompt

Design a class `RunningStats` that supports:

- `add(value: Int)`: add an integer to the stream.
- `median(): Double`: return the median of all values added so far.
- `topK(k: Int): List<Int>`: return the `k` largest values seen so far, in descending order.

Explain why you chose each data structure. Implement `add` and `median` first. Then implement `topK` if time remains.

**Constraints to clarify:** up to `10^5` calls total, values in the range `-10^9` to `10^9`, `k` may be larger than the number of values seen.

**Interviewer follow-up (ask yourself after you finish):** How would `add` change if values could be removed?

### Self-Scoring Rubric (score 1 to 5 for each)

| Criterion | 1 (weak) | 3 (adequate) | 5 (strong) |
|-----------|----------|--------------|------------|
| Approach | Jumped into code with no plan | Plan stated, but not compared with alternatives | Plan stated, alternatives compared, trade-offs named |
| DS knowledge | Wrong or unjustified choices | Correct structures, weakly justified | Each structure justified, heap invariants explained |
| Communication | Silent or hard to follow | Narrates some steps | Narrates throughout, names each decision |
| Correctness | Does not run or fails examples | Passes examples, misses an edge case | Passes examples and edge cases (empty, odd and even counts, k too large) |
| Complexity | Not stated or wrong | Stated, one part wrong | Time and space stated per method and justified |

**Total: ___ / 25**

Scoring guide: 20 to 25 ready. 15 to 19 review weak criteria and repeat in 2 days. Below 15 repeat the mock after reviewing Day 2.

---

## After Each Mock

1. Note the top two weakest criteria.
2. Write one sentence on what you would say differently next time.
3. Update the matching day file and [TRACKER.md](TRACKER.md) notes.
