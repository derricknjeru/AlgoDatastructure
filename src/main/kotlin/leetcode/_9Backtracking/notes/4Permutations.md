# 46. Permutations

## Problem

Given an array `nums` of distinct integers, return all the possible permutations. You can return the answer in **any
order**.

Problem: [LeetCode 46 - Permutations](https://leetcode.com/problems/permutations/)

## What Is a Permutation?

A permutation is an arrangement of all the input numbers.

For:

```text
nums = [1, 2, 3]
```

`[1, 2, 3]` and `[2, 1, 3]` are different permutations because their order is different.

There are:

```text
N!
```

permutations for `N` distinct numbers. For three numbers:

```text
3! = 3 * 2 * 1 = 6
```

## Approach: Backtracking with `BooleanArray`

At every recursion level:

1. Loop through every number.
2. Skip an index if it has already been used.
3. Choose an unused number.
4. Recursively build the rest of the permutation.
5. Backtrack so another number can be tried.

A `BooleanArray` tracks which array positions are currently part of the permutation.

## Kotlin Solution

```kotlin
class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val currentPermutation = mutableListOf<Int>()
        val used = BooleanArray(nums.size)

        fun dfs() {
            // A permutation is complete when it contains every number.
            if (currentPermutation.size == nums.size) {
                // Save a copy because currentPermutation is modified
                // while backtracking.
                result.add(currentPermutation.toList())
                return
            }

            // Try every number for the current position.
            for (i in nums.indices) {
                // Skip this array position if it is already being used.
                if (used[i]) {
                    continue
                }

                // Choose nums[i].
                used[i] = true
                currentPermutation.add(nums[i])

                // Continue building the remaining positions.
                dfs()

                // Undo the choice before trying another number.
                currentPermutation.removeLast()
                used[i] = false
            }
        }

        dfs()
        return result
    }
}
```

## Why There Is No `index` Parameter

In combination problems, an index usually moves forward so that numbers before it are not reconsidered.

For permutations, every unused number must be considered at every position. Therefore, each recursive call loops through
the entire input:

```kotlin
for (i in nums.indices)
```

The `used` array prevents a position from being selected more than once in the same permutation.

## How `BooleanArray` Works

For:

```text
nums = [1, 2, 3]
```

the initial state is:

```text
used = [false, false, false]
```

After choosing `2`, which is stored at index `1`:

```text
currentPermutation = [2]
used = [false, true, false]
```

The next recursion skips index `1` because it is already being used:

```kotlin
if (used[i]) {
    continue
}
```

After returning from recursion, backtracking makes index `1` available again:

```kotlin
currentPermutation.removeLast()
used[1] = false
```

## Why `BooleanArray` Is Better Than `contains`

Both approaches work for LeetCode 46 because all input values are distinct:

```kotlin
currentPermutation.contains(nums[i])
```

However, `BooleanArray` is preferred:

| `contains`                  | `BooleanArray`                              |
|-----------------------------|---------------------------------------------|
| Searches the list in `O(N)` | Checks an index in `O(1)`                   |
| Tracks values               | Tracks array positions                      |
| Less efficient              | More efficient                              |
| Relies on distinct values   | Can be extended to duplicate-value problems |

The membership check becomes:

```kotlin
if (used[i]) continue
```

instead of:

```kotlin
if (currentPermutation.contains(nums[i])) continue
```

## Decision Tree

Consider:

```text
nums = [1, 2, 3]
```

Each level fills one position in the permutation:

```text
[]
├── choose 1
│   ├── choose 2
│   │   └── choose 3 -> [1, 2, 3]
│   └── choose 3
│       └── choose 2 -> [1, 3, 2]
├── choose 2
│   ├── choose 1
│   │   └── choose 3 -> [2, 1, 3]
│   └── choose 3
│       └── choose 1 -> [2, 3, 1]
└── choose 3
    ├── choose 1
    │   └── choose 2 -> [3, 1, 2]
    └── choose 2
        └── choose 1 -> [3, 2, 1]
```

The number of choices decreases at every level:

```text
First position:  3 choices
Second position: 2 choices
Third position:  1 choice

Total permutations = 3 * 2 * 1 = 6
```

## Backtracking

Choosing a number requires two state changes:

```kotlin
used[i] = true
currentPermutation.add(nums[i])
```

After recursion returns, both changes must be reversed:

```kotlin
currentPermutation.removeLast()
used[i] = false
```

For example:

```text
Choose 1:       current = [1],    used = [true, false, false]
Choose 2:       current = [1, 2], used = [true, true, false]
Choose 3:       current = [1, 2, 3]
Save result:    [1, 2, 3]
Remove 3:       current = [1, 2], used = [true, true, false]
Remove 2:       current = [1],    used = [true, false, false]
Choose 3 next:  current = [1, 3], used = [true, false, true]
```

Backtracking restores the previous state so the next branch starts correctly.

## Why `toList()` Is Necessary

```kotlin
result.add(currentPermutation.toList())
```

`currentPermutation` is one mutable list shared by every recursive call. `toList()` creates a snapshot of the completed
permutation.

Without a copy, later `add()` and `removeLast()` operations would modify the list referenced by previously stored
results.

## Example

```text
Input:
nums = [1, 2, 3]

Output:
[
  [1, 2, 3],
  [1, 3, 2],
  [2, 1, 3],
  [2, 3, 1],
  [3, 1, 2],
  [3, 2, 1]
]
```

## Complexity

```text
Time:  O(N * N!)
Space: O(N), excluding the returned permutations
```

- There are `N!` complete permutations.
- Copying each completed permutation into the result takes `O(N)`.
- Checking `used[i]` takes `O(1)`.
- The recursion depth is at most `N`.
- `currentPermutation` and `used` each require `O(N)` space.

If the returned result is included in the space analysis, it requires:

```text
O(N * N!)
```

because there are `N!` lists and each list contains `N` numbers.

## Key Points

- Order matters in permutations.
- Loop through all numbers at every recursion level.
- Use `BooleanArray` to track selected indices.
- Mark an index before recursion and unmark it during backtracking.
- Remove the last number after each recursive call.
- Use `toList()` when saving a completed permutation.
- The base case is reached when the current permutation has `N` elements.
