package leetcode._8Tries

fun main() {

}

// https://www.youtube.com/watch?v=asbcE9mZz_U
class Solution {
    private class TrieNode {
        val children = mutableMapOf<Char, TrieNode>()
        var word: String? = null
    }

    private val root = TrieNode()
    private val result = mutableListOf<String>()

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        buildTrie(words)

        for (row in board.indices) {
            for (col in board[0].indices) {
                dfs(board, row, col, root)
            }
        }

        return result
    }

    // Builds a Trie containing all candidate words.
    private fun buildTrie(words: Array<String>) {
        for (word in words) {
            var current = root

            for (c in word) {
                current = current.children.getOrPut(c) { TrieNode() }
            }

            // Mark the end of a complete word.
            current.word = word
        }
    }

    private fun dfs(
        board: Array<CharArray>,
        row: Int,
        col: Int,
        parent: TrieNode
    ) {
        if (row !in board.indices || col !in board[0].indices) return

        val c = board[row][col]
        if (c == '#') return

        val node = parent.children[c] ?: return

        // Add the word if we reached a terminal Trie node.
        node.word?.let {
            result.add(it)
            node.word = null
        }

        // Mark the cell as visited.
        board[row][col] = '#'

        dfs(board, row - 1, col, node) // Up
        dfs(board, row + 1, col, node) // Down
        dfs(board, row, col - 1, node) // Left
        dfs(board, row, col + 1, node) // Right

        // Restore the original character.
        board[row][col] = c

        // Remove branches that cannot produce additional words.
        if (node.children.isEmpty() && node.word == null) {
            parent.children.remove(c)
        }
    }
}

/*
Time Complexity:
- buildTrie(): O(S), where S is the sum of all word lengths.
- DFS: O(M * N * 4 * 3^(L - 1)) upper bound.
- Total: O(S + M * N * 4 * 3^(L - 1)).

Space Complexity:
- Trie: O(S).
- DFS recursion stack: O(L).
- Results: O(K), where K is the total length of found words.
*/
