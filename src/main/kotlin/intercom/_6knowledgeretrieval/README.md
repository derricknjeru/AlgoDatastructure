# Problem 6 – Knowledge article retrieval

> **Priority: MEDIUM-LOW (predicted).** Before Fin answers, it retrieves help-center articles.
> Tests filtering, a simple but defensible scoring function, top-K with a heap, and search follow-ups
> (inverted index, BM25, embeddings).

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeleton + runnable examples. |
| [`solution/Solution.kt`](solution/Solution.kt) | Inverted index, TF scoring with title boost, language tiers, top-K heap. |
| `src/test/kotlin/intercom/_6knowledgeretrieval/` | Unit tests. |

```bash
./gradlew -q runMain -PmainClass=intercom._6knowledgeretrieval.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._6knowledgeretrieval.solution.SolutionKt
```

---

## 1. Prompt

> A customer on the **Premium** plan asks: *"How do I increase my API rate limit?"* Return the most
> relevant help articles for their product, audience and language.

```kotlin
data class Article(val id: String, val title: String, val content: String,
                   val product: String, val language: String, val audience: Audience)

fun searchArticles(query: String, product: String, language: String, audience: Audience, limit: Int): List<Article>
```

### Example (articles in `solution/Solution.kt` → `SAMPLE_ARTICLES`)

| id | title | product | lang | audience |
|----|-------|---------|------|----------|
| kb-101 | Increase your API rate limit | api | en | PREMIUM |
| kb-102 | Understanding API rate limits | api | en | EVERYONE |
| kb-103 | Augmenter la limite de débit de l'API | api | fr | PREMIUM |
| kb-104 | Custom API rate limits for Enterprise | api | en | ENTERPRISE |
| kb-105 | Increase your seat limit | billing | en | EVERYONE |
| kb-106 | Webhooks overview | api | en | EVERYONE |

```text
query terms: [increase, api, rate, limit]        // "how", "do", "i", "my" are stop words

search(query, "api", "en", PREMIUM, 3)    -> kb-101 (17), kb-102 (13)
   kb-101: title 4 terms × 3 = 12, content api 1 + rate 2 + limit 2 = 5  -> 17
   kb-102: title api, rate, limit(s) × 3 = 9, content rate 1 + limit 2 + api 1 = 4 -> 13
   kb-103 wrong language, kb-104 enterprise only, kb-105 billing, kb-106 no match

search(query, "api", "en", FREE, 5)       -> kb-102
search(query, "api", "en", ENTERPRISE, 5) -> kb-102 (13), kb-104 (11)
search("Comment augmenter la limite de l'API ?", "api", "fr", PREMIUM, 3)
                                          -> kb-103 (fr, 12), kb-101 (en, 4), kb-102 (en, 4)
```

---

## 2. Suggested timeline (50 min)

| Time | Phase |
|------|-------|
| 0–7 | Clarify filters vs ranking signals; agree on a simple scoring function. |
| 7–15 | Tokenizer + scoring on one article (unit-test it). |
| 15–30 | Filter → score → sort → take K. Run example. |
| 30–38 | Optimise: precomputed term frequencies, inverted index, top-K heap. |
| 38–50 | Follow-ups: BM25/IDF, synonyms, embeddings, feedback loop, multilingual. |

---

## 3. Clarifying questions

1. Are product and audience **hard filters** or ranking signals? *(Hard filters.)*
2. Audience hierarchy – should Enterprise see Premium articles? *(Here: exact match or EVERYONE.)*
3. Language: only the customer's language, or fall back? *(Customer's first, English fallback.)*
4. How should relevance be computed? Title vs body weight? *(Title 3×.)*
5. Tie-breaking? *(Deterministic: id.)*
6. Corpus size and QPS? *(Drives inverted index / precomputation.)*
7. What if nothing matches – return empty or popular articles?

---

## 4. Progressive hints

1. Write `tokenize(text)`: lowercase → split on non-letters → drop stop words → naive stemming (`limits`→`limit`).
2. Score = Σ over **distinct** query terms of `3 · tf(title) + tf(content)`.
3. Precompute per-article term-frequency maps at construction – don't re-tokenize on every query.
4. Inverted index `term → articleIds` gives candidates without scanning every article.
5. Top-K: min-heap of size K ordered "worst on top"; push, and pop when size > K. O(c log K).
6. Language preference is the **first** sort key, score second, id third.

---

## 5. Complexity

| Step | Cost |
|------|------|
| Build index | O(total tokens) |
| Candidate generation | O(Σ posting lengths) |
| Score + top-K | O(c · q + c log K) |
| Space | O(distinct terms per article) |

Naive alternative: score every article and `sortedByDescending` → O(N · (q + L) + N log N).

---

## 6. Follow-ups

| Follow-up | Strong answer |
|-----------|---------------|
| Better relevance | TF-IDF / **BM25** (term saturation + length normalisation), phrase/proximity boost. |
| Synonyms / typos | Synonym map ("raise" ≈ "increase"), fuzzy matching, n-grams. |
| Semantic search | Embed articles (chunked) + query; vector ANN search; **hybrid** = BM25 + vector with reciprocal rank fusion. This is how Fin-style RAG works. |
| Scale | Elasticsearch/OpenSearch with per-workspace index or routing; filters as keyword fields. |
| Freshness / updates | Incremental re-index on article publish; versioning. |
| Feedback loop | Use "did this resolve the conversation?" and click data to learn ranking; track escalations (Problem 4). |
| Multilingual | Per-language analyzers/stemmers; translate query; cross-lingual embeddings. |
| Permissions | Audience/segment filters must be enforced server-side – never leak internal articles. |

---

## 7. Self-review checklist

- [ ] Product and audience filters applied before ranking.
- [ ] Customer language first, fallback second.
- [ ] Ties broken deterministically.
- [ ] Explained top-K heap and the inverted index.
