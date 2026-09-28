package leetcode.stack

import kotlin.collections.sortByDescending

fun main() {

}
// https://www.youtube.com/watch?v=Pr6T-3yB9RM
fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
    // Pair = (position, speed)
    // Sort cars from closest to target → farthest from target
    val cars = mutableListOf<Pair<Int, Double>>()

    for (i in position.indices) {
        // Time needed for this car to reach the target
        val time = (target - position[i]).toDouble() / speed[i]
        cars.add(Pair(position[i], time))
    }

    cars.sortByDescending { it.first }

    var fleets = 0
    var slowestTime = 0.0

    for ((_, time) in cars) {

        // If this car takes longer than the fleet ahead,
        // it cannot catch that fleet → it forms a new fleet.
        if (time > slowestTime) {
            fleets++
            slowestTime = time
        }

        // Otherwise, it catches the fleet ahead and becomes
        // part of that fleet.
    }

    return fleets
}

// Time: O(n log n)  -> sorting the cars
// Space: O(n)       -> storing the cars


fun carFleet2(target: Int, position: IntArray, speed: IntArray): Int {
    val cars = position.indices
        .map { position[it] to (target - position[it]).toDouble() / speed[it] }
        .sortedByDescending { it.first }

    var fleets = 0
    var slowestArrivalTime = 0.0

    for ((_, arrivalTime) in cars) {
        if (arrivalTime > slowestArrivalTime) {
            fleets++
            slowestArrivalTime = arrivalTime
        }
    }


    return fleets
}


fun carFleet3(target: Int, position: IntArray, speed: IntArray): Int {
    val n = position.size
    if (n == 0) return 0

    // Pair each position with its calculated time to target
    val cars = Array(n) { i ->
        val distance = target - position[i]
        val time = distance.toDouble() / speed[i]
        Pair(position[i], time)
    }

    // Sort cars by position in descending order (closest to target first)
    cars.sortByDescending { it.first }

    var fleetCount = 0
    var currentFleetMaxTime = 0.0

    for ((_, carTime) in cars) {
        // If this car takes strictly longer than the fleet ahead,
        // it cannot catch up and starts a new fleet.
        if (carTime > currentFleetMaxTime) {
            fleetCount++
            currentFleetMaxTime = carTime
        }
    }

    return fleetCount
}




fun carFleet4(target: Int, position: IntArray, speed: IntArray): Int {
    val n = position.size
    if (n <= 1) return n

    // Pair positions with their calculated arrival time
    val cars = Array(n) { i ->
        val distance = (target - position[i]).toDouble()
        val time = distance / speed[i]
        Pair(position[i], time)
    }

    // Sort by starting position descending (closest to target first)
    cars.sortByDescending { it.first }

    val stack = ArrayDeque<Double>()

    for (car in cars) {
        val currentTime = car.second
        stack.addLast(currentTime)

        // If current car arrives faster than or at the same time as
        // the fleet ahead of it, it gets absorbed into that fleet.
        if (stack.size >= 2) {
            val current = stack.removeLast()
            val fleetAhead = stack.last()

            // If current car takes LONGER, it cannot catch up and forms its own fleet.
            // We push it back onto the stack.
            if (current > fleetAhead) {
                stack.addLast(current)
            }
        }
    }

    return stack.size
}


fun carFleet5(target: Int, position: IntArray, speed: IntArray): Int {

    val cars = position.indices
        .map { position[it] to (target - position[it]).toDouble() / speed[it] }.toMutableList()
   cars.sortByDescending { it.first }

    var fleets = 0
    var slowestArrivalTime = 0.0

    for ((_, arrivalTime) in cars) {
        if (arrivalTime > slowestArrivalTime) {
            fleets++
            slowestArrivalTime = arrivalTime
        }
    }


    return fleets
}
