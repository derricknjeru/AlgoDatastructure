# Interview prep plan – Intercom / Fin, Monday 9 November

Five days. Each day has two blocks:

1. **Kotlin essentials** (≈ 45–60 min) – learn the syntax you'll need.
2. **LeetCode / Intercom pack** (≈ 1–2 h) – solve problems that use that syntax.

All paths are relative to the repo root `/Users/derrick/AlgoDatastructure`.
Kotlin essentials live in `src/main/kotlin/intercom/_0kotlinessentials/`.

---

## How every session works

### "Lessons" = read and run
Open the `lessons/Lessons.kt` file, read the named functions top to bottom, then run the file.
Every line should print `PASS`.

### "Drills" = you write the code
Open the `practice/Practice.kt` file, replace `TODO(...)` in the named functions, run it.
Scenarios you haven't reached yet print `TODO` – that's fine. When done, compare with
`solution/Solution.kt` in the same folder.

### LeetCode = blank file, timer, out loud
1. Read the problem on leetcode.com (or the comment at the top of your old file).
2. Create a scratch file with its **own package** so names never clash, e.g.
   `src/main/kotlin/scratch/Lc981.kt`:
   ```kotlin
   package scratch.lc981

   fun main() {
       // 1. write the examples as checks FIRST
       // 2. then the solution
   }
   ```
3. Run it: `./gradlew -q runMain -PmainClass=scratch.lc981.Lc981Kt`
   (class name = file name + `Kt`).
4. Timer **25 min**. Talk out loud as if the interviewer is listening.
5. Compare with your old solution (path in each table) and write down **one mistake**.

### Running things
```bash
# any Kotlin main
./gradlew -q runMain -PmainClass=<package>.<FileName>Kt
# tests
./gradlew test --tests 'intercom.*'
```

---

## Wed 4 Nov (tonight, ≈ 1 h)

### Kotlin – Part 1, lessons 1–3
File: `_0kotlinessentials/lessons/Lessons.kt`

| Read | Topic |
|------|-------|
| `lesson1DataClasses()` | data classes, `copy()`, shallow copy gotcha |
| `lesson2Comparators()` | `compareBy` / `thenBy` / `compareByDescending` |
| `lesson3Grouping()` | `groupBy`, `groupingBy().eachCount()`, `fold`, `partition` |

```bash
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.lessons.LessonsKt
```

### Kotlin – Part 1, drills 1–3
File: `_0kotlinessentials/practice/Practice.kt`

| Drill | Function |
|-------|----------|
| 1 | `recordAssignment` |
| 2a / 2b | `assignmentOrder`, `topMacros` |
| 3a / 3b | `idsByAssignee`, `countByStatus` |

```bash
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.practice.PracticeKt
```
Scenarios 1–3 should PASS.

### LeetCode
| Problem | Your old solution |
|---------|-------------------|
| 49 Group Anagrams | `src/main/kotlin/leetcode/_1arrayandhashing/12GroupAnagrams.kt` |
| 1 Two Sum | `src/main/kotlin/leetcode/_1arrayandhashing/1TwoSum.kt` |

---

## Thu 5 Nov

### Kotlin – Part 1, lessons 4–6
File: `_0kotlinessentials/lessons/Lessons.kt`

| Read | Topic |
|------|-------|
| `lesson4GetOrPut()` | `getOrPut`, `merge`, nested maps |
| `lesson5TreeMapAndHeap()` | `TreeMap` (`floorKey`, `subMap`, …), `PriorityQueue`, top-k |
| `lesson6RequireCheckNulls()` | `require` / `check`, `?.`, `?:`, `?.let`, `toIntOrNull` |

### Kotlin – Part 1, drills 4–6
File: `_0kotlinessentials/practice/Practice.kt`

| Drill | Function |
|-------|----------|
| 4 | `buildInboxes` |
| 5a / 5b / 5c | `eventsBetween`, `lastEventAtOrBefore`, `busiestAgents` |
| 6a / 6b / 6c | `parseLimit`, `Ticket.close`, `ownerOf` |

All six scenarios should now PASS.

### Intercom pack
1. **Pack #1 – timed redo, < 30 min.** Read the prompt in
   `src/main/kotlin/intercom/_1agentassignment/README.md` (section 1 only), then solve from a blank
   scratch file (`src/main/kotlin/scratch/Assignment.kt`, `package scratch.assignment`).
   Include `closeConversation`. Compare with your `practice/Practice.kt`.
2. **Pack #3 – LC 1348.** Read `src/main/kotlin/intercom/_3conversationanalytics/README.md`
   section 1, implement `_3conversationanalytics/practice/Practice.kt`, run:
   ```bash
   ./gradlew -q runMain -PmainClass=intercom._3conversationanalytics.practice.PracticeKt
   ```

