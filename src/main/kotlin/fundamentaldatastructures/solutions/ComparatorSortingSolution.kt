package fundamentaldatastructures.solutions

import java.util.Comparator

data class ComparatorSolutionPlayer(
    val name: String,
    val score: Int
)

class ComparatorSolutionChecker : Comparator<ComparatorSolutionPlayer> {
    override fun compare(
        first: ComparatorSolutionPlayer,
        second: ComparatorSolutionPlayer
    ): Int {
        val scoreComparison = compareValues(second.score, first.score)
        return if (scoreComparison != 0) {
            scoreComparison
        } else {
            first.name.compareTo(second.name)
        }
    }

}

/*
 * Time: O(n log n) for sorting; each comparator call is O(1).
 * Space: O(n) for the returned list, plus implementation-dependent
 * auxiliary space used by the sorting algorithm.
 */
fun sortPlayersReference(
    players: List<ComparatorSolutionPlayer>
): List<ComparatorSolutionPlayer> =
    players.sortedWith(ComparatorSolutionChecker())

fun main() {
    val stack = ArrayDeque<Char>()
}