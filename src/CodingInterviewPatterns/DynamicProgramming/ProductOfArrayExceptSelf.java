package CodingInterviewPatterns.DynamicProgramming;

public class ProductOfArrayExceptSelf {
    /**
     * Prefix Product Calculation: O(n)
     * <p>
     * In the first pass through the input array nums, we calculate the prefix product by multiplying the previous prefix product with the current element.
     * This operation requires traversing the array once from left to right,
     * resulting in a time complexity of O(n).
     * Suffix Product Calculation and Result Update: O(n)
     * <p>
     * In the second pass through the input array nums, we calculate the suffix product and update the result array.
     * This involves traversing the array once from right to left.
     * For each element, we multiply it with the current suffix product and
     * update the corresponding value in the result array.
     * This operation also has a time complexity of O(n).
     * Overall, the time complexity of the solution is O(n) since both passes through the input array have linear time complexity.
     * Therefore, the algorithm satisfies the requirement of running in O(n) time complexity.
     * <p>
     * The space complexity of the given solution to the problem "Product of Array Except Self" is O(1)
     * because it uses a constant amount of extra space.
     * <p>
     * Here's the breakdown of the space complexity:
     * <p>
     * Output Array: O(1)
     * <p>
     * The result array is the output array that stores the product of all elements except
     * the current element. It is created based on the size of the input array nums,
     * but it does not contribute to the space complexity analysis because the problem statement
     * states that the output array does not count as extra space.
     * Therefore, the space complexity for the result array is considered O(1).
     * Variables: O(1)
     * <p>
     * The solution uses a few extra variables such as n, suffixProduct,
     * i, and other loop counters. These variables occupy
     * a constant amount of space and do not depend on the size of the input array nums.
     * Hence, their space complexity is O(1).
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Calculate prefix product
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Calculate suffix product and update result array
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
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

}
