package ArraysAndStrings;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

public class PlusOneInteger {
    /**
     * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
     * <p>
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
     * <p>
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     * <p>
     * Example 1:
     * <p>
     * Input: [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * Example 2:
     * <p>
     * Input: [4,3,2,1]
     * Output: [4,3,2,2]
     * Explanation: The array represents the integer 4321.
     *
     */
    public static void main(String[] args) {
        //int[] digits = {4, 3, 2, 1};
        //int[] digits = {1, 2, 3};
        int[] digits = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        plusOne(digits);

        System.out.println(Arrays.toString(plusOne(digits)));
    }

    static int[] plusOne(int[] digits) {
        int k = 0;
        int x;
        for (int i = 0; i < digits.length; i++) {
            if (i == digits.length - 1) {
                x = digits[i] + 1;
            } else {
                x = digits[i];
            }

            k = 10 * k + x;


        }

        String stringArray = Integer.toString(k);


        int[] newGuess = new int[stringArray.length()];

        for (int i = 0; i < stringArray.length(); i++) {
            newGuess[i] = stringArray.charAt(i) - '0';
        }


        return newGuess;
    }
}
