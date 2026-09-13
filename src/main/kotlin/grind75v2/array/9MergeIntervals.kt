package grind75v2.array

fun main() {

}
// https://www.youtube.com/watch?v=44H3cEC2fFM
/**
 * Key Points of Your SolutionIn-place updates:
 * Modifying currentInterval[1] directly updates the reference stored inside res,
 * which avoids creating unnecessary extra objects during merges.Sorting Efficiency: intervals.sortBy { it[0] }
 * correctly orders intervals by start time in O(n log n) time.
 * Linear Scan: The loop runs in O(n), yielding an overall optimal time complexity of O(nlogn)
 */

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.size <= 1) return intervals
    //nlogn
    intervals.sortBy { it[0] }

    val res = mutableListOf<IntArray>()
    var currentInterval = intervals[0]
    res.add(currentInterval)

    for (i in 1 until intervals.size) {
        val currentEnd = currentInterval[1]
        val nextInterval = intervals[i]

        if (currentEnd >= nextInterval[0]) {
            currentInterval[1] = maxOf(currentEnd, nextInterval[1])
        } else {
            currentInterval = nextInterval
            res.add(currentInterval)
        }
    }

    return res.toTypedArray()
}