### LeetCode
| Problem | Your old solution |
|---------|-------------------|
| **981** Time Based Key-Value Store | `leetcode/_4binarysearch/6TimeBasedKeyValueStore.kt` |
| **703** Kth Largest Element in a Stream | `leetcode/_10heappriorityqueue/2KthLargest.kt` |

---

## Fri 6 Nov

### Kotlin – Part 2 (Collections), lessons 1–8
File: `_0kotlinessentials/collections/lessons/Lessons.kt` · data table in `collections/Data.kt`

| Read | Topic |
|------|-------|
| `overviewAndTypes()` | List / Set / Map, read-only vs mutable |
| `create()` | `List(n){}`, `buildList`, `listOfNotNull`, … |
| `iterate()` | `withIndex`, map destructuring, ConcurrentModificationException |
| `change()` | `remove` vs `removeAt`, `retainAll`, `sort` vs `sorted` |
| `filter()` | `filter`, `partition`, `any` / `all` / `none` |
| `order()` | `sortedBy`, `sortedWith`, `nullsLast(reverseOrder())` |
| `transform()` | `map`, `flatMap`, `zip`, `associate*`, `joinToString` |
| `group()` | `groupBy`, `groupingBy().fold / reduce` |

```bash
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.collections.lessons.LessonsKt
```

### Kotlin – Part 2, drills 1–5
File: `_0kotlinessentials/collections/practice/Practice.kt`

| Drill | Function |
|-------|----------|
| 1 | `customersWithAssignedConversations` |
| 2 | `unratedIds` |
| 3 | `bestRatedFirst` |
| 4 | `summaryLine` |
| 5 | `messagesById` |

```bash
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.collections.practice.PracticeKt
```
Scenario "Filter / order / transform" should PASS.

### LeetCode
| Problem | Your old solution |
|---------|-------------------|
| **146** LRU Cache | `leetcode/_6LinkedList/10LRUCache.kt` |
| 155 Min Stack | `leetcode/_2stack/4MinStack.kt` |
| **347** Top K Frequent Elements | `leetcode/_1arrayandhashing/13TopKFrequent.kt` |

---

## Sat 7 Nov

### Kotlin – Part 2 (Collections), lessons 9–16
File: `_0kotlinessentials/collections/lessons/Lessons.kt`

| Read | Topic |
|------|-------|
| `aggregate()` | `sumOf`, `maxByOrNull` vs `maxOf`, `fold`, `runningFold` |
| `combine()` | `union` / `intersect` / `subtract`, `+` / `-` |
| `retrieveSingle()` | `getOrNull`, `firstOrNull {}`, `find`, `single` |
| `retrieveParts()` | `take` / `drop`, `chunked`, `windowed`, `zipWithNext`, `subList` view |
| `listSpecific()` | `binarySearch` and insertion point |
| `mapSpecific()` | `getOrDefault`, `filterValues`, `merge`, `compute`, `toSortedMap` |
| `sequences()` | lazy evaluation, early exit |
| `ranges()` | `..`, `until` / `..<`, `downTo`, `step`, `coerceIn` |

### Kotlin – Part 2, drills 6–14
File: `_0kotlinessentials/collections/practice/Practice.kt`

| Drill | Function |
|-------|----------|
| 6 | `averageRating` |
| 7 | `busiestAssignee` |
| 8 | `cumulativeMessages` |
| 9 | `chatOnlyCustomers` |
| 10 | `firstUnassignedId`, `longestConversationId` |
| 11 | `pages`, `busiestWindow` |
| 12 | `ratingsByCustomer` |
| 13 | `firstBigConversationIds` |
| 14 | `uncoveredHours` |

All four scenarios should now PASS.

### Intercom pack
**Pack #2 – conversation service (chat data model).** Read
`src/main/kotlin/intercom/_2conversationservice/README.md` sections 1–3, implement
`_2conversationservice/practice/Practice.kt`, run:
```bash
./gradlew -q runMain -PmainClass=intercom._2conversationservice.practice.PracticeKt
```
Then answer section 6 (follow-ups) out loud – this round was mostly data modelling.

### LeetCode
| Problem | Your old solution |
|---------|-------------------|
| 973 K Closest Points to Origin | `leetcode/_10heappriorityqueue/4KClosest.kt` |
| 56 Merge Intervals | `leetcode/_1arrayandhashing/9MergeIntervals.kt` |
| 57 Insert Interval | `leetcode/_1arrayandhashing/5Interval.kt` |

---

## Sun 8 Nov

