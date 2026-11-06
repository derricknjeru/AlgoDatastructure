package fundamentaldatastructures.exercises

import java.util.PriorityQueue

fun main() {

}

fun topKFrequent(words: Array<String>, k: Int): List<String> {
    // Count how often each word appears in the full input.
    val occurrenceMap = mutableMapOf<String, Int>()
    for (word in words) {
        occurrenceMap[word] = occurrenceMap.getOrDefault(word, 0) + 1
    }

    /*
     * Poll order matches the required result:
     * 1. compareByDescending places higher-frequency words first.
     * 2. thenBy places lexicographically smaller words first when counts tie.
     *
     * For example, if "i" and "love" both occur twice, "i" comes first.
     */

    val heap = PriorityQueue(
        compareByDescending<String> { occurrenceMap.getValue(it) }
            .thenBy { it })

    // Only distinct words need ordering because each word appears once in the result.
    heap.addAll(occurrenceMap.keys)

    return List(k) { heap.poll() }
}

fun topKFrequent2(words: Array<String>, k: Int): List<String> {
    // Build the same frequency table directly with Kotlin collection helpers.
    val occurrenceMap = words.groupingBy { it }.eachCount()

    /*
     * Sort only the d distinct words, not all n input entries.
     * Higher frequency comes first; equal frequencies use ascending word order.
     */
    val res = occurrenceMap.keys
        .sortedWith(compareByDescending<String> { occurrenceMap[it] }.thenBy { it })

    // take(k) returns the first k ranked words without changing their order.
    return res.take(k)
}

/*
 * Direct-sort complexity for topKFrequent2:
 *
 * n = total words, d = distinct words, L = word-comparison cost.
 * - Frequency counting: O(n) expected time.
 * - Sorting distinct words: O(d log d * L) comparison-cost time.
 * - Auxiliary space: O(d) for the frequency map and sorted distinct-word list,
 *   excluding the returned output.
 *
 * The heap version also counts in O(n), stores O(d) distinct words, inserts
 * them in O(d log d * L), and polls k results in O(k log d * L).
 */