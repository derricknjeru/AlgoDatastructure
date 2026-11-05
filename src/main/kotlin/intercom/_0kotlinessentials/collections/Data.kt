package intercom._0kotlinessentials.collections

/*
 * Shared sample data for Part 2 – Collections.
 *
 *  id | customer | assignee | channel  | messages | rating
 *   1 | acme     | Alice    | CHAT     |    4     |   5
 *   2 | globex   | Bob      | EMAIL    |    9     |   3
 *   3 | acme     | Alice    | WHATSAPP |    2     |   -
 *   4 | initech  | -        | CHAT     |    1     |   -
 *   5 | globex   | Bob      | CHAT     |    6     |   4
 *   6 | acme     | Carol    | EMAIL    |   12     |   2
 */

enum class Channel { CHAT, EMAIL, WHATSAPP }

data class Conversation(
    val id: Int,
    val customer: String,
    val assignee: String?,
    val channel: Channel,
    val messages: Int,
    val rating: Int?,
)

val CONVERSATIONS = listOf(
    Conversation(1, "acme", "Alice", Channel.CHAT, messages = 4, rating = 5),
    Conversation(2, "globex", "Bob", Channel.EMAIL, messages = 9, rating = 3),
    Conversation(3, "acme", "Alice", Channel.WHATSAPP, messages = 2, rating = null),
    Conversation(4, "initech", null, Channel.CHAT, messages = 1, rating = null),
    Conversation(5, "globex", "Bob", Channel.CHAT, messages = 6, rating = 4),
    Conversation(6, "acme", "Carol", Channel.EMAIL, messages = 12, rating = 2),
)

fun List<Conversation>.ids(): List<Int> = map { it.id }
