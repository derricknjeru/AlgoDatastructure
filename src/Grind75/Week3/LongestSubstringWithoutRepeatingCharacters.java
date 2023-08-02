package Grind75.Week3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
    //https://www.youtube.com/watch?v=wiGpQwVHdE0
    //https://www.youtube.com/watch?v=4i6-9IzQHwo
    public static void main(String[] args) {

        Solution solution = new Solution();

        // Edge case 1: Empty String
        String s1 = "";
        System.out.println("Edge case 1: " + solution.lengthOfLongestSubstring(s1)); // Output: 0

        // Edge case 2: Single Character String
        String s2 = "a";
        System.out.println("Edge case 2: " + solution.lengthOfLongestSubstring(s2)); // Output: 1

        // Edge case 3: String with All Repeating Characters
        String s3 = "aaaaa";
        System.out.println("Edge case 3: " + solution.lengthOfLongestSubstring(s3)); // Output: 1

        // Edge case 4: String with No Repeating Characters
        String s4 = "abcdefg";
        System.out.println("Edge case 4: " + solution.lengthOfLongestSubstring(s4)); // Output: 7

        // Edge case 5: String with Repeating Characters at the End
        String s5 = "abcaabc";
        System.out.println("Edge case 5: " + solution.lengthOfLongestSubstring(s5)); // Output: 3
    }


    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int maxLen = 0;
            Set<Character> lookup = new HashSet<>();

            int left = 0;
            //O(n) Time and space
            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                while (lookup.contains(c)) {
                    char ch = s.charAt(left);
                    lookup.remove(ch);
                    left++;
                }

                lookup.add(c);
                int currentLen = right - left + 1;
                maxLen = Math.max(maxLen, currentLen);
            }

            return maxLen;
        }
    }

    class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int maxLength = 0;
            int left = 0; // Left pointer of the sliding window
            HashMap<Character, Integer> charIndexMap = new HashMap<>();

            for (int right = 0; right < s.length(); right++) {
                char ch = s.charAt(right);
                if (charIndexMap.containsKey(ch)) {
                    // If the character is already in the map, move the left pointer to the right of its last occurrence.
                    left = Math.max(left, charIndexMap.get(ch) + 1);
                }
                charIndexMap.put(ch, right); // Update the index of the character in the map
                maxLength = Math.max(maxLength, right - left + 1); // Update the maximum length
            }

            return maxLength;
        }
    }


}
