package leetcode._9Backtracking

// https://www.youtube.com/watch?v=0snEunUacZY
fun letterCombinations(digits: String): List<String> {
    // An empty input has no letter combinations.
    if (digits.isEmpty()) return emptyList()

    val result = mutableListOf<String>()
    val currentCombination = StringBuilder()

    val digitToLetters = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    fun dfs(index: Int) {
        // One letter has been selected for every digit.
        if (index == digits.length) {
            result.add(currentCombination.toString())
            return
        }

        val letters = digitToLetters.getValue(digits[index])

        // Try every letter mapped to the current digit.
        for (letter in letters) {
            // Choose a letter.
            currentCombination.append(letter)

            // Move to the next digit.
            dfs(index + 1)

            // Undo the choice before trying another letter.
            currentCombination.deleteCharAt(
                currentCombination.lastIndex
            )
        }
    }

    dfs(index = 0)
    return result
}

/*
Time complexity:
O(N * 4^N)

Each digit can map to at most four letters. There can therefore be
up to 4^N combinations, and copying each completed combination into
a String takes O(N).

A tighter output-sensitive bound is O(N * 3^A * 4^B), where A is
the number of digits mapping to three letters and B is the number
mapping to four letters.

Space complexity:
O(N), excluding the returned combinations.

The recursion depth and currentCombination contain at most N characters.
*/