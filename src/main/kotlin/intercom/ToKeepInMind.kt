package intercom

import java.util.PriorityQueue

fun main() {
    // 1 ->
    val array = IntArray(26)
    val c = 'b'
    array[c - 'a']++

    // Map ->
    val map = mutableMapOf<String, MutableList<String>>()
    map.getOrPut("") { mutableListOf() }.add("")

    // when checking a key of the map
    map.containsKey("")
    // or
    map[""]?.let { /* it is equal to value */ } //e.g. seen[need]?.let { return intArrayOf(it, i) }

    // vararg is allowed only inside a parenthesized argument list
    // like intArrayOf(it, i) not intArrayOf {it, i}
    intArrayOf(1, 6) // not intArrayOf {it, i}

    val nums = IntArray(26)
    nums.forEachIndexed { index, num ->
        println("$index -> $num")
    }

    val best = compareByDescending<Map.Entry<String, Int>> { it.value }
        .thenBy { it.key }

    val minHeap = PriorityQueue(best.reversed())

    // gives a list
    minHeap.sortedWith(best).map { it.key }

    val agents = listOf("Alice", "Bob")
    for (i in agents.lastIndex downTo 0) println(agents[i])
    for ((index, agent) in agents.withIndex()) println("$index -> $agent")
    agents.forEachIndexed { index, agent ->
        println("$index -> $agent")
    }


    for(i in nums.indices){ }
}