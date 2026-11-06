package fundamentaldatastructures.exercises

import java.util.PriorityQueue

fun main() {

}


fun frequencySort(nums: IntArray): IntArray {
    /*
     * ==========================================
     * COMPLEXITY ANALYSIS:
     * - Time Complexity:  O(N log N) where N is the number of elements in nums.
     *                     Counting takes O(N), and sorting takes O(N log N).
     * - Space Complexity: O(N) to store the frequency map and the sorted collection.
     * ==========================================
     */

    // Step 1: Count the frequency of each unique number.
    // groupingBy { it }.eachCount() efficiently builds a Map<Int, Int>
    // mapping each element to its occurrence count in O(N) time.
    val frequencyMap = nums.asSequence().groupingBy { it }.eachCount()

    // Step 2: Sort the array using a custom multi-criteria comparator.
    return nums.sortedWith(
        compareBy<Int> { frequencyMap[it] } // Primary key: Ascending order of frequency
            .thenByDescending { it }       // Secondary key: Descending order of value (for ties)
    ).toIntArray()                         // Convert the resulting List<Int> back to a primitive IntArray
}


fun frequencySort2(nums: IntArray): IntArray {
    /*
     * Time: O(N + U log U)
     * - Count frequencies: O(N)
     * - Add and remove U unique values from the heap: O(U log U)
     * - Construct the result: O(N)
     *
     * Space: O(U) auxiliary space, or O(N + U) including the output array.
     *
     * N = number of elements
     * U = number of unique elements
     */

    val occurrenceMap = nums
        .asSequence()
        .groupingBy { it }
        .eachCount()

    val minHeap = PriorityQueue(
        compareBy<Map.Entry<Int, Int>> { it.value }
            .thenByDescending { it.key }
    )

    minHeap.addAll(occurrenceMap.entries)

    val result = IntArray(nums.size)
    var index = 0

    while (minHeap.isNotEmpty()) {
        val (number, frequency) = minHeap.poll()

        repeat(frequency) {
            result[index++] = number
        }
    }

    return result
}