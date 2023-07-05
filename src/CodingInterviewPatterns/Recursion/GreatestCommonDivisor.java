package CodingInterviewPatterns.Recursion;

public class GreatestCommonDivisor {
    /**
     * The time complexity of this solution can be analyzed as follows:
     * <p>
     * Finding the maximum and minimum elements in the array:
     * <p>
     * This requires iterating through the array once, so it has a time complexity of O(n), where n is the length of the array.
     * Calculating the greatest common divisor (GCD):
     * <p>
     * The GCD calculation is performed recursively using the Euclidean algorithm.
     * In the worst case, the Euclidean algorithm has a time complexity of O(log(min(max, min))), where max is the maximum element in the array and min is the minimum element in the array.
     * The algorithm reduces the larger number by taking the modulus with the smaller number until the smaller number becomes zero, so it typically performs a logarithmic number of steps.
     * Overall, the time complexity of this solution is O(n + log(min(max, min))).
     * <p>
     * It's worth noting that the space complexity of this solution is O(1) since it only uses a constant amount of additional space regardless of the input size.
     */
    class Solution {
        public int findGCD(int[] nums) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }

            return gcd(max, min);
        }

        private int gcd(int a, int b) {
            if (a == 0) return b;
            return gcd(b % a, a);
        }
    }
}
