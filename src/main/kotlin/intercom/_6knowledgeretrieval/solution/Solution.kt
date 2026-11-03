package intercom._6knowledgeretrieval.solution

import intercom.Harness
import intercom.Harness.expect
import intercom.Harness.note
import intercom.Harness.scenario
import java.util.PriorityQueue

/*
 * Problem 6 – Knowledge article retrieval (what Fin does before answering).
 *
 *  1. Index: tokenize every article once; term frequencies for title and content + inverted index.
 *  2. Candidates: union of postings for the query terms (never scan the whole corpus).
 *  3. Hard filters: product, audience (EVERYONE or the customer's audience), language
 *     (customer's language, or the fallback language).
 *  4. Score: Σ over distinct query terms (titleWeight · tfTitle + tfContent).
 *  5. Rank: customer's language first, then score desc, then id. Top-K with a size-K min-heap.
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

data class SearchResult(val article: Article, val score: Int, val inCustomerLanguage: Boolean)

/**
 * Let N = articles, q = query terms, c = candidates.
 * - build index: O(total tokens)
 * - search: O(q + c · q + c · log k)
 * - space: O(total distinct terms per article)
 */
class KnowledgeBase(
    articles: List<Article>,
    private val fallbackLanguage: String = "en",
    private val titleWeight: Int = 3,
) {
    private class IndexedArticle(val article: Article, val titleTf: Map<String, Int>, val contentTf: Map<String, Int>)

    private val indexed: Map<String, IndexedArticle>
    private val postings: Map<String, Set<String>>

    init {
        require(articles.map { it.id }.toSet().size == articles.size) { "Duplicate article ids" }
        indexed = articles.associate { article ->
            article.id to IndexedArticle(article, termFrequencies(article.title), termFrequencies(article.content))
        }
        val index = HashMap<String, MutableSet<String>>()
        for (entry in indexed.values) {
            (entry.titleTf.keys + entry.contentTf.keys).forEach { term ->
                index.getOrPut(term) { linkedSetOf() } += entry.article.id
            }
        }
        postings = index
    }

    fun searchArticles(query: String, product: String, language: String, audience: Audience, limit: Int): List<Article> =
        search(query, product, language, audience, limit).map { it.article }

    fun search(query: String, product: String, language: String, audience: Audience, limit: Int): List<SearchResult> {
        require(limit >= 0) { "limit must be >= 0" }
        if (limit == 0) return emptyList()
        val terms = tokenize(query).toSet()
        val candidateIds = terms.flatMapTo(HashSet()) { postings[it].orEmpty() }

        // Min-heap of the best `limit` results seen so far; root = current worst.
        val heap = PriorityQueue(limit + 1, BEST_FIRST.reversed())
        for (id in candidateIds) {
            val entry = indexed.getValue(id)
            val article = entry.article
            if (!article.product.equals(product, ignoreCase = true)) continue
            if (article.audience != Audience.EVERYONE && article.audience != audience) continue
            val inCustomerLanguage = article.language.equals(language, ignoreCase = true)
            if (!inCustomerLanguage && !article.language.equals(fallbackLanguage, ignoreCase = true)) continue

            val score = terms.sumOf { titleWeight * (entry.titleTf[it] ?: 0) + (entry.contentTf[it] ?: 0) }
            heap += SearchResult(article, score, inCustomerLanguage)
            if (heap.size > limit) heap.poll()
        }
        return heap.sortedWith(BEST_FIRST)
    }

    companion object {
        private val BEST_FIRST: Comparator<SearchResult> =
            compareByDescending<SearchResult> { it.inCustomerLanguage }
                .thenByDescending { it.score }
                .thenBy { it.article.id }

        private val SPLIT = Regex("""[^\p{L}\p{N}]+""")

        private val STOP_WORDS = setOf(
            "a", "an", "and", "are", "can", "do", "does", "for", "from", "how", "i", "in", "is", "it",
            "me", "my", "of", "on", "or", "the", "their", "to", "what", "when", "with", "you", "your",
            "de", "des", "du", "et", "la", "le", "les", "un", "une",
        )

        /** Lowercase, split on non-letters/digits, drop 1-char tokens and stop words, naive plural stemming. */
        fun tokenize(text: String): List<String> =
            text.lowercase()
                .split(SPLIT)
                .filter { it.length > 1 && it !in STOP_WORDS }
                .map { stem(it) }

        private fun stem(token: String) =
            if (token.length > 3 && token.endsWith("s") && !token.endsWith("ss")) token.dropLast(1) else token

        private fun termFrequencies(text: String): Map<String, Int> = tokenize(text).groupingBy { it }.eachCount()
    }
}

val SAMPLE_ARTICLES = listOf(
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
    Article(
        "kb-106", "Webhooks overview",
        "Webhooks notify your app when a conversation changes.",
        "api", "en", Audience.EVERYONE,
    ),
)

fun main() {
    val kb = KnowledgeBase(SAMPLE_ARTICLES)
    val query = "How do I increase my API rate limit?"

    scenario("Example – Premium customer asks about API rate limits") {
        expect("query terms", KnowledgeBase.tokenize(query), listOf("increase", "api", "rate", "limit"))
        val results = kb.search(query, "api", "en", Audience.PREMIUM, 3)
        results.forEach { note("${it.article.id} '${it.article.title}' score=${it.score}") }
        expect("ids", results.map { it.article.id }, listOf("kb-101", "kb-102"))
        expect("scores", results.map { it.score }, listOf(17, 13))
        expect("top-1", kb.searchArticles(query, "api", "en", Audience.PREMIUM, 1).map { it.id }, listOf("kb-101"))
    }

    scenario("Audience filtering") {
        expect("FREE", kb.searchArticles(query, "api", "en", Audience.FREE, 5).map { it.id }, listOf("kb-102"))
        expect("ENTERPRISE", kb.searchArticles(query, "api", "en", Audience.ENTERPRISE, 5).map { it.id }, listOf("kb-102", "kb-104"))
    }

    scenario("Product filtering") {
        expect("billing", kb.searchArticles("increase my limit", "billing", "en", Audience.FREE, 5).map { it.id }, listOf("kb-105"))
    }

    scenario("Customer language first, English as fallback") {
        val results = kb.search("Comment augmenter la limite de l'API ?", "api", "fr", Audience.PREMIUM, 3)
        results.forEach { note("${it.article.id} lang=${it.article.language} score=${it.score}") }
        expect("ids", results.map { it.article.id }, listOf("kb-103", "kb-101", "kb-102"))
        expect("scores", results.map { it.score }, listOf(12, 4, 4))
    }

    scenario("No matches") {
        expect("stop words only", kb.searchArticles("how do I", "api", "en", Audience.PREMIUM, 3), emptyList<Article>())
        expect("limit 0", kb.searchArticles(query, "api", "en", Audience.PREMIUM, 0), emptyList<Article>())
    }

    Harness.summary()
}
