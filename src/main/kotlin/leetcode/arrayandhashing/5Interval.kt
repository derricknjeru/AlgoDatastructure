package leetcode.arrayandhashing

import kotlin.math.max
import kotlin.math.min


fun main() {
    //https://www.youtube.com/watch?v=A8NUOmlwOlM
    //Medium
    /**
     * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     *
     * Two intervals are considered overlapping if they share at least one point.
     *
     * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     *
     * Return intervals after the insertion.
     *
     * Note that you don't need to modify intervals in-place. You can make a new array and return it.
     *
     *
     *
     * Example 1:
     *
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     * Example 2:
     *
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     *
     *
     * Constraints:
     *
     * 0 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 105
     * intervals is sorted by starti in ascending order.
     * newInterval.length == 2
     * 0 <= start <= end <= 105
     */

}

fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    /* Complexity Analysis

   * **Time Complexity: O(n)
   * We iterate through the `intervals` array exactly **once**, where $n$ is the total number of intervals.
   * Inside the loop, all operations (`min`, `max`, `add`) run in (O)(1) time.


   * **Space Complexity: O(n)
   * The `result` list stores at most n + 1 intervals in the worst case (when no overlaps occur).
   * Converting the list to an array (`toTypedArray()`) allocates O(n) additional memory for the final output array.
   */
    val res = mutableListOf<IntArray>()
    var currentInterval = newInterval
    for (interval in intervals) {
        if (interval[1] < currentInterval[0]) {
            res.add(interval)
        } else if (interval[0] > currentInterval[1]) {
            res.add(currentInterval)
            currentInterval = interval
        } else {
            currentInterval[0] = min(currentInterval[0], interval[0])
            currentInterval[1] = max(currentInterval[1], interval[1])
        }
    }
    res.add(currentInterval)
    return res.toTypedArray()
}


fun insert2(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    /* Complexity Analysis

   * **Time Complexity: O(n)
   * We iterate through the `intervals` array exactly **once**, where $n$ is the total number of intervals.
   * Inside the loop, all operations (`min`, `max`, `add`) run in (O)(1) time.


   * **Space Complexity: O(n)
   * The `result` list stores at most n + 1 intervals in the worst case (when no overlaps occur).
   * Converting the list to an array (`toTypedArray()`) allocates O(n) additional memory for the final output array.
   */
    val result = mutableListOf<IntArray>()
    var mergedInterval = newInterval

    for (interval in intervals) {
        when {
            // Case 1: Current interval ends before the new interval starts (No overlap)
            interval[1] < mergedInterval[0] -> {
                result.add(interval)
            }

            // Case 2: Current interval starts after the new interval ends (No overlap)
            interval[0] > mergedInterval[1] -> {
                result.add(mergedInterval)
                mergedInterval = interval // Shift focus to the current interval
            }

            // Case 3: Intervals overlap — expand the boundaries to merge them
            else -> {
                mergedInterval[0] = min(mergedInterval[0], interval[0])
                mergedInterval[1] = max(mergedInterval[1], interval[1])

                // we don't add it here yet because it could be overlapping with remaining intervals
            }
        }
    }

    // Append the final remaining interval
    result.add(mergedInterval)

    return result.toTypedArray()
}