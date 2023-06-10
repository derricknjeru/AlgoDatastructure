package CodingInterviewPatterns.SlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
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

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));  // Output: 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));     // Output: 1
        System.out.println(lengthOfLongestSubstring("pwwkew"));    // Output: 3
    }


}
