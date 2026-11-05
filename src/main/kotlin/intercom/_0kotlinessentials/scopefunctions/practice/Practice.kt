package intercom._0kotlinessentials.scopefunctions.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario
import intercom._0kotlinessentials.scopefunctions.AgentProfile

/*
 * Part 4 – Scope function drills. Use the function named in each TODO. Answers: ../solution/Solution.kt
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.scopefunctions.practice.PracticeKt
 */

// 1. "Hi <trimmed name>", or "Hi there" when the name is null or blank.
fun greeting(name: String?): String = TODO("1. takeIf + ?.let + ?:")

// 2. New agent: limit 3, online, with the given skills.
fun newAgent(name: String, skills: List<String>): AgentProfile = TODO("2. apply")

// 3. Return the agent unchanged, after adding "assigned to <agent>" to the log.
fun assignAndLog(agent: String, log: MutableList<String>): String = TODO("3. also")

// 4. The number if it's a positive integer (spaces allowed), else null.
fun parsePositive(text: String): Int? = TODO("4. toIntOrNull + takeIf")

// 5. "Alice: online, limit 3, skills billing+api" ("away" when offline).
fun statusLine(agent: AgentProfile): String = TODO("5. with")

// 6. active * 100 / limit
fun loadPercent(agent: AgentProfile, active: Int): Int = TODO("6. run")

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