### Kotlin – Part 3 (Queues), all lessons + all drills
Lessons: `_0kotlinessentials/queues/lessons/Lessons.kt` – read all 8:
`queueFifo`, `stackLifo`, `dequeBothEnds`, `javaQueueApi`, `priorityQueue`, `bfs`,
`slidingTimeWindow`, `concurrentQueues`.

Drills: `_0kotlinessentials/queues/practice/Practice.kt`

| Drill | Function |
|-------|----------|
| 1 | `roundRobin` |
| 2 | `RecentMessages` (`add`, `latest`) |
| 3 | `isBalanced` |
| 4 | `applyEdits` |
| 5 | `hops` |
| 6 | `minPerWindow` |
| 7 | `serveOrder` |

```bash
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.queues.lessons.LessonsKt
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.queues.practice.PracticeKt
```

### Kotlin – Part 4 (Scope functions), all lessons + all drills
Lessons: `_0kotlinessentials/scopefunctions/lessons/Lessons.kt` – `let`, `run`, `with`, `apply`,
`also`, `takeIf`, `gotchas`.

Drills: `_0kotlinessentials/scopefunctions/practice/Practice.kt` – `greeting`, `newAgent`,
`assignAndLog`, `parsePositive`, `statusLine`, `loadPercent`.

```bash
./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.scopefunctions.practice.PracticeKt
```

### Kotlin – tests
1. Read `src/test/kotlin/intercom/_0kotlinessentials/KotlinTestBasicsTest.kt`.
2. Create `src/test/kotlin/intercom/_1agentassignment/MyAssignmentTest.kt` with 3 tests for **your**
   `intercom._1agentassignment.practice.AssignmentSystem` (the example, nobody has capacity,
   `closeConversation`).
3. Run: `./gradlew test --tests 'intercom._1agentassignment.*'`

### LeetCode
| Problem | Your old solution |
|---------|-------------------|
| 239 Sliding Window Maximum | `leetcode/_5sliding_window/6SlidingWindowMaximum.kt` (**empty – solve fresh there**) |
| 20 Valid Parentheses | `leetcode/_2stack/1ValidParentheses.kt` |
| 200 Number of Islands | `leetcode/_11graph/2NumberOfIslands.kt` |
| 208 Implement Trie | `leetcode/_8Tries/1ImplementTrie.kt` |
| **1882** Process Tasks Using Servers | not in your folder – [leetcode.com/problems/process-tasks-using-servers](https://leetcode.com/problems/process-tasks-using-servers/) |

### Full mock – Pack #1 (60 min)
Ask a friend (or record yourself) to play interviewer using
`src/main/kotlin/intercom/_1agentassignment/README.md`:
section 1 prompt → you ask section 3 questions → code → interviewer asks 2–3 rows from section 6.
Then read `_1agentassignment/practice/ScalableAssignmentSystem.kt` (TreeSet, offline agents, concurrency).

---

## Mon 9 Nov – interview day

No new problems. 30–45 min max:
1. Re-read section 6 (follow-ups) of the READMEs for packs #1, #2 and #3.
2. Skim the cheat sheets in `_0kotlinessentials/README.md` (Parts 1–4).
3. Type the `main` template once from memory (see "Speed drill" in that README).

### In the interview
1. Ask clarifying questions; restate the rules in your own words.
2. Write the given example as a `main` check **before** the implementation.
3. Start with the simple O(n) version; run it early and often.
4. Say edge cases out loud (empty, full, duplicates, unknown ids, ties).
5. Only then optimise – and explain the trade-off (e.g. scan vs TreeSet).

---

## If you fall behind

**Never skip:** Pack #1, Pack #3 (LC 1348), LC 981, Part 1 of Kotlin essentials.

**Drop in this order:**
1. Sunday's LeetCode list (except 1882)
2. Part 4 (Scope functions)
3. Part 2 lessons 13–16 (`listSpecific`, `mapSpecific`, `sequences`, `ranges`)

## Progress checklist

- [ ] Wed 4 – Part 1 lessons 1–3, drills 1–3 · LC 49, 1
- [ ] Thu 5 – Part 1 lessons 4–6, drills 4–6 · Pack #1 redo · Pack #3 (LC 1348) · LC 981, 703
- [ ] Fri 6 – Part 2 lessons 1–8, drills 1–5 · LC 146, 155, 347
- [ ] Sat 7 – Part 2 lessons 9–16, drills 6–14 · Pack #2 · LC 973, 56, 57
- [ ] Sun 8 – Parts 3 + 4 · 3 tests for #1 · LC 239, 20, 200, 208, 1882 · full mock of #1
- [ ] Mon 9 – follow-ups #1–#3 · cheat sheets · main template
