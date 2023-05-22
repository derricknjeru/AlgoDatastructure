package arrays_and_strings;

public class AverageEvenDivisibleByThree {
    public static void main(String[] args) {
        //https://leetcode.com/problems/average-value-of-even-numbers-that-are-divisible-by-three/
    }

    /**
     * Time complexity is O(n)
     * Space complexity is O(1) constant time
     * <p>
     * Given an integer array nums of positive integers, return the average value of all even integers that are divisible by 3.
     * <p>
     * Note.md.md.md.md that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,3,6,10,12,15]
     * Output: 9
     * Explanation: 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2 = 9.
     * Example 2:
     * <p>
     * Input: nums = [1,2,4,7,10]
     * Output: 0
     * Explanation: There is no single number that satisfies the requirement, so return 0.
     *
     * @param nums
     * @return
     */
    public int averageValue(int[] nums) {
        int sum = 0;
        int n = 0;
        for (int a : nums) {
            //a%2==0 && a%3==0 can work
            if (a % 6 == 0) { //using 6 because 6 is the gcd of 2 & 3
                sum += a;
                n++;
            }
        }

        if (sum == 0) return 0; //To avoid arithmetic exception

        return sum / n;
    }
}
