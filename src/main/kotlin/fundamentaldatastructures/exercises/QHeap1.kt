package fundamentaldatastructures.exercises

import java.util.PriorityQueue

/*
 * HackerRank QHEAP1
 *
 * Queries:
 * - 1 v: insert v.
 * - 2 v: delete one occurrence of v.
 * - 3: print the current minimum.
 *
 * A single PriorityQueue.remove(value) is avoided because finding and removing
 * an arbitrary value is O(n). Instead, values stores active candidates and
 * deletedValues records deletions lazily in a second min-heap.
 *
 * Before printing, matching heap heads are removed from both heaps. Cleanup at
 * the head is sufficient because only the smallest active value is requested;
 * deferred deletions deeper in the heaps cannot affect the current answer yet.
 *
 * Duplicates are supported: each type 2 query records exactly one deletion.
 *
 * Sample input:
 * 5
 * 1 4
 * 1 9
 * 3
 * 2 4
 * 3
 *
 * Sample output:
 * 4
 * 9
 *
 * Complexity:
 * - Insert: O(log n).
 * - Record deletion: O(log n).
 * - Cleanup and print: O(log n) amortized because each stored value is polled
 *   at most once from each heap.
 * - Total for q queries: O(q log q).
 * - Space: O(q).
 *
 * How to test:
 * printf '5\n1 4\n1 9\n3\n2 4\n3\n' | ./gradlew -q runMain -PmainClass=fundamentaldatastructures.exercises.QHeap1Kt
 */

class LazyDeletionMinHeap {
    private val values = PriorityQueue<Int>()
    private val deletedValues = PriorityQueue<Int>()

    fun insert(value: Int) {
        values.offer(value)
    }

    fun delete(value: Int) {
        deletedValues.offer(value)
    }

    fun minimum(): Int {
        removeDeletedHeads()
        check(values.isNotEmpty()) { "minimum requested from an empty heap" }
        return values.peek()
    }

    private fun removeDeletedHeads() {
        while (
            values.isNotEmpty() &&
            deletedValues.isNotEmpty() &&
            values.peek() == deletedValues.peek()
        ) {
            values.poll()
            deletedValues.poll()
        }
    }
}

fun main() {
    val queryCount = readln().trim().toInt()
    val heap = LazyDeletionMinHeap()

    repeat(queryCount) {
        val query = readln().trim().split(Regex("\\s+"))
        when (query[0].toInt()) {
            1 -> heap.insert(query[1].toInt())
            2 -> heap.delete(query[1].toInt())
            3 -> println(heap.minimum())
            else -> error("Unknown query type: ${query[0]}")
        }
    }
}
