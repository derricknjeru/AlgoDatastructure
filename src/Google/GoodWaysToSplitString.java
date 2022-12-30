package Google;

import java.util.HashMap;

public class GoodWaysToSplitString {
    public static void main(String[] args) {
        /**
         * You are given a string s.
         *
         * A split is called good if you can split s into two non-empty strings sleft and sright where their concatenation is equal to s (i.e., sleft + sright = s) and the number of distinct letters in sleft and sright is the same.
         *
         * Return the number of good splits you can make in s.
         *
         *
         *
         * Example 1:
         *
         * Input: s = "aacaba"
         * Output: 2
         * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
         * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
         * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
         * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
         * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
         * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
         * Example 2:
         *
         * Input: s = "abcd"
         * Output: 1
         * Explanation: Split the string as follows ("ab", "cd").
         *
         *
         * Constraints:
         *
         * 1 <= s.length <= 105
         * s consists of only lowercase English letters.
         */
        //https://www.youtube.com/watch?v=lRVpVUC5mQ4
        //https://leetcode.com/problems/number-of-good-ways-to-split-a-string/description/
    }

    /**
     * Time complexity is O(n)  n is size of the string
     * space complexity is O(1) ---constant time ---maximum is 26
     *
     * @param s
     * @return
     */
    public int numSplits(String s) {
        int[] left = new int[26];
        int[] right = new int[26];
        // Time O(n)
        //use .toCharArray() if space is not important since .toCharArray() allocates news memory
        //space O(n) because of s.toCharArray()
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            right[c - 'a']++;
        }

        int goodSplits = 0;
        // Time O(n)
        //use .toCharArray() if space is not important since .toCharArray() allocates news memory
        //space O(n) because of s.toCharArray()
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            left[c - 'a']++;
            right[c - 'a']--;

            //find size of the two arrays where character is not zero i.e has a value
            int leftSize = getSize(left);
            int rightSize = getSize(right);

            if (leftSize == rightSize) {
                goodSplits++;
            }
        }

        return goodSplits;
    }

    /**
     * Using hashmap
     * Time complexity is O(n)  n is size of the string
     * space complexity is O(1) ---constant time ---maximum is 26
     *
     * @param s
     * @return
     */
    public int numSplits2(String s) {
        HashMap<Character, Integer> left = new HashMap<>();
        HashMap<Character, Integer> right = new HashMap<>();
        // Time O(n)
        //use .toCharArray() if space is not important since .toCharArray() allocates news memory
        //space O(n) because of s.toCharArray()
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            right.put(c, right.getOrDefault(c, 0) + 1);
        }

        int goodSplits = 0;
        // Time O(n)
        //use .toCharArray() if space is not important since .toCharArray() allocates news memory
        //space O(n) because of s.toCharArray()
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            left.put(c, right.getOrDefault(c, 0) + 1);
            right.put(c, right.getOrDefault(c, 0) - 1);

            //remove empty keys/values
            if (right.get(c) == 0) {
                right.remove(c);
            }

            //find size of the two arrays where character is not zero i.e has a value
            int leftSize = left.size();
            int rightSize = right.size();

            if (leftSize == rightSize) {
                goodSplits++;
            }
        }

        return goodSplits;
    }

    /**
     * @param s
     * @return Time complexity is O(n) n is size of the string
     * space complexity is O(n)  n is size of the string
     */
    public int numSplits3(String s) {
        int[] left = new int[26];
        int[] right = new int[26];
        // Time O(n)
        //use .toCharArray() is space is not important since .toCharArray() allocates news memory
        //space O(n) because of s.toCharArray()
        for (char c : s.toCharArray()) {
            right[c - 'a']++;
        }

        int goodSplits = 0;
        // Time O(n)
        //use .toCharArray() is space is not important since .toCharArray() allocates news memory
        //space O(n) because of s.toCharArray()
        for (char c : s.toCharArray()) {
            left[c - 'a']++;
            right[c - 'a']--;

            //find size of the two arrays where character is not zero i.e has a value
            int leftSize = getSize(left);
            int rightSize = getSize(right);

            if (leftSize == rightSize) {
                goodSplits++;
            }
        }

        return goodSplits;

    }
    //O(n) where n is 26 characters, so it is okay to call it for every instance.

    private int getSize(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num != 0) count++;
        }
        return count;
    }


}
