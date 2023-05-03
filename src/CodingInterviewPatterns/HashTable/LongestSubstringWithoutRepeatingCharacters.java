package CodingInterviewPatterns.HashTable;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     *
     * Constraints:
     *
     * 0 <= s.length <= 5 * 104
     * s consists of English letters, digits, symbols and spaces.
     * @param s
     * @return
     */
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    //https://www.youtube.com/results?search_query=3.+longest+substring+without+repeating+characters+java
   // https://www.youtube.com/watch?v=3IETreEybaA

    public int lengthOfLongestSubstring(String s) {

        int p1=0;
        int p2=0;
        int max=0;

        HashSet<Character> set = new HashSet();

        while(p2<s.length()){

            char c = s.charAt(p2);

            if(!set.contains(c)){
                set.add(c);
                p2++;
                max=Math.max(max,set.size());
            }else{
                set.remove(s.charAt(p1));
                p1++;
            }

        }

        return max;

    }
}
