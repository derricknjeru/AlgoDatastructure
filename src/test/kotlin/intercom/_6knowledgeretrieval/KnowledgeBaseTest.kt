package intercom._6knowledgeretrieval

import intercom._6knowledgeretrieval.solution.Article
import intercom._6knowledgeretrieval.solution.Audience
import intercom._6knowledgeretrieval.solution.KnowledgeBase
import intercom._6knowledgeretrieval.solution.SAMPLE_ARTICLES
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class KnowledgeBaseTest {
    private val kb = KnowledgeBase(SAMPLE_ARTICLES)
    private val query = "How do I increase my API rate limit?"

    @Test
    fun `tokenizer drops stop words and stems plurals`() {
        assertEquals(listOf("increase", "api", "rate", "limit"), KnowledgeBase.tokenize(query))
        assertEquals(listOf("rate", "limit", "access"), KnowledgeBase.tokenize("Rate LIMITS, access!"))
    }

    @Test
    fun `premium english example`() {
        val results = kb.search(query, "api", "en", Audience.PREMIUM, 3)
        assertEquals(listOf("kb-101", "kb-102"), results.map { it.article.id })
        assertEquals(listOf(17, 13), results.map { it.score })
    }

    @Test
    fun `limit keeps only the best`() {
        assertEquals(listOf("kb-101"), kb.searchArticles(query, "api", "en", Audience.PREMIUM, 1).map { it.id })
        assertTrue(kb.searchArticles(query, "api", "en", Audience.PREMIUM, 0).isEmpty())
        assertFailsWith<IllegalArgumentException> { kb.searchArticles(query, "api", "en", Audience.PREMIUM, -1) }
    }

    @Test
    fun `audience and product filters`() {
        assertEquals(listOf("kb-102"), kb.searchArticles(query, "api", "en", Audience.FREE, 5).map { it.id })
        assertEquals(listOf("kb-102", "kb-104"), kb.searchArticles(query, "api", "en", Audience.ENTERPRISE, 5).map { it.id })
        assertEquals(listOf("kb-105"), kb.searchArticles(query, "billing", "en", Audience.PREMIUM, 5).map { it.id })
    }

    @Test
    fun `customer language ranks before fallback language`() {
        val ids = kb.searchArticles("Comment augmenter la limite de l'API ?", "api", "fr", Audience.PREMIUM, 3).map { it.id }
        assertEquals(listOf("kb-103", "kb-101", "kb-102"), ids)
    }

    @Test
    fun `articles in other non fallback languages are excluded`() {
        val kbWithGerman = KnowledgeBase(
            SAMPLE_ARTICLES + Article("kb-200", "API Ratenlimit erhöhen", "API API API", "api", "de", Audience.EVERYONE),
        )
        val ids = kbWithGerman.searchArticles(query, "api", "fr", Audience.PREMIUM, 10).map { it.id }
        assertTrue("kb-200" !in ids)
    }

    @Test
    fun `ties are broken by id`() {
        val kb = KnowledgeBase(
            listOf(
                Article("b", "Refunds", "", "billing", "en", Audience.EVERYONE),
                Article("a", "Refunds", "", "billing", "en", Audience.EVERYONE),
            ),
        )
        assertEquals(listOf("a", "b"), kb.searchArticles("refund", "billing", "en", Audience.FREE, 2).map { it.id })
    }
}
