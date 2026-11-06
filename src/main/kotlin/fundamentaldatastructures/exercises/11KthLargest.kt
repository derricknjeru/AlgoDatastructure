package fundamentaldatastructures.exercises

import java.util.PriorityQueue

fun main() {

}

class KthLargest(val k: Int, nums: IntArray) {
    val minHeap = PriorityQueue<Int>()

    init{
        for(n in nums){
            add(n)
        }
    }

    fun add(`val`: Int): Int {
        minHeap.add(`val`)
        if(minHeap.size >k){
            minHeap.poll()
        }
        return minHeap.peek() ?: -1
    }

}