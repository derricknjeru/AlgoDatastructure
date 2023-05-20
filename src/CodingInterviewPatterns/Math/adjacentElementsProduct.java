package CodingInterviewPatterns.Math;

public class adjacentElementsProduct {
    public static void main(String[] args) {
        /**
         * Given an array of integers, find the pair of adjacent elements that has the largest product and return that product.
         *
         * Example
         *
         * For inputArray = [3, 6, -2, -5, 7, 3], the output should be
         * solution(inputArray) = 21.
         *
         * 7 and 3 produce the largest product.
         *
         * Input/Output
         *
         * [execution time limit] 3 seconds (java)
         *
         * [memory limit] 1 GB
         *
         * [input] array.integer inputArray
         *
         * An array of integers containing at least two elements.
         *
         * Guaranteed constraints:
         * 2 ≤ inputArray.length ≤ 10,
         * -1000 ≤ inputArray[i] ≤ 1000.
         *
         * [output] integer
         *
         * The largest product of adjacent elements.
         */

        int[] nums = {-23, 4, -3, 8, -12};
        int largestProduct = solution(nums);
        System.out.println("The largest product of adjacent elements is: " + largestProduct);


    }

    private static int solution(int[] inputArray) {

        int maxProd = Integer.MIN_VALUE;

        for (int i = 0; i < inputArray.length - 1; i++) {
            int currProd = inputArray[i] * inputArray[i + 1];
            maxProd = Math.max(maxProd, currProd);
        }

        return maxProd;

    }


}
