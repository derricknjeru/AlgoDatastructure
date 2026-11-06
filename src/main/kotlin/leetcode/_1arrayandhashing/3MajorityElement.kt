package leetcode._1arrayandhashing

fun main() {
    val nums = intArrayOf(3, 2, 3)
    val list = listOf("one", "two", "three", "four", "five", "six", "two", "three", "three")
    println(list.groupingBy { it }.eachCount())
    val x = list.asSequence().filter { it != "one" }.groupingBy { it }.eachCount()
    println(x)

    val stringX = "Derrick"
    val charArrayMap = stringX.asSequence().groupingBy { it }.eachCount()
    println(charArrayMap)
    val mostFreq = majorityElement(nums)
    println("The number is $mostFreq")
    /**
     * Given an array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [3,2,3]
     * Output: 3
     * Example 2:
     *
     * Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     *
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     * The input is generated such that a majority element will exist in the array.
     *
     *
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     */

    val count = IntArray(26)
    count[0] = 6
    count[1] = 5
    count[2] = 4
    count[3] = 7
    val max = count.maxOrNull() ?: 0

    println(max)

    val map = mapOf(
        'a' to 4,
        'b' to 3,
        'c' to 2,
        'd' to 1
    )

    val max2 = map.maxByOrNull { it.value }?.key
    val max3 = map.maxOfOrNull { it.value }
    println(max2)
    println(max3)

}

// https://www.youtube.com/watch?v=7pnhv842keE
//Time Complexity: O(N)
//Space Complexity: O(1)
fun majorityElement(nums: IntArray): Int {
    var count = 0
    var res = 0
    for (n in nums) {
        if (count == 0) res = n
        count += if (n == res) 1 else -1
    }
    return res
}

//Time Complexity: O(N)
//Space Complexity: O(N)
fun majorityElement2(nums: IntArray): Int {
    val count = mutableMapOf<Int, Int>()
    var maxCount = 0
    var res = 0 // Changed from val to var

    for (n in nums) {
        val currentCount = count.getOrDefault(n, 0) + 1
        count[n] = currentCount

        if (currentCount > maxCount) {
            res = n
            maxCount = currentCount
        }
    }
    return res
}

//Time Complexity: O(N)
//Space Complexity: O(N)
private fun majorityElement3(nums: IntArray): Int {
    val occurrencesMap = mutableMapOf<Int, Int>()
    for (num in nums) {
        occurrencesMap[num] = occurrencesMap.getOrDefault(num, 0) + 1
    }
    val most = occurrencesMap.maxBy { it.value }
    val maxCount = 0;
    val res = 0
    // val mostFrequent = occurrenceMap.maxByOrNull { it.value }
    // return mostFrequent?.key ?: 0

    // occurrencesMap.isEmpty()
    return most.key
}

/**
 * Idiomatic counting with the standard library – same idea as majorityElement3, without the manual loop.
 *
 * How it works, for nums = [2, 2, 1, 1, 1, 2, 2]:
 *  1. `asIterable()` wraps the IntArray as an Iterable<Int> WITHOUT copying it.
 *     Needed because `groupingBy` exists on Iterable / Array<T> / Sequence / String,
 *     but NOT on primitive arrays (IntArray) and NOT on Iterator.
 *  2. `groupingBy { it }` says "group each number by itself" (the key is the number).
 *     It's lazy: no lists are built, it only describes the grouping.
 *  3. `eachCount()` does the single pass and returns Map<Int, Int> = number -> occurrences:
 *     {2=4, 1=3}  (keys in first-seen order).
 *  4. `maxBy { it.value }` returns the ENTRY with the highest count (2=4); `.key` is the number.
 *     maxBy throws on an empty map – fine here because the problem guarantees a majority exists.
 *     Use maxByOrNull { it.value }?.key if the input could be empty.
 *
 * Time:  O(N) – one pass to count (O(1) average per hash-map update) + O(K) to find the max, K ≤ N.
 * Space: O(K) for the map of K distinct numbers -> O(N) in the worst case.
 * Follow-up "O(1) space?" -> Boyer–Moore voting (majorityElement above).
 */
private fun majorityElement4(nums: IntArray): Int {
    val occurrencesMap = nums.asIterable().groupingBy { it }.eachCount()
    val most = occurrencesMap.maxBy { it.value }
    return most.key
}

/**
 * Same as majorityElement4, but wraps the array as a lazy Sequence instead of an Iterable.
 *
 * Result and complexity are IDENTICAL here:
 *  - `asSequence()` doesn't copy the array either.
 *  - `eachCount()` must read every element anyway, so laziness saves nothing.
 *
 * When asSequence() WOULD help: chaining steps before the grouping on large input, e.g.
 *   nums.asSequence().filter { it > 0 }.groupingBy { it }.eachCount()
 * Here `filter` doesn't build a temporary List (with asIterable() it would).
 *
 * Time:  O(N).   Space: O(K) -> O(N) worst case.
 */
private fun majorityElement5(nums: IntArray): Int {
    val occurrencesMap = nums.asSequence().groupingBy { it }.eachCount()
    val most = occurrencesMap.maxBy { it.value }
    return most.key
}
