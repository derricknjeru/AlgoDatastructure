package fundamentaldatastructures.exercises

import java.util.PriorityQueue


class MedianFinder() {

    // Max heap: stores the smaller half of numbers.
    // The largest number in this half is always at the top.
    private val small = PriorityQueue<Int>(compareByDescending { it })

    // Min heap: stores the larger half of numbers.
    // The smallest number in this half is always at the top.
    private val large = PriorityQueue<Int>()

    fun addNum(num: Int) {

        // Step 1: Add to the smaller half.
        small.offer(num)

        // Step 2: Ensure every number in small <= every number in large.
        if (large.isNotEmpty() && small.peek() > large.peek()) {
            large.offer(small.poll())
        }

        // Step 3: Balance heap sizes.
        if (small.size > large.size + 1) {
            large.offer(small.poll())
        } else if (large.size > small.size + 1) {
            small.offer(large.poll())
        }
    }

    fun findMedian(): Double {

        // Odd number of elements.
        if (small.size > large.size) {
            return small.peek().toDouble()
        }

        if (large.size > small.size) {
            return large.peek().toDouble()
        }

        // Even number of elements.
        return (small.peek().toDouble() + large.peek().toDouble()) / 2.0
    }
}

/*
Time Complexity:
- addNum(): O(log n)
- findMedian(): O(1)

Space Complexity:
- O(n), for storing all numbers.
*/



class MedianFinder2() {

    // Max heap: stores the smaller half of numbers.
    // The largest number in this half is always at the top.
    private val small = PriorityQueue<Int>(compareByDescending { it })

    // Min heap: stores the larger half of numbers.
    // The smallest number in this half is always at the top.
    private val large = PriorityQueue<Int>()

    fun addNum(num: Int) {

        // Step 1: Add to the smaller half.
        small.offer(num)

        // Step 2: Ensure every number in small <= every number in large.
        if (large.isNotEmpty() && small.peek() > large.peek()) {
            large.offer(small.poll())
        }

        // Step 3: Balance heap sizes.
        if (small.size > large.size + 1) {
            large.offer(small.poll())
        } else if (large.size > small.size + 1) {
            small.offer(large.poll())
        }
    }

    fun findMedian(): Double {
        // Odd number of elements.
        return when {
            small.size > large.size -> small.peek().toDouble()
            large.size > small.size -> return large.peek().toDouble()
            else -> (small.peek().toDouble() + large.peek().toDouble()) / 2.0
        }
    }
}

/*
Time Complexity:
- addNum(): O(log n)
- findMedian(): O(1)

Space Complexity:
- O(n), for storing all numbers.
*/