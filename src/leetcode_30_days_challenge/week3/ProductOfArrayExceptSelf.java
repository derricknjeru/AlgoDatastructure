package leetcode_30_days_challenge.week3;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    /**
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     * <p>
     * Example:
     * <p>
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
     * <p>
     * Note.md: Please solve it without division and in O(n).
     * <p>
     * Follow up:
     * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
     */

    public static void main(String[] args) {
        ProductOfArrayExceptSelf product = new ProductOfArrayExceptSelf();
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(product.productExceptSelf(nums)));
    }

    public int[] productExceptSelf(int[] nums) {
        int size = nums.length;
        int[] result = new int[size];

        int[] left_products = new int[size];
        int[] right_products = new int[size];

        left_products[0] = 1; //left most product
        right_products[size - 1] = 1;  //right most product

        for (int i = 1; i < size; i++) {
            ///////////////////left item* left most product so far
            left_products[i] = nums[i - 1] * left_products[i - 1];

        }
        //int i = size - 2 starting at second last item
        for (int i = size - 2; i >= 0; i--) {
            ///////////////////right item* right most product so far
            right_products[i] = nums[i + 1] * right_products[i + 1];
        }

        for (int i = 0; i < size; i++) {
            result[i] = left_products[i] * right_products[i];
        }

        return result;
    }

    //https://www.youtube.com/watch?v=tSRFtR3pv74
    public int[] productExceptSelf2(int[] nums) {
        int size = nums.length;
        int[] result = new int[size];
        return result;
    }
}
