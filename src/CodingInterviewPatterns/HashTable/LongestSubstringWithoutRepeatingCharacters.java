package CodingInterviewPatterns.HashTable;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     * <p>
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     * <p>
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= s.length <= 5 * 104
     * s consists of English letters, digits, symbols and spaces.
     *
     * @param s
     * @return
     */
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
    //https://www.youtube.com/watch?v=wiGpQwVHdE0
    //https://www.youtube.com/watch?v=4i6-9IzQHwo
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLen = 0;
        Set<Character> seen = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (seen.contains(c)) {
                char ch = s.charAt(left);
                seen.remove(ch);
                left++;
            }
            seen.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
    /**
     * The expression right - left + 1 calculates the length of the current substring without repeating characters.
     *
     * In the sliding window approach, we use two pointers, left and right, to define the boundaries of the current substring we are considering. The length of the substring is given by right - left + 1.
     *
     * Here's a breakdown of the components:
     *
     * right represents the right boundary of the substring, which is the index of the current character we are examining.
     * left represents the left boundary of the substring, which is the index of the earliest character in the substring (the character at left is included in the substring).
     * right - left gives the number of characters between left and right, inclusive.
     * Adding 1 (right - left + 1) accounts for the fact that we want to include the current character in the substring.
     * For example, if left = 2 and right = 5, the substring would consist of the characters at indices 2, 3, 4, and 5. The length of the substring would be right - left + 1 = 5 - 2 + 1 = 4.
     *
     * Therefore, right - left + 1 gives us the length of the current substring without repeating characters.
     */
}
