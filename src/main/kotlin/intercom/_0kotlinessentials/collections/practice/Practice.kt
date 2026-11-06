package intercom._0kotlinessentials.collections.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario
import intercom._0kotlinessentials.collections.CONVERSATIONS
import intercom._0kotlinessentials.collections.Conversation

/*
 * Part 2 – Collections drills. Data table: ../Data.kt. Aim for one expression per drill.
 * Answers: ../solution/Solution.kt
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.collections.practice.PracticeKt
 */

// 1. Customers that have at least one ASSIGNED conversation, distinct, alphabetical.
fun customersWithAssignedConversations(conversations: List<Conversation>): List<String> =
    conversations
        .filter { it.assignee != null }
        .map { it.customer }
        .distinct()
        .sorted()

// 2. Ids of conversations without a rating.
fun unratedIds(conversations: List<Conversation>): List<Int> = conversations.filter { it.rating == null }.map { it.id }

// 3. Ids ordered by rating, best first; unrated last (keep their original order).
fun bestRatedFirst(conversations: List<Conversation>): List<Int> =
    conversations.sortedWith(compareBy(nullsLast(reverseOrder())) { it.rating }).map { it.id }

// 4. "#1 Alice, #2 Bob, #3 Alice, ..." – first 3 only, "unassigned" for null.
fun summaryLine(conversations: List<Conversation>): String =
    conversations.joinToString(limit = 3) { "#${it.id} ${it.assignee ?: "unassigned"}" }

// 5. id -> messages
fun messagesById(conversations: List<Conversation>): Map<Int, Int> =
    conversations
        .associate { it.id to it.messages }

// 6. Average of the ratings that exist; null when there are none.
fun averageRating(conversations: List<Conversation>): Double? = TODO("6. mapNotNull/average")

// 7. Assignee with the most messages in total (ignore unassigned).
fun busiestAssignee(conversations: List<Conversation>): String? = TODO("7. groupingBy/fold/maxByOrNull")

// 8. Running total of messages.
fun cumulativeMessages(conversations: List<Conversation>): List<Int> = TODO("8. runningReduce")

// 9. Customers who used CHAT but never EMAIL or WHATSAPP.
fun chatOnlyCustomers(conversations: List<Conversation>): Set<String> = TODO("9. union/subtract")

// 10. First unassigned conversation id; id of the conversation with the most messages.
fun firstUnassignedId(conversations: List<Conversation>): Int? = TODO("10a. firstOrNull")

fun longestConversationId(conversations: List<Conversation>): Int? = TODO("10b. maxByOrNull")

// 11. Split into pages; the biggest sum over any k consecutive minutes.
fun pages(ids: List<Int>, pageSize: Int): List<List<Int>> = TODO("11a. chunked")

fun busiestWindow(messagesPerMinute: List<Int>, k: Int): Int? = TODO("11b. windowed")

// 12. customer -> its non-null ratings; drop customers with no ratings.
fun ratingsByCustomer(conversations: List<Conversation>): Map<String, List<Int>> =
    TODO("12. groupBy/mapValues/filterValues")

// 13. Ids of the first n conversations with at least minMessages – don't process the rest.
fun firstBigConversationIds(conversations: List<Conversation>, minMessages: Int, n: Int): List<Int> =
    TODO("13. asSequence")

// 14. Hours 0..23 not inside any covered range.
fun uncoveredHours(covered: List<IntRange>): List<Int> = TODO("14. ranges")

fun main() {
    val c = CONVERSATIONS
    scenario("Filter / order / transform") {
        expect("1. customersWithAssignedConversations", customersWithAssignedConversations(c), listOf("acme", "globex"))
        expect("2. unratedIds", unratedIds(c), listOf(3, 4))
        expect("3. bestRatedFirst", bestRatedFirst(c), listOf(1, 5, 2, 6, 3, 4))
        expect("4. summaryLine", summaryLine(c), "#1 Alice, #2 Bob, #3 Alice, ...")
        expect("5. messagesById", messagesById(c), mapOf(1 to 4, 2 to 9, 3 to 2, 4 to 1, 5 to 6, 6 to 12))
    }
    scenario("Aggregate / combine") {
        expect("6. averageRating", averageRating(c), 3.5)
        expect("6. averageRating(empty)", averageRating(emptyList()), null)
        expect("7. busiestAssignee", busiestAssignee(c), "Bob")
        expect("8. cumulativeMessages", cumulativeMessages(c), listOf(4, 13, 15, 16, 22, 34))
        expect("9. chatOnlyCustomers", chatOnlyCustomers(c), setOf("initech"))
    }
    scenario("Retrieve") {
        expect("10. firstUnassignedId", firstUnassignedId(c), 4)
        expect("10. longestConversationId", longestConversationId(c), 6)
        expect("11. pages", pages((1..6).toList(), 4), listOf(listOf(1, 2, 3, 4), listOf(5, 6)))
        expect("11. busiestWindow(k=2)", busiestWindow(listOf(4, 9, 2, 1, 6, 12), 2), 18)
    }
    scenario("Map / sequences / ranges") {
        expect("12. ratingsByCustomer", ratingsByCustomer(c), mapOf("acme" to listOf(5, 2), "globex" to listOf(3, 4)))
        expect("13. firstBigConversationIds", firstBigConversationIds(c, minMessages = 5, n = 2), listOf(2, 5))
        expect("14. uncoveredHours", uncoveredHours(listOf(8..12, 14..17)), (0..7).toList() + 13 + (18..23))
    }
    Harness.summary()
}
