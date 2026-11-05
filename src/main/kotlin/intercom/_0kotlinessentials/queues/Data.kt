package intercom._0kotlinessentials.queues

/* Shared data for Part 3 – Queues, stacks and deques. */

data class Ticket(val id: Int, val priority: Int, val arrivedAt: Int) // priority 1 = most urgent

val TICKETS = listOf(
    Ticket(1, priority = 2, arrivedAt = 0),
    Ticket(2, priority = 1, arrivedAt = 1),
    Ticket(3, priority = 2, arrivedAt = 2),
    Ticket(4, priority = 1, arrivedAt = 3),
    Ticket(5, priority = 3, arrivedAt = 4),
)

/** Help-center article -> articles it links to (directed). */
val RELATED_ARTICLES = mapOf(
    "reset-password" to listOf("account-locked", "two-factor"),
    "account-locked" to listOf("contact-support"),
    "two-factor" to listOf("authenticator-app", "contact-support"),
    "authenticator-app" to emptyList(),
    "contact-support" to emptyList(),
)
