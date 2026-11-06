package fundamental_data_structures

/*
 * Kth Largest in a Stream
 *
 * Implement a class initialized with k and the existing stream values.
 * add(value) appends value and returns the kth-largest value seen so far.
 *
 * Example:
 * val stream = KthLargestTodo(3, intArrayOf(4, 5, 8, 2))
 * stream.add(3)  == 4
 * stream.add(5)  == 5
 * stream.add(10) == 5
 * stream.add(9)  == 8
 * stream.add(4)  == 8
 *
 * Constraints:
 * 1 <= k <= 10_000
 * 0 <= nums.size <= 10_000
 * -10_000 <= values <= 10_000
 * Assume add is called only when the stream contains at least k values.
 */

class KthLargestTodo(
    private val k: Int,
    nums: IntArray
) {
    init {
        require(k > 0) { "k must be positive" }
        // TODO: Initialize the data structure with nums.
        nums.size
    }

    fun add(value: Int): Int =
        TODO("Implement kth-largest stream tracking")
}
