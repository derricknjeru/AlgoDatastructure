package leetcode._8Tries


/*
 * ============================================================
 * TRIE DATA STRUCTURE (PREFIX TREE) - KOTLIN NOTES
 * ============================================================
 *
 * 1. DEFINITION
 * ------------------------------------------------------------
 * A Trie (pronounced "try") is a tree-based data structure used
 * to store and search strings efficiently.
 *
 * Each node represents a character, and a path from the root
 * represents a prefix or a complete word.
 *
 * Tries share common prefixes between words.
 *
 * Example words:
 *     cat, car, card, care, dog
 *
 * The words cat, car, card, and care share the prefix "ca".
 *
 *
 * 2. KEY TERMINOLOGY
 * ------------------------------------------------------------
 * Root:
 *     The starting node. It represents the empty prefix.
 *
 * Node:
 *     Represents a character or the root.
 *
 * Child:
 *     A node connected below another node.
 *
 * Prefix:
 *     The beginning of a word.
 *     Example: "app" is a prefix of "apple".
 *
 * End-of-word marker:
 *     A Boolean indicating whether a complete word ends at
 *     the current node.
 *
 * Path:
 *     A sequence of connected nodes representing characters.
 *
 *
 * 3. WHY USE A TRIE?
 * ------------------------------------------------------------
 * - Efficient prefix searching.
 * - Autocomplete and search suggestions.
 * - Dictionary and spell-check implementations.
 * - Word games and word search.
 * - Finding words that share common prefixes.
 *
 *
 * 4. TRIE NODE REPRESENTATION
 * ------------------------------------------------------------
 * children:
 *     Maps a character to its corresponding child node.
 *
 * isEndOfWord:
 *     Indicates whether a complete word ends at this node.
 *
 * Why use HashMap<Char, TrieNode>?
 *     It allows each node to have children for the characters
 *     that actually appear, rather than allocating a fixed
 *     array for every possible character.
 *
 * Why is isEndOfWord necessary?
 *     The path for "app" can exist because "apple" was inserted.
 *     However, "app" is not necessarily a complete word.
 *
 *
 * 5. CORE OPERATIONS
 * ------------------------------------------------------------
 * insert(word):
 *     Traverses the word character by character.
 *     Creates missing nodes and marks the last node as a word.
 *
 * search(word):
 *     Checks whether the entire path exists AND the final node
 *     is marked as the end of a word.
 *
 * startsWith(prefix):
 *     Checks whether the path for the prefix exists.
 *     It does not require the last node to end a word.
 *
 *
 * 6. TIME AND SPACE COMPLEXITY
 * ------------------------------------------------------------
 * Let:
 *     L = length of the word.
 *     P = length of the prefix.
 *     N = total number of characters across inserted words.
 *
 * insert(word):
 *     Time: O(L) expected
 *     Extra space: O(L) worst case for newly created nodes.
 *
 * search(word):
 *     Time: O(L) expected
 *     Auxiliary space: O(1)
 *
 * startsWith(prefix):
 *     Time: O(P) expected
 *     Auxiliary space: O(1)
 *
 * Overall Trie storage:
 *     O(N) worst case.
 *     Shared prefixes can reduce the number of nodes.
 *
 * HashMap operations take expected O(1) time per character.
 *
 *
 * 7. COMPLETE KOTLIN IMPLEMENTATION
 * ============================================================
 */

class TrieNode {
    val children = HashMap<Char, TrieNode>()
    var isEndOfWord = false
}

class TrieSample {

    private val root = TrieNode()

    // Inserts a word into the Trie.
    // Time: O(L) expected
    // Space: O(L) worst case for newly created nodes
    fun insert(word: String) {
        var current = root

        for (char in word) {
            current = current.children.getOrPut(char) {
                TrieNode()
            }
        }

        current.isEndOfWord = true
    }

    // Returns true if the complete word exists.
    // Time: O(L) expected
    // Auxiliary space: O(1)
    fun search(word: String): Boolean {
        return findPrefix(word)?.isEndOfWord == true
    }

    // Returns true if the prefix exists.
    // Time: O(P) expected
    // Auxiliary space: O(1)
    fun startsWith(prefix: String): Boolean {
        return findPrefix(prefix) != null
    }

    private fun findPrefix(prefix: String): TrieNode? {
        var current = root
        for (c in prefix) {
            current = current.children[c] ?: return null
        }
        return current
    }

    /**
     * Bonus: Retrieves all words that start with a given prefix (Autocomplete feature).
     */
    fun getWordsWithPrefix(prefix: String): List<String> {
        val results = mutableListOf<String>()
        val startNode = findPrefix(prefix) ?: return results

        // Helper DFS function to collect words
        fun dfs(node: TrieNode, currentPath: StringBuilder) {
            if (node.isEndOfWord) {
                results.add(currentPath.toString())
            }
            for ((char, childNode) in node.children) {
                currentPath.append(char)
                dfs(childNode, currentPath)
                currentPath.deleteCharAt(currentPath.length - 1) // backtrack
            }
        }

        dfs(startNode, StringBuilder(prefix))
        return results
    }
}

/*
 * 8. EXAMPLE USAGE
 * ============================================================
 */

fun main() {
    val trie = TrieSample()

    trie.insert("apple")

    println(trie.search("apple"))    // true
    println(trie.search("app"))      // false
    println(trie.startsWith("app"))  // true

    trie.insert("app")

    println(trie.search("app"))      // true
}

/*
 * 9. IMPORTANT INTERVIEW POINTS
 * ============================================================
 *
 * 1. A Trie is also called a Prefix Tree.
 *
 * 2. Every complete word does not need its own separate path.
 *    Words with common prefixes share nodes.
 *
 * 3. A node can represent both the end of one word and the
 *    prefix of a longer word.
 *
 * 4. search() checks isEndOfWord.
 *
 * 5. startsWith() does not check isEndOfWord.
 *
 * 6. Inserting an existing word again does not create duplicate
 *    nodes; it simply marks the final node as a word ending.
 *
 * 7. Trie search time depends primarily on the input length,
 *    rather than the total number of stored words.
 *
 * 8. An empty string is considered present by search("") in
 *    this implementation because the root initially exists
 *    but is not marked as an end of a word.
 *
 * 9. This implementation is case-sensitive:
 *    "Apple" and "apple" are different strings.
 *
 * 10. For lowercase English letters only, children can instead
 *     be represented by Array<TrieNode?> of size 26.
 *
 *
 * 10. LEETCODE PRACTICE ORDER
 * ============================================================
 *
 * 208. Implement Trie (Prefix Tree) - foundational problem
 * 211. Design Add and Search Words Data Structure - wildcard
 * 648. Replace Words - prefix matching
 * 212. Word Search II - Trie + DFS + backtracking
 *
 * ============================================================
 */
