package intercom._0kotlinessentials

import intercom._0kotlinessentials.solution.Agent
import intercom._0kotlinessentials.solution.Ticket
import intercom._0kotlinessentials.solution.busiestAgents
import intercom._0kotlinessentials.solution.parseLimit
import intercom._0kotlinessentials.solution.recordAssignment
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/*
 * 7. kotlin.test basics – a template you can copy for any problem.
 *
 *   ./gradlew test --tests 'intercom._0kotlinessentials.*'
 *   Report: build/reports/tests/test/index.html
 *
 * - kotlin.test is already in build.gradle.kts (testImplementation) and runs on JUnit 5.
 * - Tests live under src/test/kotlin, mirroring the package of the code they test.
 * - A new instance of the class is created for EVERY test method, so fields start fresh.
 */
class KotlinTestBasicsTest {

    private lateinit var alice: Agent

    @BeforeTest // runs before each @Test
    fun setUp() {
        alice = Agent("Alice")
    }

    @Test // backtick names read like sentences in the report
    fun `assertEquals takes EXPECTED first, then ACTUAL`() {
        assertEquals(Agent("Alice", active = 1, lastAssigned = 7), recordAssignment(alice, now = 7))
        assertEquals(0, alice.active, "copy() must not change the original") // optional message
    }

    @Test
    fun `booleans and nulls`() {
        assertTrue(alice.active < alice.limit)
        assertFalse(alice.active > 0)
        assertNull(mapOf(1 to "Alice")[2])
        // assertNotNull returns the value as non-null, so you can keep using it.
        val owner: String = assertNotNull(mapOf(1 to "Alice")[1])
        assertEquals(5, owner.length)
    }

    @Test
    fun `assertFailsWith returns the exception so you can check the message`() {
        val error = assertFailsWith<IllegalArgumentException> { parseLimit("-1") }
        assertEquals("limit must be >= 0, was -1", error.message)

        val ticket = Ticket()
        ticket.close()
        assertFailsWith<IllegalStateException> { ticket.close() }
    }

    @Test
    fun `lists compare by content, arrays need assertContentEquals`() {
        assertEquals(listOf("Bob", "Dan"), busiestAgents(mapOf("Alice" to 5, "Bob" to 9, "Dan" to 7), 2))
        assertContentEquals(intArrayOf(1, 2, 3), intArrayOf(1, 2, 3)) // assertEquals would compare references
    }

    @Test
    fun `maps compare by content, regardless of order`() {
        assertEquals(mapOf("a" to 1, "b" to 2), linkedMapOf("b" to 2, "a" to 1))
    }

    @Test
    fun `table-driven test - many cases, one assertion`() {
        val cases = mapOf(" 4 " to 4, "0" to 0, "12" to 12)
        for ((input, expected) in cases) {
            assertEquals(expected, parseLimit(input), "input='$input'")
        }
    }
}
