package revision.codility;

public class Task11 {
    public static void main(String[] args) {
        /**
         * You are given an implementation of a function:
         *
         * class Solution public String solution(String S); }
         *
         * that, given a non-empty string consisting of N lowercase English letters, returns the character which occurs most frequently in the string. If more than one character satisfies this requirement, the function should return the earliest alphabetically. For example, if both c and d are the most frequent letters, then the answer is c.
         *
         * For example, given a string:
         *
         * S = "hello"
         *
         * the function should return "l". It appears twice in S. No other characters appear as frequently.
         *
         * The attached code is still incorrect for some inputs. Despite the error(s), the code may produce a correct answer for the example test cases. The goal of the exercise is to find and fix the bug(s) in the
         *
         * implementation. You can modify at most four lines.
         *
         * Write an efficient algorithm for the following assumptions:
         *
         * • N is an integer within the range [1.100,000]; • string S consists only of lowercase letters (a-z).
         *
         * Copyright 2009-2020 by Codility Limited. All Rights Reserved. Unauthorized copying. publication or disclosure prohibited.
         */

        //String s = "test";
        String s = "testsample";
        System.out.printf("%s", solution(s));

    }

    /**
     * Complexity analysis
     *
     * Time complexity : O(n). Time complexity is O(n) because accessing the counter table is a constant time operation.
     *
     * Space complexity : O(1). Although we do use extra space, the space complexity is O(1) because the table's size stays constant no matter how large n is.
     *
     * Follow up
     *
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     *
     * Answer
     *
     * Use a hash table instead of a fixed size counter. Imagine allocating a large size array to fit the entire range of unicode characters, which could go up to more than 1 million. A hash table is a more generic solution and could adapt to any range of characters.
     * @param s
     * @return
     */

    public static char solution(String s) {
        int[] arr = new int[26];

        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        int max = -1;
        char c = ' ';

        for (int i = 0; i < 26; i++) {
            if (max < arr[i]) {
                max = arr[i];
                c = (char) (i + 'a');
            }
        }
        return c;

    }


    //Returns maximum occurring character
    public static char solution22(String s) {
        int[] arr = new int[256]; //there are 256 ASCII characters. This includes standard ASCII characters(0-127) and Extended ASCII characters(128-255).

        for (char c : s.toCharArray()) {
            arr[c]++;
        }

        int max = -1;
        char freq = ' ';

        for (char c : s.toCharArray()) {
            if (max < arr[c]) {
                max = arr[c];
                freq = c;
            }
        }

        return freq;
    }
}
