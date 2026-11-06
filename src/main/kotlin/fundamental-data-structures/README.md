# 3-Day Kotlin Data Structures Interview Prep

## Goal

Prepare for a HackerRank-style live interview where you are assessed on:

1. **Approach**: how you break down a problem before coding.
2. **Data structure knowledge**: choosing and justifying maps, sets, lists, heaps, queues, stacks, and ordered collections.
3. **Communication**: thinking out loud while you work.
4. **Correctness and complexity**: working code, edge cases, and stated Big-O.

Rules for practice (mirror the real session):

- Solve in **Kotlin** only.
- Think out loud. Record yourself or talk to an empty room.
- **No AI tools** during timed practice.
- Respect the timers.

## Structure

| Day | Focus | File | Problems |
|-----|-------|------|----------|
| 1 | Maps, sets, sorting | [day-1-maps-sets-sorting.md](docs/day-1-maps-sets-sorting.md) | 9 |
| 2 | Heaps | [day-2-heaps.md](docs/day-2-heaps.md) | 6 |
| 3 | Ordered collections and design | [day-3-ordered-and-design.md](docs/day-3-ordered-and-design.md) | 5 |

Also:

- [Timed mocks](docs/mocks.md): two 35-minute mock interviews.
- [Mock solutions](docs/mock-solutions.md): review only after completing a timed attempt.
- [Progress tracker](docs/TRACKER.md): progress log for all 20 core problems.
- [Secondary problems](docs/secondary.md): optional problems once the core list is done.
- [Practice exercises](exercises/): Kotlin TODO starters.
- [Reference solutions](solutions/MockPracticeSolutions.kt): solutions for the Kotlin starters.

## Resources

- [HackerRank Interview Preparation Kit](https://www.hackerrank.com/interview/interview-preparation-kit?utm_source=chatgpt.com)
- [HackerRank Data Structures Practice](https://www.hackerrank.com/domains/data-structures?utm_source=chatgpt.com)

### Suggested daily schedule (about 3 hours)

- 10 min: review the cheat sheet below.
- 2 hours: solve the day's problems (roughly 12 to 15 minutes each, timed; stop and review if stuck past 20 minutes).
- 20 min: review solutions, rewrite one solution cleanly from memory.
- 10 min: update the [progress tracker](docs/TRACKER.md) and note what felt slow.

On Day 3 evening, run Mock 2 if you have not already done so.

## Emphasis (70/30)

- **About 70%**: maps, lists, sorting, heaps (frequency counting, grouping, top-K, running median, comparators).
- **About 30%**: ordered collections, stacks, queues, linked lists (LRU, intervals, bracket matching, design problems).

## Kotlin Collection Cheat Sheet

| Need | Kotlin type | Constructor / notes |
|------|-------------|---------------------|
| Growable list, random access | `MutableList<T>` / `ArrayList<T>` | `mutableListOf()`, `ArrayList<T>(capacity)` |
| Key to value lookup | `HashMap<K, V>` / `MutableMap<K, V>` | `hashMapOf()`, `getOrDefault`, `getOrPut`, `merge` |
| Unique membership | `HashSet<T>` / `MutableSet<T>` | `hashSetOf()`, `add` returns `false` on duplicate |
| Sorted keys, range or floor/ceiling queries | `TreeMap<K, V>` | `firstKey()`, `floorKey()`, `ceilingKey()`, `headMap`, `tailMap` |
| Sorted unique elements | `TreeSet<T>` | `first()`, `last()`, `floor()`, `ceiling()`, `pollFirst()` |
| Min heap (default) | `PriorityQueue<T>` | `PriorityQueue<Int>()` is min-heap |
| Max heap | `PriorityQueue<T>` with reversed comparator | `PriorityQueue<Int>(compareByDescending { it })` or `PriorityQueue<Int>(reverseOrder())` |
| Custom ordering | `PriorityQueue`, `sortedWith` | `compareBy({ it.a }, { it.b })`, `compareByDescending`, `thenBy`, `thenByDescending` |
| Queue (FIFO) | `ArrayDeque<T>` | `addLast()`, `removeFirst()`, `first()` |
| Stack (LIFO) | `ArrayDeque<T>` | `addFirst()` / `removeFirst()` (or `addLast()` / `removeLast()`) |
| Linked list (rarely needed) | `LinkedList<T>` or `ArrayDeque` | Prefer `ArrayDeque` unless you need a node-level list |
| Insertion-ordered map | `LinkedHashMap<K, V>` | Use for LRU with `accessOrder = true` |

Interview tips:

- Use `ArrayDeque`, not `java.util.Stack` or `LinkedList`, for stacks and queues. `ArrayDeque` is the idiomatic choice.
- `PriorityQueue` has no `peek` of an element without removing it. Use `peek()` to read, `poll()` to remove.
- Sorting a `List` with a comparator: `list.sortedWith(compareBy<Pair<String, Int>>({ -it.second }, { it.first }))`.
- `Map.entries.sortedWith(...)` is handy for "top K by frequency" problems.
- Use `String.toCharArray()` or `count` arrays of size 26 when keys are lowercase letters (faster than a map).

## Per-Problem Routine

Use this checklist on every problem. Say each step out loud.

1. **Clarify (2 min)**: restate the problem. Ask about input size, duplicates, empty input, and whether the output must be sorted or in place.
2. **Identify the data structure and why (2 min)**: name it and justify it. Example: "I need O(1) lookups by key, so a `HashMap`." Mention the alternative you rejected and why.
3. **Plan (2 min)**: describe the algorithm in plain English with one small example.
4. **Implement in Kotlin (10 to 15 min)**: write clean, named variables. Narrate what each block does.
5. **Handle tie-breaks and edge cases (2 min)**: equal frequencies, equal priorities, empty input, one element, negative numbers, duplicates.
6. **State complexity (1 min)**: time and space for the algorithm, and for each data structure operation if relevant.
7. **Discuss alternatives (2 min)**: if the input is a stream, too large for memory, or sorted already, how would the solution change? Examples: heap versus full sort for top K, streaming with a bounded heap, external sorting for huge files.

## Core Patterns to Recognize

| Pattern | Typical structure | Example problems |
|---------|-------------------|------------------|
| Frequency counting | `HashMap<T, Int>` | Ransom Note, Frequency Queries |
| Grouping by signature | `HashMap<Key, MutableList<T>>` | Group Anagrams |
| Complement lookup | `HashMap<value, index>` | Two Sum |
| Custom sort with tie-break | `sortedWith(compareBy ...)` | Sort by Frequency, Reorder Logs |
| Top K | Bounded min heap of size K | Top K Frequent Words, Kth Largest in a Stream |
| Running median | Two heaps (max and min) | Find Median from Data Stream |
| Ordered lookup | `TreeMap` / `TreeSet` | My Calendar I |
| Bracket matching | Stack | Balanced Brackets |
| Two-stack queue | Two stacks | Queue Using Two Stacks |
| Cache with eviction | `LinkedHashMap` (access order) | LRU Cache |

## Quick Self-Check Before Each Session

- Can I explain why I picked each data structure in one sentence?
- Can I write a comparator for two fields, including descending order, from memory?
- Can I build a max heap in Kotlin without looking it up?
- Can I state the time complexity of `HashMap` get and put, `TreeMap` get and put, and `PriorityQueue` add and poll?
