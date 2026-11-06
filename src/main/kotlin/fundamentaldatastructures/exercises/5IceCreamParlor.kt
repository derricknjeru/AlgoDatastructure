package fundamentaldatastructures.exercises

/*
 * Ice Cream Parlor
 *
 * Two friends have exactly m units of money and must choose two distinct
 * ice cream flavors whose costs add up to m. Return their 1-based indices
 * in ascending order.
 *
 * Problem: https://www.hackerrank.com/challenges/icecream-parlor/problem
 *
 * Input:
 * - m: the total amount available.
 * - arr: the cost of each flavor.
 *
 * Output:
 * - The two 1-based indices whose costs sum to m, with the smaller index first.
 *
 * The lookup must happen before the current cost is inserted so one item
 * is never reused and two different items with the same cost still work.
 *
 * Complexity:
 * - Expected O(n) time because each item is visited once and hash-map
 *   lookup and insertion are expected O(1).
 * - O(d) space for d distinct costs, which is O(n) in the worst case.
 */

fun icecreamParlor(m: Int, arr: Array<Int>): Array<Int> {
    val indexByCost = mutableMapOf<Int, Int>()

    for (index in arr.indices) {
        val complement = m - arr[index]
        val previousIndex = indexByCost[complement]

        if (previousIndex != null) {
            return arrayOf(previousIndex + 1, index + 1)
        }

        indexByCost[arr[index]] = index
    }

    return emptyArray()
}

fun main() {

}
