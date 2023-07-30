package Grind75.week2;

import java.util.HashMap;

public class RansomNote {
    //https://leetcode.com/problems/ransom-note/description/
    public static void main(String[] args) {
        Solution sln = new Solution();
        // Test cases
        System.out.println(sln.canConstruct("a", "b"));    // Output: false
        System.out.println(sln.canConstruct("aa", "ab"));  // Output: false
        System.out.println(sln.canConstruct("aa", "aab")); // Output: true
    }

    /**
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * <p>
     * Each letter in magazine can only be used once in ransomNote.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: ransomNote = "a", magazine = "b"
     * Output: false
     * Example 2:
     * <p>
     * Input: ransomNote = "aa", magazine = "ab"
     * Output: false
     * Example 3:
     * <p>
     * Input: ransomNote = "aa", magazine = "aab"
     * Output: true
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= ransomNote.length, magazine.length <= 105
     * ransomNote and magazine consist of lowercase English letters.
     */

    static class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            if (ransomNote == null || magazine == null || ransomNote.length() > magazine.length()) {
                return false;
            }

            int[] letterCounts = new int[26];

            // Count the occurrences of each letter in the magazine
            for (char ch : magazine.toCharArray()) {
                letterCounts[ch - 'a']++;
            }

            // Check if ransomNote can be constructed from the magazine
            for (char ch : ransomNote.toCharArray()) {
                int index = ch - 'a';
                if (letterCounts[index] == 0) {
                    return false;
                }
                letterCounts[index]--;
            }

            return true;
        }
    }

    static class Solution2 {
        public boolean canConstruct(String ransomNote, String magazine) {
            if (ransomNote == null || magazine == null || ransomNote.length() > magazine.length()) {
                return false;
            }

            HashMap<Character, Integer> letterCounts = new HashMap<>();

            // Count the occurrences of each letter in the magazine
            for (char ch : magazine.toCharArray()) {
                letterCounts.put(ch, letterCounts.getOrDefault(ch, 0) + 1);
            }

            // Check if ransomNote can be constructed from the magazine
            for (char ch : ransomNote.toCharArray()) {
                if (!letterCounts.containsKey(ch) || letterCounts.get(ch) == 0) {
                    return false;
                }
                letterCounts.put(ch, letterCounts.get(ch) - 1);
            }

            return true;
        }
    }
}
