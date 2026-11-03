package intercom._1agentassignment

import intercom._1agentassignment.solution.AssignmentSystem
import intercom._1agentassignment.solution.ConversationAssigner
import intercom._1agentassignment.solution.TreeSetAssignmentSystem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AgentAssignmentTest {

    private val factories: List<(List<String>) -> ConversationAssigner> = listOf(
        { AssignmentSystem(it) },
        { TreeSetAssignmentSystem(it) },
    )

    private fun forEachImpl(block: (create: (List<String>) -> ConversationAssigner) -> Unit) =
        factories.forEach(block)

    @Test
    fun `reported example`() = forEachImpl { create ->
        val system = create(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Bob", 4)
        system.setLimit("Charlie", 3)

        assertEquals(listOf("Alice", "Bob", "Charlie", "Alice"), system.previewAssignments(4))
        assertEquals(listOf("Alice", "Bob", "Charlie", "Alice"), listOf(101, 102, 103, 104).map { system.assign(it) })
        assertEquals(listOf("Bob", "Charlie", "Bob", "Charlie", "Bob"), system.previewAssignments(5))
    }

    @Test
    fun `preview does not mutate and matches the next assigns`() = forEachImpl { create ->
        val system = create(listOf("A", "B", "C"))
        system.setLimit("C", 4)
        val preview = system.previewAssignments(8)
        assertEquals(preview, system.previewAssignments(8))
        assertEquals(preview, (1..8).map { system.assign(it) })
        assertNull(system.assign(9))
    }

    @Test
    fun `close conversation extension picks the longest-waiting tied agent`() = forEachImpl { create ->
        val system = create(listOf("Alice", "Bob", "Charlie"))
        system.setLimit("Alice", 2)
        system.setLimit("Bob", 3)
        system.setLimit("Charlie", 2)
        listOf(101, 102, 103, 104).forEach { system.assign(it) }

        assertTrue(system.closeConversation(101))
        assertEquals("Bob", system.assign(105))
        assertFalse(system.closeConversation(101))
    }

    @Test
    fun `returns null and empty preview when nobody has capacity`() = forEachImpl { create ->
        val system = create(listOf("Alice"))
        system.setLimit("Alice", 0)
        assertNull(system.assign(1))
        assertEquals(emptyList(), system.previewAssignments(3))
    }

    @Test
    fun `lowering a limit keeps existing work but blocks new work`() = forEachImpl { create ->
        val system = create(listOf("Alice", "Bob"))
        system.setLimit("Bob", 5)
        listOf(1, 2, 3).forEach { system.assign(it) } // Alice, Bob, Alice
        system.setLimit("Alice", 1)
        assertEquals("Bob", system.assign(4))
        system.closeConversation(1)
        assertEquals("Bob", system.assign(5))
        system.closeConversation(3)
        assertEquals("Alice", system.assign(6))
    }

    @Test
    fun `assign is idempotent per conversation id`() = forEachImpl { create ->
        val system = create(listOf("Alice", "Bob"))
        assertEquals("Alice", system.assign(1))
        assertEquals("Alice", system.assign(1))
        assertEquals("Bob", system.assign(2))
    }

    @Test
    fun `offline agents are skipped`() = forEachImpl { create ->
        val system = create(listOf("Alice", "Bob"))
        system.setOnline("Alice", false)
        assertEquals(listOf("Bob", "Bob"), system.previewAssignments(5))
        system.setOnline("Alice", true)
        assertEquals("Alice", system.assign(1))
    }

    @Test
    fun `rejects unknown agents and bad limits`() = forEachImpl { create ->
        val system = create(listOf("Alice"))
        assertFailsWith<IllegalArgumentException> { system.setLimit("Zed", 1) }
        assertFailsWith<IllegalArgumentException> { system.setLimit("Alice", -1) }
        assertFailsWith<IllegalArgumentException> { create(listOf("Alice", "Alice")) }
    }
}
