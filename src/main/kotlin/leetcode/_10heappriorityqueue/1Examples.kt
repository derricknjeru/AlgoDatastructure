package leetcode._10heappriorityqueue

import java.util.PriorityQueue

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val minHeap = PriorityQueue<Int>()
    list.forEach { minHeap.add(it) }
    println(minHeap.size)

    var prefix = ""
    while (minHeap.isNotEmpty()) {
        print("$prefix${minHeap.poll()} ")
        prefix = ","
    }

    val maxHeap = PriorityQueue<Int>(reverseOrder())
    val maxHeap2 = PriorityQueue<Int>(compareByDescending { it })
    val maxHeap3 =
        PriorityQueue<Int> { a, b -> b.compareTo(a) } // avoid negatives because thy can overflow as in: a, b -> b - a)

    list.forEach { maxHeap.add(it) }
    list.forEach { maxHeap2.add(it) }
    maxHeap3.addAll(maxHeap)
    println()

    prefix = ""
    while (maxHeap3.isNotEmpty()) {
        print("$prefix${maxHeap3.poll()}")
        prefix = ","
    }

    //4. Priority Queue with Custom Objects

    data class Job(val name: String, val priority: Int)

    val jobsMinHeap = PriorityQueue<Job>(compareBy { it.priority })
    jobsMinHeap.offer(Job("waiter", 5))
    jobsMinHeap.offer(Job("chef", 2))
    jobsMinHeap.offer(Job("Waiter", 6))
    println("\n ---minHeap---")
    while (jobsMinHeap.isNotEmpty()) {
        println("${jobsMinHeap.poll()} ")
    }

    val jobsMaxHeap = PriorityQueue<Job>(compareByDescending { it.priority })
    jobsMaxHeap.offer(Job("waiter", 5))
    jobsMaxHeap.offer(Job("chef", 2))
    jobsMaxHeap.offer(Job("Waiter", 6))
    println("---maxHeap---")

    while (jobsMaxHeap.isNotEmpty()) {
        println("${jobsMaxHeap.poll()} ")
    }

    // 5. Comparing Multiple Properties
    val jobsMaxHeap2 = PriorityQueue<Job>(compareByDescending<Job> { it.priority }.thenBy { it.name })
    val jobsMinHeap2 = PriorityQueue<Job>(compareBy<Job> { it.priority }.thenBy { it.name })

    val numbers = intArrayOf(7, 2, 9, 4, 1, 8)

    println(kLargest(numbers, 3))

}

fun kLargest(numbers: IntArray, k: Int): List<Int> {
    require(k in 1..numbers.size)

    val minHeap = PriorityQueue<Int>()

    for (number in numbers) {
        minHeap.offer(number)

        if (minHeap.size > k) {
            minHeap.poll()
        }
    }
    val list = minHeap.toList()
    val top = list.first()
    return list
}