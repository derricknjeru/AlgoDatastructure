package Grind75.week2;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    public static void main(String[] args) {
        Solution lp = new Solution();
        System.out.println(lp.longestPalindrome("abccccdd")); // Output: 7
        System.out.println(lp.longestPalindrome("a")); // Output: 1
    }


    static class Solution {
        //Time complexity is O(n)
        //space complexity is O(m) --where m is the number of unique characters
        public int longestPalindrome(String s) {
            Map<Character, Integer> charCount = new HashMap<>();

            for (char c : s.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }

            int maxLength = 0;
            boolean hasOddCount = false;
            //unique character
            for (int count : charCount.values()) {
                if (count % 2 == 0) {
                    maxLength += count;
                } else {
                    maxLength += count - 1;
                    hasOddCount = true;
                }
            }

            if (hasOddCount) {
                maxLength++;
            }

            return maxLength;
        }
    }
}
