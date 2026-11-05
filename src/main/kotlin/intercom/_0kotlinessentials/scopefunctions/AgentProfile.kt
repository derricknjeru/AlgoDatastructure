package intercom._0kotlinessentials.scopefunctions

/* Shared type for Part 4 – Scope functions. Mutable on purpose: apply/also/with shine on mutable objects. */
class AgentProfile(val name: String) {
    var limit = 2
    var online = false
    val skills = mutableListOf<String>()
}
