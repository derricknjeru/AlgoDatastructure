package leetcode._6LinkedList

fun main() {

}


/*
Summary of Trade-offs

| Approach                | Time       | Extra Space | Modifies Array? | Notes                         |
|-------------------------|------------|-------------|-----------------|-------------------------------|
| Floyd's Cycle Detection | O(n)       | O(1)        | No              | Meets all problem constraints |
| HashSet                 | O(n)*      | O(n)        | No              | Simple, but uses extra memory |
| Sorting                 | O(n log n) | Depends**   | Yes             | Modifies the input array      |

* Expected time with hash-based operations.
** Depends on the sorting implementation, including its stack usage.
*/

// Time Complexity: O(n) - Both pointers traverse the array a constant number of times
// Space Complexity: O(1) - Only a constant amount of extra space (two pointer variables) is used
fun findDuplicate(nums: IntArray): Int {
    var hare = 0
    var tortoise = 0

    // Phase 1: Find the intersection point of the tortoise and hare within the cycle
    do {
        tortoise = nums[tortoise]         // Tortoise moves 1 step
        hare = nums[nums[hare]]           // Hare moves 2 steps
    } while (hare != tortoise)

    // Phase 2: Find the entrance of the cycle (which is the duplicate number)
    hare = 0 // Reset hare back to the starting index
    while (hare != tortoise) {
        hare = nums[hare]                 // Both pointers now move 1 step at a time
        tortoise = nums[tortoise]
    }

    // They will meet at the duplicate number
    return hare
}


// Time Complexity: O(n) - Single pass through the array
// Space Complexity: O(n) - HashSet stores up to n elements (Violates the O(1) space constraint)
fun findDuplicate2(nums: IntArray): Int {
    val seen = mutableSetOf<Int>()

    for (num in nums) {
        // If the element is already in the set, we found our duplicate
        if (!seen.add(num)) {
            return num
        }
    }

    error("not found")
    /**
     * `error("not found")` is a Kotlin function that **throws an `IllegalStateException`** with the message `"not found"`.
     * It stops execution—it doesn’t return a value or just print a message.
     * error("not found")
     * // Equivalent to:
     * throw IllegalStateException("not found")
     */
}


class Solution {
    // Time Complexity: O(n log n) - Dominated by the sorting step
    // Space Complexity: O(1) (or O(n) depending on sorting implementation) - Violates the rule against modifying the array
    fun findDuplicate(nums: IntArray): Int {
        // Sort the array in-place (Modifies the original array, violating problem constraints)
        nums.sort()

        for (i in 1 until nums.size) {
            // If two adjacent numbers are the same, we found the duplicate
            if (nums[i] == nums[i - 1]) {
                return nums[i]
            }
        }

        return -1
    }
}