package grind75v2.array

fun main() {
    val nums = intArrayOf(2, 0, 2, 1, 1, 0)
    sortColors(nums = nums)

}

fun sortColors3(nums: IntArray): Unit {
    // Line 3: Use of sortedMapOf() violates the restriction against using built-in sorting facilities.
    val occurrences = sortedMapOf<Int, Int>()
    for (i in nums) {
        occurrences[i] = occurrences.getOrDefault(i, 0) + 1
    }
    var index = 0
    for ((key, value) in occurrences) {
        var count = value
        while (count > 0) {
            nums[index++] = key
            count--
        }
    }
}


/**
 * Sorts an array containing 0s, 1s, and 2s in-place (Dutch National Flag Algorithm).
 *
 * TIME COMPLEXITY: O(n)
 * - Single pass through the array of size n.
 * - In each loop iteration, either `i` increments or `high` decrements,
 *   ensuring at most n iterations.
 *
 * SPACE COMPLEXITY: O(1)
 * - Modifies `nums` in-place using only three primitive integer pointers
 *   (`low`, `high`, `i`) and a constant-space swap operation.
 */
fun sortColors(nums: IntArray): Unit {
    // Points to the boundary where the next 0 should be placed
    var low = 0

    // Points to the boundary where the next 2 should be placed
    var high = nums.size - 1

    // Scans through the array (current unexamined element)
    var i = 0

    // Iterate until the scan pointer passes the high boundary.
    // Elements to the right of 'high' are already placed correctly as 2s.
    while (i <= high) {
        when (nums[i]) {
            0 -> {
                // Swap current 0 to the low region
                swap(i, low, nums)
                // Both pointers move forward because the element swapped from 'low'
                // is guaranteed to be 1 (or i == low, so it is safely processed).
                i++
                low++
            }

            1 -> {
                // 1 is already in its correct middle region; move to the next element
                i++
            }

            2 -> {
                // Swap current 2 to the high region
                swap(i, high, nums)
                // Shrink the high boundary
                high--
                // Note: 'i' is NOT incremented here because the element swapped
                // from 'high' into 'i' is unexamined and must be evaluated next.
            }
        }
    }
}

// Helper function to swap two elements in-place: O(1) time & space
private fun swap(i: Int, j: Int, nums: IntArray) {
    val temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp
}


//Accepted
//Direct Counting Array (Two-Pass, O(n) Time, O(1) Space)
//Since colors are constrained to 0, 1, and 2, count frequencies using a fixed 3-element array:
fun sortColors2(nums: IntArray): Unit {
    val counts = IntArray(3)

    // Pass 1: Count occurrences
    for (num in nums) {
        counts[num]++
    }
    // occurrence of each value, e.g. [2, 2, 2] for intArrayOf(2,0,2,1,1,0)
    println(counts.contentToString())

    // Pass 2: Overwrite original array
    var index = 0
    for (color in 0..2) {
        repeat(counts[color]) {
            nums[index++] = color
        }
    }
}

//Accepted
