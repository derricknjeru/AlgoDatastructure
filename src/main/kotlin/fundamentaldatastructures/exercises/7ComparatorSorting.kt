package fundamentaldatastructures.exercises

import java.util.Comparator
import java.util.PriorityQueue

/*
 * Comparator Sorting
 *
 * Sort players by score from highest to lowest. When two players have the
 * same score, sort their names in ascending alphabetical order.
 *
 * Constraints:
 * - 0 <= score <= 1000
 * - Duplicate names are allowed.
 * - Names contain lowercase English letters.
 *
 * Sample input:
 * 5
 * amy 100
 * david 100
 * heraldo 50
 * aakansha 75
 * aleksa 150
 *
 * Sample output:
 * aleksa 150
 * amy 100
 * david 100
 * aakansha 75
 * heraldo 50
 *
 * Complexity target:
 * - Sorting: O(n log n), excluding input and output.
 * - Each comparator call: O(1).
 */

/*
 * How to test
 *
 * Run from the repository root:
 * /Users/derrick/AlgoDatastructure
 *
 * Compile:
 * ./gradlew -q compileKotlin
 *
 * Run with the sample input:
 * printf '5\namy 100\ndavid 100\nheraldo 50\naakansha 75\naleksa 150\n' | ./gradlew -q runMain -PmainClass=fundamentaldatastructures.exercises.ComparatorSortingKt
 *
 * Expected output:
 * aleksa 150
 * amy 100
 * david 100
 * aakansha 75
 * heraldo 50
 *
 * Implement the TODO comparator before running unless it has already been filled in.
 */

data class Player(
    val name: String,
    val score: Int
)

class Checker : Comparator<Player> {
    override fun compare(first: Player, second: Player): Int {
        val compareValues = compareValues(second.score, first.score)
        return if (compareValues != 0) {
            compareValues
        } else {
            first.name.compareTo(second.name, ignoreCase = true)
        }
    }
    fun test(){
        val minHeap = PriorityQueue(compareBy<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
        for((value, key) in minHeap) {

        }
    }

}


fun main() {
    val players = listOf(
        Player("James", 13),
        Player("Mary", 20),
        Player("John", 100),
        Player("Joseph", 13),
    ).toTypedArray()

    players.sortWith(Checker())
    for ((name, score) in players) {
        println("$name $score")
    }
}
