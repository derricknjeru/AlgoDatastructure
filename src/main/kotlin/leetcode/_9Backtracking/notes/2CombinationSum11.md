# 40. Combination Sum II

## Problem

Given an array of candidate numbers and a target, return all unique combinations whose sum equals the target.

- Each array position may be used only once.
- The input may contain duplicate values.
- The returned combinations must be unique.
- The order of the combinations does not matter.

Problem: [LeetCode 40 - Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

## Main Difference from Combination Sum I

| Combination Sum I | Combination Sum II |
|---|---|
| A candidate may be reused | Each array position may be used once |
| Input values are distinct | Input may contain duplicates |
| Include with `dfs(index, ...)` | Include with `dfs(index + 1, ...)` |
| Duplicate skipping is unnecessary | Duplicate skipping is required |

## Approach: Include or Exclude

At every index, make one of two decisions:

1. **Include** the current candidate and move to the next index.
2. **Exclude** the current candidate and move past every duplicate of that value.

The array must first be sorted so that duplicate values are adjacent.

## Kotlin Solution

```kotlin
class Solution {
    fun combinationSum2(
        candidates: IntArray,
        target: Int
    ): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val currentCombination = mutableListOf<Int>()

        // Sorting places duplicate values next to each other.
        candidates.sort()

        fun dfs(index: Int, total: Int) {
            // The current combination adds up to the target.
            if (total == target) {
                // Save a copy because currentCombination is modified
                // while backtracking.
                result.add(currentCombination.toList())
                return
            }

            // Stop when the sum is too large or no candidates remain.
            if (total > target || index >= candidates.size) {
                return
            }

            // Include the current candidate.
            currentCombination.add(candidates[index])

            // Move to the next index because each array position
            // may be used only once.
            dfs(index + 1, total + candidates[index])

            // Undo the inclusion before exploring the exclude branch.
            currentCombination.removeLast()

            // Skip every duplicate of the current value when excluding it.
            // Without this loop, equal values at the same decision level
            // could generate identical combinations.
            var nextIndex = index + 1

            while (
                nextIndex < candidates.size &&
                candidates[nextIndex] == candidates[index]
            ) {
                nextIndex++
            }

            // Exclude the current value and continue from the first
            // different value.
            dfs(nextIndex, total)
        }

        dfs(index = 0, total = 0)
        return result
    }
}
```

## Why `index + 1` Is Used on the Include Branch

```kotlin
dfs(index + 1, total + candidates[index])
```

Each array position can be selected only once. Moving to `index + 1` prevents the same position from being reused.

This differs from Combination Sum I:

```kotlin
// Combination Sum I: reuse is allowed.
dfs(index, total + candidates[index])

// Combination Sum II: reuse is not allowed.
dfs(index + 1, total + candidates[index])
```

## Why the Exclude Branch Skips Duplicates

Consider:

```text
candidates = [1, 1, 2]
```

If the first `1` is excluded but recursion immediately continues at the second `1`, both positions can become the first selected `1`. This can generate the same combination more than once.

The duplicate-skipping loop moves to the first different value:

```kotlin
var nextIndex = index + 1

while (
    nextIndex < candidates.size &&
    candidates[nextIndex] == candidates[index]
) {
    nextIndex++
}
```

This skip is performed only on the **exclude branch**. The include branch moves only one position forward, allowing combinations such as `[1, 1, 6]` when the input contains two separate `1` positions.

## Decision Tree

Consider this smaller example:

```text
candidates = [1, 1, 2]
target = 3
```

The array is already sorted. Each node shows:

```text
(index, total, currentCombination)
```

```text
                         (0, 0, [])
                        candidate = 1
                       /             \
                 include 1          exclude 1
                    /                   \
              (1, 1, [1])          (2, 0, [])
             candidate = 1          candidate = 2
              /        \              /        \
        include 1    exclude 1   include 2    exclude 2
           /             \          /              \
     (2, 2, [1,1])   (2, 1, [1]) (3, 2, [2])   (3, 0, [])
      candidate = 2    candidate = 2
        /      \          /      \
   include 2  exclude 2 include 2  exclude 2
       /          \         /          \
 (3,4,[1,1,2]) (3,2,[1,1]) (3,3,[1,2]) (3,1,[1])
       X             X          VALID         X
```

The valid combination is:

```text
[1, 2]
```

At the root, excluding the first `1` skips the second `1` as well:

```text
index = 0
nextIndex = 1

candidates[1] == candidates[0] // both are 1
nextIndex = 2
```

Therefore, the exclude branch goes directly from index `0` to index `2`:

```text
(0, 0, []) -> (2, 0, [])
```

Without duplicate skipping, it would go to `(1, 0, [])`. That second `1` could then produce `[1, 2]` again, creating a duplicate result.

## Backtracking

```kotlin
currentCombination.add(candidates[index])
dfs(index + 1, total + candidates[index])
currentCombination.removeLast()
```

`removeLast()` reverses the previous choice. This restores `currentCombination` to its earlier state before recursion explores the exclude branch.

## Why `toList()` Is Necessary

```kotlin
result.add(currentCombination.toList())
```

`currentCombination` is one mutable list shared by all recursive calls. `toList()` creates a snapshot of its current elements. Later calls to `add()` or `removeLast()` therefore do not change combinations already stored in `result`.

## Example

```text
Input:
candidates = [10, 1, 2, 7, 6, 1, 5]
target = 8

Sorted candidates:
[1, 1, 2, 5, 6, 7, 10]

Output:
[
  [1, 1, 6],
  [1, 2, 5],
  [1, 7],
  [2, 6]
]
```

## Complexity

```text
Time:  O(2^N * N) in the worst case
Space: O(N), excluding the returned combinations
```

- Every candidate has an include or exclude decision, resulting in up to `2^N` subsets.
- Copying a valid combination may take up to `O(N)`.
- Sorting costs `O(N log N)`, which is dominated by the exponential search.
- The recursion depth and current combination contain at most `N` elements.

## Key Points

- Sort the candidates before recursion.
- Include with `index + 1` because each position may be used only once.
- Backtrack with `removeLast()` after the include branch.
- On the exclude branch, skip all adjacent duplicates.
- Use `toList()` when saving a combination.
