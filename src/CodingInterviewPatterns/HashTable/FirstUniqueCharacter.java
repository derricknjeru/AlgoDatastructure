package CodingInterviewPatterns.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstUniqueCharacter {
    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();

        result.toArray(new Integer[0]);
    }

    /**
     * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "leetcode"
     * Output: 0
     * Example 2:
     * <p>
     * Input: s = "loveleetcode"
     * Output: 2
     * Example 3:
     * <p>
     * Input: s = "aabb"
     * Output: -1
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 105
     * s consists of only lowercase English letters.
     *
     * @param s
     * @return
     */

    public int firstUniqueChar(String s) {

        int[] count = new int[26];
        int n = s.length();
        // build char count bucket : <charIndex, count>
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            count[index]++;
        }

        // find the index
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            if (count[index] == 1) {
                return i;
            }

        }
        return -1;

    }

    public int firstUniqueChar2(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }

        }

        return -1;

        /**
         * Complexity Analysis
         *
         * Time complexity : \mathcal{O}(N)O(N) since we go through the string of length N two times.
         * Space complexity : \mathcal{O}(1)O(1) because English alphabet contains 26 letters.
         */

    }
}
