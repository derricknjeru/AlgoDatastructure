package fundamentaldatastructures.exercises

import java.util.PriorityQueue


fun runningMedian(a: Array<Int>): Array<Double> {

    // Max heap: stores the smaller half.
    val small = PriorityQueue<Int>(compareByDescending { it })

    // Min heap: stores the larger half.
    val large = PriorityQueue<Int>()

    val result = ArrayList<Double>()

    for (num in a) {

        // Step 1: Insert into the smaller half.
        small.offer(num)

        // Step 2: Maintain ordering between the heaps.
        if (large.isNotEmpty() && small.peek() > large.peek()) {
            large.offer(small.poll())
        }

        // Step 3: Balance the heap sizes.
        if (small.size > large.size + 1) {
            large.offer(small.poll())
        } else if (large.size > small.size + 1) {
            small.offer(large.poll())
        }

        // Step 4: Calculate the current median.
        val median = when {
            small.size > large.size -> small.peek().toDouble()

            large.size > small.size -> large.peek().toDouble()

            else -> (small.peek().toDouble() + large.peek().toDouble()) / 2.0
        }

        result.add(median)
    }

    return result.toTypedArray()
}

/*
Time Complexity:
- Each insertion: O(log n)
- Each median calculation: O(1)
- Overall: O(n log n)

Space Complexity:
- O(n), for the two heaps and result array.
*/
