# Day 1: Maps, Sets, and Sorting

**Target time:** about 2 hours of timed solving (roughly 12 to 15 minutes per problem).

Status key: mark `- [x] Solved` when you have a passing solution. Mark `- [x] Reviewed` after you compare with a reference solution and rewrite one from memory.

Note: links marked **(link not verified)** were not checked against the live site. Confirm the URL before you start.

---

## 1. Two Sum

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/two-sum/
- **Skill:** `HashMap` complement lookup (value to index)
- **Status:**
  - [X] Solved
  - [X] Reviewed

**Key questions to answer out loud:**

- Why is a hash map better than a nested loop here? What are the time complexities of each?
 Ans: Look up is constant time & insertion is also constant time
- What do you store as the key and what as the value? Why?
  Ans: Num as key, index as value; because you will be checking numbers 
- What happens if the same number appears twice, and does the answer need to be unique?
   Ans: This is why the hash-map lookup must happen **before** inserting the current number: it prevents matching an element with itself while still supporting duplicates.

---

## 2. Ransom Note

- **Platform:** HackerRank (CtCI)
- **Link:** https://www.hackerrank.com/challenges/ctci-ransom-note/problem
- **Skill:** Frequency counting with `HashMap<String, Int>`
- **Status:**
  - [X] Solved
  - [X] Reviewed

**Key questions to answer out loud:**

- Why is a count map a better choice than sorting both word lists? Ans: sorting =nlogn countmap = O(m + n)
- How do you decide the answer when a word is missing from the magazine? return No
- Could you use an array instead of a map if the input were only lowercase letters? What is the trade-off? Yes

---

## 3. Group Anagrams

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/group-anagrams/
- **Skill:** Grouping by a canonical key (sorted string or count signature)
- **Status:**
  - [X] Solved
  - [X] Reviewed

**Key questions to answer out loud:**

- What makes two strings belong to the same group? How do you turn that into a key?
- Compare the sorted-string key with a 26-count key. What are the time costs of each?
- What order should the output groups be in, and does the problem require any specific order?

---

## 4. Sparse Arrays

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/sparse-arrays/problem
- **Skill:** Map from string to count, then answer queries with `getOrDefault`
- **Status:**
  - [X] Solved
  - [X] Reviewed

**Key questions to answer out loud:**

- Why is a map the natural structure when most queries are for strings that never appeared?
  - A map stores only strings that appeared and provides expected O(1) count lookup, which suits sparse queries.
- What should a query return for an unseen string, and how do you avoid a null crash in Kotlin?
  - An unseen query returns 0; use `occurrenceMap[query] ?: 0` or `getOrDefault(query, 0)` to avoid null.
- If the strings were very long, how would you reduce the memory cost (for example, hashing)?
  - Use fixed-size hashes or fingerprints to reduce key memory, but account for collision risk with strong or double hashes or by verifying originals when exactness is required.

---

## 5. Ice Cream Parlor

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/icecream-parlor/problem **(link not verified)**
- **Skill:** Map from price to index for pair finding under a budget
- **Status:**
  - [X] Solved
  - [X] Reviewed

**Key questions to answer out loud:**

- How is this a variant of Two Sum? What extra constraint must you respect?
- Why must the two indices be different, and how does your map handle that?
- What is the expected output format, and which index is printed first?

---

## 6. Sort Array by Increasing Frequency

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/sort-array-by-increasing-frequency/
- **Skill:** Frequency map plus a custom comparator with tie-break
- **Status:**
  - [X] Solved
  - [X] Reviewed

**Key questions to answer out loud:**

- Which sort key is primary and which is the tie-break? How do you express that in `sortedWith`?
- Why does the tie-break need to be in descending value order?
- What is the complexity of your sort, and is there a way to avoid a comparison sort?

---

## 7. Comparator

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/java-comparator/problem **(link not verified)**
- **Skill:** Custom ordering with `compareBy` and `thenBy` (Kotlin equivalent of a Java comparator)
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- What does the comparator return and what do negative, zero, and positive values mean?
- How do you chain a second field as a tie-break in Kotlin?
- How would you make one field descending while the other stays ascending?

---

## 8. Reorder Data in Log Files

- **Platform:** LeetCode
- **Link:** https://leetcode.com/problems/reorder-data-in-log-files/
- **Skill:** Partitioning plus custom comparator with multiple tie-break rules
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- How do you decide whether a log is a letter-log or a digit-log? Where should digit-logs end up?
- Why is a stable sort important when letter-logs have equal content?
- Would you sort the whole list once with a comparator or split into two lists first? Which is clearer?

---

## 9. Frequency Queries

- **Platform:** HackerRank
- **Link:** https://www.hackerrank.com/challenges/frequency-queries/problem
- **Skill:** Two maps (value to count, count to set of values) for O(1) queries
- **Status:**
  - [ ] Solved
  - [ ] Reviewed

**Key questions to answer out loud:**

- Why is one map not enough to answer "does any value occur exactly k times" quickly?
- When a count changes, how do you remove the value from the old count bucket without scanning?
- What happens on a delete when the value's count is already zero? Does the problem say?
