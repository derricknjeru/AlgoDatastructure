package Grind75.week1;

import java.util.*;

public class ValidAnagram {
    //https://leetcode.com/problems/valid-anagram/
    public static void main(String[] args) {
        String s1 = "anagram";
        String t1 = "nagaram";

        String s2 = "rat";
        String t2 = "car";

        Solution solution = new Solution();

        System.out.println("Is s1 an anagram of t1? " + solution.isAnagram(s1, t1));
        System.out.println("Is s2 an anagram of t2? " + solution.isAnagram(s2, t2));
    }

    static class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int[] count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int c : count) {
                if (c != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution2 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            Map<Character, Integer> count = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
                count.put(t.charAt(i), count.getOrDefault(t.charAt(i), 0) - 1);
            }

            for (int c : count.values()) {
                if (c != 0) {
                    return false;
                }
            }

            return true;
        }
    }


    class Solution3 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();

            Arrays.sort(sChars);
            Arrays.sort(tChars);

            return Arrays.equals(sChars, tChars);
        }
    }

}
