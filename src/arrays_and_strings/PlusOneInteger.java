package arrays_and_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     */
    public static void main(String[] args) {
        //int[] digits = {4, 3, 2, 1};
        //int[] digits = {1, 2, 3};
       // int[] digits = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] digits = {1, 2, 9};
        //int[] digits = {0, 0};
        //int[] digits = {9,9,9};

        List<Integer> list = new ArrayList<>();



        System.out.println(Arrays.toString(plusOne(digits)));
    }

    static int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }

            digits[i]=0;

        }
        //In a case of 999
        //We add one because length will increase by 1 since the result should be 1000
        int [] newIntArr= new int[digits.length+1];
        newIntArr[0]=1;

        return newIntArr;
    }


}
