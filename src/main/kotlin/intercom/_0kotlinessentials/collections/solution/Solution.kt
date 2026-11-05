package intercom._0kotlinessentials.collections.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario
import intercom._0kotlinessentials.collections.CONVERSATIONS
import intercom._0kotlinessentials.collections.Channel
import intercom._0kotlinessentials.collections.Conversation

/* Answers to ../practice/Practice.kt (Part 2 – Collections). Try the drills first. */

// 1. filter + map + distinct + sorted
fun customersWithAssignedConversations(conversations: List<Conversation>): List<String> =
    conversations.filter { it.assignee != null }.map { it.customer }.distinct().sorted()

// 2. filter + map
fun unratedIds(conversations: List<Conversation>): List<Int> =
    conversations.filter { it.rating == null }.map { it.id }

// 3. Comparator for a nullable key: best first, nulls last.
fun bestRatedFirst(conversations: List<Conversation>): List<Int> =
    conversations.sortedWith(compareBy<Conversation, Int?>(nullsLast(reverseOrder())) { it.rating }).map { it.id }

// 4. joinToString with limit + elvis
fun summaryLine(conversations: List<Conversation>): String =
    conversations.joinToString(limit = 3) { "#${it.id} ${it.assignee ?: "unassigned"}" }

// 5. associate
fun messagesById(conversations: List<Conversation>): Map<Int, Int> =
    conversations.associate { it.id to it.messages }

// 6. mapNotNull + takeIf – average() of an empty list is NaN, so return null instead.
fun averageRating(conversations: List<Conversation>): Double? =
    conversations.mapNotNull { it.rating }.takeIf { it.isNotEmpty() }?.average()

// 7. mapNotNull to drop unassigned (no !!), groupingBy + fold, maxByOrNull
fun busiestAssignee(conversations: List<Conversation>): String? =
    conversations
        .mapNotNull { conv -> conv.assignee?.let { it to conv.messages } }
        .groupingBy { it.first }
        .fold(0) { total, (_, messages) -> total + messages }
        .maxByOrNull { it.value }
        ?.key

// 8. runningReduce = prefix sums
fun cumulativeMessages(conversations: List<Conversation>): List<Int> =
    conversations.map { it.messages }.runningReduce { total, m -> total + m }

// 9. set operations
fun chatOnlyCustomers(conversations: List<Conversation>): Set<String> {
    fun customersOn(channel: Channel) = conversations.filter { it.channel == channel }.map { it.customer }.toSet()
    return customersOn(Channel.CHAT) subtract (customersOn(Channel.EMAIL) union customersOn(Channel.WHATSAPP))
}

// 10. single elements
fun firstUnassignedId(conversations: List<Conversation>): Int? = conversations.firstOrNull { it.assignee == null }?.id

fun longestConversationId(conversations: List<Conversation>): Int? = conversations.maxByOrNull { it.messages }?.id

// 11. collection parts
fun pages(ids: List<Int>, pageSize: Int): List<List<Int>> = ids.chunked(pageSize)

fun busiestWindow(messagesPerMinute: List<Int>, k: Int): Int? = messagesPerMinute.windowed(k) { it.sum() }.maxOrNull()

// 12. map-specific: groupBy with a value transform, then clean the values
fun ratingsByCustomer(conversations: List<Conversation>): Map<String, List<Int>> =
    conversations
        .groupBy({ it.customer }, { it.rating })
        .mapValues { (_, ratings) -> ratings.filterNotNull() }
        .filterValues { it.isNotEmpty() }

// 13. sequences: stop as soon as n are found
fun firstBigConversationIds(conversations: List<Conversation>, minMessages: Int, n: Int): List<Int> =
    conversations.asSequence().filter { it.messages >= minMessages }.map { it.id }.take(n).toList()

// 14. ranges
fun uncoveredHours(covered: List<IntRange>): List<Int> =
    (0..23).filter { hour -> covered.none { hour in it } }

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
