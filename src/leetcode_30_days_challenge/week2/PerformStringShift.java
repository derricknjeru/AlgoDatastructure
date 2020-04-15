package leetcode_30_days_challenge.week2;

import java.util.Scanner;

public class PerformStringShift {
    /**
     * You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:
     * <p>
     * direction can be 0 (for left shift) or 1 (for right shift).
     * amount is the amount by which string s is to be shifted.
     * A left shift by 1 means remove the first character of s and append it to the end.
     * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
     * Return the final string after all operations.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "abc", shift = [[0,1],[1,2]]
     * Output: "cab"
     * Explanation:
     * [0,1] means shift to left by 1. "abc" -> "bca"
     * [1,2] means shift to right by 2. "bca" -> "cab"
     * Example 2:
     * <p>
     * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
     * Output: "efgabcd"
     * Explanation:
     * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
     * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
     * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
     * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 100
     * s only contains lower case English letters.
     * 1 <= shift.length <= 100
     * shift[i].length == 2
     * 0 <= shift[i][0] <= 1
     * 0 <= shift[i][1] <= 100
     * Hide Hint #1
     * Intuitively performing all shift operations is acceptable due to the constraints.
     * Hide Hint #2
     * You may notice that left shift cancels the right shift, so count the total left shift times (may be negative if the final result is right shift), and perform it once.
     */
    public static void main(String[] args) {
        String s = "abc";
        int[][] shift = {{0, 1}, {1, 2}};
        PerformStringShift performStringShift = new PerformStringShift();
        performStringShift.stringShift(s, shift);

        System.out.println(performStringShift.stringShift(s, shift));
        Scanner in = new Scanner(System.in);
        String S = in.next();
        try {
            // the String to int conversion happens here
            int i = Integer.parseInt(S.trim());

            // print out the value after the conversion
            System.out.println(i);
        } catch (NumberFormatException nfe) {
            System.out.println("Bad string");
        }
    }

    public String stringShift(String s, int[][] shift) {
        int totalShit = 0;

        for (int[] ints : shift) {
            int direction = ints[0];
            int amount = ints[1];

            if (direction == 0) {
                //left shift
                totalShit -= amount;
            } else {
                totalShit += amount;
            }
        }

        String newFront;
        String newback;

        if (totalShit < 0) {
            //standardize @effective rotation
            totalShit = Math.abs(totalShit) % s.length();
            //left shift
            //example
            //abcde  and left shift by 2
            //newfront=cde
            // newback=ab

            newFront = s.substring(totalShit);
            newback = s.substring(0, totalShit);


        } else if (totalShit > 0) {
            //standardize @effective rotation
            totalShit = Math.abs(totalShit) % s.length();
            //example
            //abcder  and right shift by 2
            //newfront=er
            //newback=abcd

            newFront = s.substring(s.length() - totalShit);
            newback = s.substring(0, s.length() - totalShit);

        } else {
            return s;
        }
        return newFront + newback;

    }
}
