package intercom._6knowledgeretrieval.practice

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.scenario

/*
 * Problem 6 – Knowledge article retrieval. Read ../README.md first.
 *
 *  - Filter by product and audience (article audience EVERYONE or equal to the customer's).
 *  - Prefer the customer's language; fall back to English.
 *  - Rank by relevance: title matches count 3x, content matches 1x (stop words ignored,
 *    "limits" == "limit").
 *  - Return the top `limit` articles.
 */

enum class Audience { EVERYONE, FREE, PREMIUM, ENTERPRISE }

data class Article(
    val id: String,
    val title: String,
    val content: String,
    val product: String,
    val language: String,
    val audience: Audience,
)

class KnowledgeBase(articles: List<Article>) {

    fun searchArticles(query: String, product: String, language: String, audience: Audience, limit: Int): List<Article> {
        TODO("searchArticles")
    }
}

private val ARTICLES = listOf(
    Article(
        "kb-101", "Increase your API rate limit",
        "Premium workspaces can raise their API rate limit from the Developer Hub. " +
            "Open Developer Hub, choose your app and request a higher rate limit.",
        "api", "en", Audience.PREMIUM,
    ),
    Article(
        "kb-102", "Understanding API rate limits",
        "Every app has a default rate limit of 1000 requests per minute. When you exceed the limit the API returns HTTP 429.",
        "api", "en", Audience.EVERYONE,
    ),
    Article(
        "kb-103", "Augmenter la limite de débit de l'API",
        "Les espaces Premium peuvent augmenter la limite de débit de l'API depuis le Developer Hub.",
        "api", "fr", Audience.PREMIUM,
    ),
    Article(
        "kb-104", "Custom API rate limits for Enterprise",
        "Enterprise customers can negotiate a custom rate limit with their account manager.",
        "api", "en", Audience.ENTERPRISE,
    ),
    Article(
        "kb-105", "Increase your seat limit",
        "Admins can increase the number of seats from the billing page.",
        "billing", "en", Audience.EVERYONE,
    ),
)

fun main() {
    val kb = KnowledgeBase(ARTICLES)
    val query = "How do I increase my API rate limit?"

    scenario("Example – Premium customer asks about API rate limits") {
        expect("top 3", kb.searchArticles(query, "api", "en", Audience.PREMIUM, 3).map { it.id }, listOf("kb-101", "kb-102"))
        expect("top 1", kb.searchArticles(query, "api", "en", Audience.PREMIUM, 1).map { it.id }, listOf("kb-101"))
    }

    scenario("Audience filtering") {
        expect("FREE", kb.searchArticles(query, "api", "en", Audience.FREE, 5).map { it.id }, listOf("kb-102"))
        expect("ENTERPRISE", kb.searchArticles(query, "api", "en", Audience.ENTERPRISE, 5).map { it.id }, listOf("kb-102", "kb-104"))
    }

    scenario("French customer – French article first, English fallback") {
        expect(
            "fr",
            kb.searchArticles("Comment augmenter la limite de l'API ?", "api", "fr", Audience.PREMIUM, 3).map { it.id },
            listOf("kb-103", "kb-101", "kb-102"),
        )
    }

    Harness.summary()
}
