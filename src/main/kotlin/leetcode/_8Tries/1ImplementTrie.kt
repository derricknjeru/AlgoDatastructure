package leetcode._8Tries

fun main() {
    val trie = Trie()

    trie.insert("apple")

    println(trie.search("apple"))    // true
    println(trie.search("app"))      // false
    println(trie.startsWith("app"))  // true

    trie.insert("app")

    println(trie.search("app"))      // true
}

class Trie() {

    // Root node represents the beginning of the Trie.
    private val root = TrieNode()

    fun insert(word: String) {
        var current = root

        // Traverse each character, creating missing nodes as needed.
        for (c in word) {
            current = current.children.getOrPut(c) { TrieNode() }
        }

        // Mark the final node as the end of a complete word.

        current.isEndOfWord = true
    }
    // Time: O(L), where L is the length of the word.
    // Space: O(L) in the worst case when all characters require new nodes.

    fun search(word: String): Boolean {
        // A word exists only if its path exists and the final node is a complete word.
        return findPrefix(word)?.isEndOfWord == true
    }
    // Time: O(L), where L is the length of the word.
    // Space: O(1) auxiliary space.

    fun startsWith(prefix: String): Boolean {
        // A prefix exists if its path can be traversed successfully.
        return findPrefix(prefix) != null
    }
    // Time: O(L), where L is the length of the prefix.
    // Space: O(1) auxiliary space.

    private fun findPrefix(prefix: String): TrieNode? {
        var current = root

        // Follow the Trie path one character at a time.
        for (c in prefix) {
            // Return null immediately if the character path doesn't exist.
            current = current.children[c] ?: return null
        }

        // Return the node reached after traversing the entire prefix.
        return current
    }
    // Time: O(L), where L is the length of the prefix.
    // Space: O(1) auxiliary space.
}

class Trie2 {
    private class TrieNode {
        val children = arrayOfNulls<TrieNode>(26)
        var isWord = false
    }

    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        for (c in word) {
            val index = c - 'a'
            current = current.children[index]
                ?: TrieNode().also { current.children[index] = it }
        }
        current.isWord = true
    }

    fun search(word: String): Boolean =
        searchPrefix(word)?.isWord == true

    fun startsWith(prefix: String): Boolean =
        searchPrefix(prefix) != null

    private fun searchPrefix(prefix: String): TrieNode? {
        var current = root
        for (c in prefix) {
            current = current.children[c - 'a'] ?: return null
        }
        return current
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */