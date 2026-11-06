# Day 2: Heaps

**Target time:** about 2 hours of timed solving (roughly 15 to 20 minutes per problem).

Status key: mark `- [x] Solved` when you have a passing solution. Mark `- [x] Reviewed` after you compare with a reference solution and rewrite one from memory.

Reminder: in Kotlin, `PriorityQueue<Int>()` is a **min heap**. For a **max heap**, use `PriorityQueue<Int>(compareByDescending { it })` or `PriorityQueue<Int>(reverseOrder())`.

Note: links marked **(link not verified)** were not checked against the live site. Confirm the URL before you start.

---

## 1. Top K Frequent Words

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/top-k-frequent-words/
- **Skill:** Frequency map plus bounded heap with a two-field comparator (count, then lexicographic)
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- Which heap do you use for top K, min or max, and why? What size do you keep it at?
- Which tie-break order do the problem's rules require, and how does your comparator encode it?
- What is the complexity of a full sort versus a bounded heap? When does the heap win?

---

## 2. Kth Largest Element in a Stream

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/kth-largest-element-in-a-stream/
- **Skill:** Size-K min heap maintained across inserts
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- Why does a min heap of size K give you the Kth largest at the top?
- When a new value arrives, what is the exact sequence of operations to keep the heap at size K?
- What is the cost per `add`, and how does it compare with re-sorting the whole stream?

---

## 3. Jesse and Cookies

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/jesse-and-cookies/problem
- **Skill:** Min heap repeatedly combining the two smallest values
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- Why must you always combine the two smallest values? Give a small counter-example if you used the largest.
- How do you detect the case where it is impossible to reach the target sweetness?
- What is the total complexity, and how many combine operations can happen in the worst case?

---

## 4. QHEAP1

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/qheap1/problem
- **Skill:** Min heap with lazy deletion (or a second heap as a delete buffer)
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- `PriorityQueue` does not support deleting an arbitrary value efficiently. What are your options?
- If you use lazy deletion, how do you know the top of the heap is stale?
- What are the complexities of insert, delete, and get-min in your design?

---

## 5. Find the Running Median

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/find-the-running-median/problem
- **Skill:** Two heaps (max heap for lower half, min heap for upper half) with rebalancing
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- What invariant must the two heaps keep, and when do you rebalance?
- How do you compute the median when the total count is odd versus even?
- Why is a sorted list with insertion slower here? What is its cost per element?

---

## 6. Find Median from Data Stream

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/find-median-from-data-stream/
- **Skill:** Two heaps (same as running median, in class-design form)
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- How does the design differ from the running median problem? What methods must the class expose?
- If most numbers in the stream are in a small range, how would you speed things up? (Bucket counts.)
- If the stream were too large for memory, what would you change? (Approximate median, sampling, or external storage.)
