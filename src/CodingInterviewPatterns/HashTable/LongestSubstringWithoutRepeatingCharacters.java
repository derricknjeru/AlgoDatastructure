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
}
