# Day 3: Ordered Collections and Design

**Target time:** about 2 hours of timed solving (roughly 20 minutes per problem, since these are design-heavy).

Status key: mark `- [x] Solved` when you have a passing solution. Mark `- [x] Reviewed` after you compare with a reference solution and rewrite one from memory.

Note: links marked **(link not verified)** were not checked against the live site. Confirm the URL before you start.

---

## 1. LRU Cache

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/lru-cache/
- **Skill:** `LinkedHashMap` with access order, or hash map plus doubly linked list
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- What two operations must be O(1), and which structure gives each?
- In Kotlin, what does `LinkedHashMap(capacity, loadFactor, accessOrder = true)` change? How do you evict?
- If you implement it by hand, what does each node store, and why is the doubly linked list necessary?

---

## 2. Queue Using Two Stacks

- **Platform:** HackerRank (CtCI)
- **Link:** https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem **(link not verified)**
- **Skill:** Two stacks used as an in-stack and out-stack (amortized O(1) dequeue)
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- When exactly do you move elements from the in-stack to the out-stack? Why only when the out-stack is empty?
- What is the amortized cost of dequeue, and why is the worst case O(n) for a single call?
- In Kotlin, which type do you use for each stack, and why not `java.util.Stack`?

---

## 3. Balanced Brackets

- **Platform:** HackerRank (CtCI)
- **Link:** https://www.hackerrank.com/challenges/balanced-brackets/problem **(link not verified)**
- **Skill:** Stack for matching open and close brackets
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- What is the invariant that the stack maintains at each step?
- Which early exits make the solution faster (for example, a closing bracket with an empty stack)?
- How would you map each closing bracket to its opener cleanly in Kotlin (a `Map<Char, Char>`)?

---

## 4. Design Linked List

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/design-linked-list/
- **Skill:** Singly or doubly linked list with a dummy head and index-based operations
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- Why is a dummy head node useful? What edge cases does it remove?
- For `addAtIndex` and `deleteAtIndex`, how do you handle an index equal to the size or greater than the size?
- What is the cost of each operation, and when would an `ArrayList` be a better choice in practice?

---

## 5. My Calendar I

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/my-calendar-i/
- **Skill:** `TreeMap` for ordered intervals with floor and ceiling lookups
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- What is the overlap condition for half-open intervals `[start, end)`?
- Which neighbor do you need to check in the `TreeMap` to decide whether a new booking conflicts? Why is one neighbor enough?
- What is the complexity per booking, and how would a plain list compare?
