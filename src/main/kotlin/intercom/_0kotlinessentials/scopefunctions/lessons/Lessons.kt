package intercom._0kotlinessentials.scopefunctions.lessons

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.expectThrows
import intercom.Harness.note
import intercom.Harness.scenario
import intercom._0kotlinessentials.scopefunctions.AgentProfile

/*
 * Part 4 – Scope functions (https://kotlinlang.org/docs/scope-functions.html)
 *
 *   ./gradlew -q runMain -PmainClass=intercom._0kotlinessentials.scopefunctions.lessons.LessonsKt
 *
 *   function   object is   returns           reach for it when…
 *   let        it          lambda result     the value may be null (?.let), or you transform a value
 *   run        this        lambda result     you compute a result from an object's members
 *   with(x)    this        lambda result     you make several calls on one object (not an extension)
 *   apply      this        the object        you configure / build an object
 *   also       it          the object        you add a side effect: log, validate, record
 *
 * Two questions pick the function:
 *   1) Do I want the OBJECT back (apply / also) or a RESULT (let / run / with)?
 *   2) Do I want `this` (members without a prefix) or `it` (an explicit name)?
 */

fun let() = scenario("1. let – null checks and transforming a value") {
    val owners = mapOf(1 to "Alice")
    expect("?.let runs only when non-null", owners[1]?.let { "Assigned to $it" }, "Assigned to Alice")
    expect("?.let { } ?: default", owners[2]?.let { "Assigned to $it" } ?: "Unassigned", "Unassigned")
    expect("returns the lambda result", "  Alice ".let { it.trim().length }, 5)
    expect("name the parameter when `it` is unclear", listOf(4, 9).let { messageCounts -> messageCounts.sum() }, 13)
}

fun run() = scenario("2. run – compute a result using `this`") {
    val alice = AgentProfile("Alice").apply { limit = 4; online = true }
    expect("members without a prefix", alice.run { "$name: ${if (online) "online" else "away"}, limit $limit" }, "Alice: online, limit 4")
    expect("?.run on a nullable", (null as AgentProfile?)?.run { name } ?: "nobody", "nobody")
    val percent = run { // non-extension run: a block that computes a value with its own local variables
        val active = 3
        val limit = 4
        active * 100 / limit
    }
    expect("plain run { } block", percent, 75)
}

fun with() = scenario("3. with – several calls on one object") {
    val summary = with(StringBuilder()) {
        append("Conversation #42")
        append(" · ")
        append("assigned to Alice")
        toString() // last expression = result
    }
    expect("with(builder)", summary, "Conversation #42 · assigned to Alice")
    val alice = AgentProfile("Alice")
    expect("with(agent)", with(alice) { "$name has ${skills.size} skills and limit $limit" }, "Alice has 0 skills and limit 2")
}

fun apply() = scenario("4. apply – configure, get the object back") {
    val alice = AgentProfile("Alice").apply {
        limit = 4
        online = true
        skills += listOf("billing", "api")
    }
    expect("configured in one expression", Triple(alice.limit, alice.online, alice.skills), Triple(4, true, listOf("billing", "api")))
    val team = mutableListOf<String>().apply {
        add("fin")
        add("Alice")
    }
    expect("build a mutable collection", team, listOf("fin", "Alice"))
}

fun also() = scenario("5. also – side effects, get the object back") {
    val auditLog = mutableListOf<String>()
    val assignee = "Alice".also { auditLog += "assigned conversation 42 to $it" }
    expect("value passes through", assignee, "Alice")
    expect("side effect happened", auditLog, listOf("assigned conversation 42 to Alice"))

    fun parseLimit(text: String) = text.trim().toInt().also { require(it >= 0) { "limit must be >= 0, was $it" } }
    expect("validate inline", parseLimit(" 3 "), 3)
    expectThrows("validation fails", IllegalArgumentException::class) { parseLimit("-1") }

    var a = 1
    var b = 2
    a = b.also { b = a }
    expect("the swap idiom", a to b, 2 to 1)
}

fun takeIf() = scenario("6. takeIf / takeUnless – keep the value only if…") {
    expect("takeIf true -> value", "42".toIntOrNull()?.takeIf { it > 0 }, 42)
    expect("takeIf false -> null", "0".toIntOrNull()?.takeIf { it > 0 }, null)
    expect("takeUnless", "  ".takeUnless { it.isBlank() } ?: "(empty message)", "(empty message)")
    expect("chain with ?.let", "Alice".takeIf { it.isNotBlank() }?.let { "Hi $it" }, "Hi Alice")
}

fun gotchas() = scenario("7. Gotchas") {
    note("apply/also return the OBJECT – using them to compute a value is a bug:")
    val wrong = "Alice".apply { length }
    val right = "Alice".run { length }
    expect("apply { length } returns \"Alice\", not 5", wrong to right, "Alice" to 5)

    note("Nested lets shadow `it`; name the parameters instead.")
    val pairs = listOf(1, 2).let { outer -> listOf("a").let { inner -> "${outer.size}${inner.size}" } }
    expect("named parameters", pairs, "21")

    note("Readability first: one scope function per expression is plenty. A plain `if` is often clearer.")
}

fun main() {
    let()
    run()
    with()
    apply()
    also()
    takeIf()
    gotchas()
    Harness.summary()
}
