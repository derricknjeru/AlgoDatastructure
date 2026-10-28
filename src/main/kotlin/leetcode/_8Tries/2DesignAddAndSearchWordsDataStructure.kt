package leetcode._8Tries

import kotlin.collections.get
import kotlin.collections.set

fun main() {
    val dic = WordDictionary()
    val dictionary = WordDictionary2()

    dictionary.addWord("bad")
    dictionary.addWord("dad")
    dictionary.addWord("mad")

    // search ["pad"],["bad"],[".ad"],["b.."]]
    println(dictionary.search("pad")) // false
    println(dictionary.search("bad")) // true
    println(dictionary.search(".ad")) // true
    println(dictionary.search("b..")) // true
}


class WordDictionary() {

    private class TrieNode {
        // Maps each character to its child node.
        val children = mutableMapOf<Char, TrieNode>()

        // True if a complete word ends at this node.
        var isWord = false
    }

    private val root = TrieNode()

    fun addWord(word: String) {
        var current = root

        // Build the Trie one character at a time.
        for (c in word) {
            current = current.children.getOrPut(c) { TrieNode() }
        }

        // Mark the final node as the end of a complete word.
        current.isWord = true
    }

    fun search(word: String): Boolean {
        return searchFrom(root, word, 0)
    }

    private fun searchFrom(
        node: TrieNode,
        word: String,
        index: Int
    ): Boolean {
        // All characters matched: check if a complete word ends here.
        if (index == word.length) {
            return node.isWord
        }

        val c = word[index]

        if (c == '.') {
            // Wildcard: try every possible child.
            for (child in node.children.values) {
                if (searchFrom(child, word, index + 1)) return true
            }
            // No child produced a matching word.
            return false
        }

        // Normal character: follow the matching child.
        val child = node.children[c] ?: return false
        return searchFrom(child, word, index + 1)
    }
}

/*
Time Complexity:
- addWord(word): O(L), where L is the word length.
- search(word): O(26^L) worst case with lowercase English letters
  when every character is a wildcard and the Trie is sufficiently branched.
  More precisely, search visits at most the relevant Trie nodes.

Space Complexity:
- addWord(word): O(L) worst-case additional space for new Trie nodes.
- Trie storage: O(N * L) worst case for N words of maximum length L.
- search(word): O(L) recursion stack space.
*/


class WordDictionary2() {

    private class TrieNode {
        // Maps each character to its child node.
        val children = arrayOfNulls<TrieNode>(26)

        // True if a complete word ends at this node.
        var isWord = false
    }

    private val root = TrieNode()

    fun addWord(word: String) {
        var current = root

        // Build the Trie one character at a time.
        for (c in word) {
            val index = c - 'a'
            current = current.children[index] ?: TrieNode().also { current.children[index] = it }
        }

        // Mark the final node as the end of a complete word.
        current.isWord = true
    }

    fun search(word: String): Boolean {
        return searchFrom(root, word, 0)
    }

    private fun searchFrom(
        node: TrieNode,
        word: String,
        index: Int
    ): Boolean {
        // All characters matched: check if a complete word ends here.
        if (index == word.length) {
            return node.isWord
        }

        val c = word[index]

        if (c == '.') {
            for (child in node.children) {
                if (child != null && searchFrom(child, word, index + 1)) return true
            }
            return false
        }

        // Normal character: follow the matching child.
        val child = node.children[c - 'a'] ?: return false
        return searchFrom(child, word, index + 1)
    }
}


class WordDictionary3() {

    private class TrieNode {
        // Maps each character to its child node.
        val children = mutableMapOf<Char, TrieNode>()

        // True if a complete word ends at this node.
        var isWord = false
    }

    private val root = TrieNode()

    fun addWord(word: String) {
        var current = root

        // Build the Trie one character at a time.
        for (c in word) {
            current = current.children.getOrPut(c) { TrieNode() }
        }

        // Mark the final node as the end of a complete word.
        current.isWord = true
    }

    fun search(word: String): Boolean {
        return searchFrom(root, word, 0)
    }

    private fun searchFrom(
        node: TrieNode,
        word: String,
        index: Int
    ): Boolean {
        // All characters matched: check if a complete word ends here.
        if (index == word.length) {
            return node.isWord
        }

        val c = word[index]

        if (c == '.') {
            return node.children.values.any { searchFrom(it, word, index + 1) }
        }

        // Normal character: follow the matching child.
        val child = node.children[c] ?: return false
        return searchFrom(child, word, index + 1)
    }
}


class WordDictionary4() {

    private class TrieNode {
        // Maps each character to its child node.
        val children = arrayOfNulls<TrieNode>(26)

        // True if a complete word ends at this node.
        var isWord = false
    }

    private val root = TrieNode()

    fun addWord(word: String) {
        var current = root

        // Build the Trie one character at a time.
        for (c in word) {
            val index = c - 'a'
            current = current.children[index] ?: TrieNode().also { current.children[index] = it }
        }

        // Mark the final node as the end of a complete word.
        current.isWord = true
    }

    fun search(word: String): Boolean {
        return searchFrom(root, word, 0)
    }

    private fun searchFrom(
        node: TrieNode,
        word: String,
        index: Int
    ): Boolean {
        // All characters matched: check if a complete word ends here.
        if (index == word.length) {
            return node.isWord
        }

        val c = word[index]

        if (c == '.') {
            return node.children.any { child -> child != null && searchFrom(child, word, index + 1) }
        }

        // Normal character: follow the matching child.
        val child = node.children[c - 'a'] ?: return false
        return searchFrom(child, word, index + 1)
    }
}