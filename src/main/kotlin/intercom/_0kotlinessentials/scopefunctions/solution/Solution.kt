package intercom._0kotlinessentials.scopefunctions.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario
import intercom._0kotlinessentials.scopefunctions.AgentProfile

/* Answers to ../practice/Practice.kt (Part 4 – Scope functions). Try the drills first. */

// 1. takeIf + ?.let + ?:
fun greeting(name: String?): String =
    name?.trim()?.takeIf { it.isNotEmpty() }?.let { "Hi $it" } ?: "Hi there"

// 2. apply: configure and return the same object
fun newAgent(name: String, skills: List<String>): AgentProfile =
    AgentProfile(name).apply {
        limit = 3
        online = true
        this.skills += skills // `this.` because the parameter has the same name
    }

// 3. also: side effect, value passes through
fun assignAndLog(agent: String, log: MutableList<String>): String =
    agent.also { log += "assigned to $it" }

// 4. toIntOrNull + takeIf
fun parsePositive(text: String): Int? = text.trim().toIntOrNull()?.takeIf { it > 0 }

// 5. with: several reads from one object
fun statusLine(agent: AgentProfile): String = with(agent) {
    "$name: ${if (online) "online" else "away"}, limit $limit, skills ${skills.joinToString("+")}"
}

// 6. run: compute from the object's members
fun loadPercent(agent: AgentProfile, active: Int): Int = agent.run {
    require(limit > 0) { "limit must be > 0" }
    active * 100 / limit
}

fun main() {
    scenario("Scope function drills") {
        expect("1. greeting(Alice)", greeting("Alice"), "Hi Alice")
        expect("1. greeting(null)", greeting(null), "Hi there")
        expect("1. greeting(blank)", greeting("   "), "Hi there")

        val agent = newAgent("Alice", listOf("billing", "api"))
        expect("2. newAgent", Triple(agent.limit, agent.online, agent.skills), Triple(3, true, listOf("billing", "api")))

        val log = mutableListOf<String>()
        expect("3. assignAndLog returns the agent", assignAndLog("Alice", log), "Alice")
        expect("3. … and logs", log, listOf("assigned to Alice"))

        expect("4. parsePositive", listOf("4", " 7 ", "0", "x").map { parsePositive(it) }, listOf(4, 7, null, null))
        expect("5. statusLine", statusLine(agent), "Alice: online, limit 3, skills billing+api")
        expect("6. loadPercent", loadPercent(newAgent("Bob", emptyList()).apply { limit = 4 }, active = 3), 75)
    }
    Harness.summary()
}
