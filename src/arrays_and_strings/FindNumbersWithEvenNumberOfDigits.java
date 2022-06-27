package arrays_and_strings;

import java.util.Arrays;
import java.util.HashMap;

public class FindNumbersWithEvenNumberOfDigits {
    /**
     * Find Numbers with Even Number of Digits
     * Given an array nums of integers, return how many of them contain an even number of digits.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [12,345,2,6,7896]
     * Output: 2
     * Explanation:
     * 12 contains 2 digits (even number of digits).
     * 345 contains 3 digits (odd number of digits).
     * 2 contains 1 digit (odd number of digits).
     * 6 contains 1 digit (odd number of digits).
     * 7896 contains 4 digits (even number of digits).
     * Therefore only 12 and 7896 contain an even number of digits.
     * Example 2:
     * <p>
     * Input: nums = [555,901,482,1771]
     * Output: 1
     * Explanation:
     * Only 1771 contains an even number of digits.
     *
     * @param args
     */
    public static void main(String[] args) {
        FindNumbersWithEvenNumberOfDigits numbers = new FindNumbersWithEvenNumberOfDigits();

        //int[] nums = {12, 345, 2, 6, 7896};

        // System.out.printf("How many contains an even number of digits %d",
        //       numbers.findNumbers(nums));
        String x = String.valueOf(-123);

        System.out.printf("%s", x.length());


    }

    public int findNumbers2(int[] nums) {
        int count = 0;
        int maxCount = 0;

        if (nums == null || nums.length == 0) return count;

        int size = nums.length;

        HashMap<Integer, Integer> digitCount = new HashMap<>();

        for (int i = 0; i < size; i++) {
            while (nums[i] > 0) {
                int rem = nums[i] % 10;
                System.out.print(rem);
                digitCount.put(i, digitCount.getOrDefault(i, 0) + 1);
                maxCount++;
                nums[i] /= 10;
            }
            System.out.println("\n---------------");
            System.out.println("The count " + digitCount.get(i));
            System.out.println("The max count " + maxCount);
            if (maxCount % 2 == 0) {
                count++;
            }
            System.out.println("\n---------------");
            maxCount = 0;
        }

        for (int i = 0; i < size; i++) {
            if (digitCount.get(i) % 2 == 0) {
                count++;
            }
        }

        return count;

    }

    public int findNumbers(int[] nums) {
        int count = 0;
        if (nums == null || nums.length == 0) return count;

        for (int num : nums) {
            int maxCount = 0;
            while (num > 0) {
                int rem = num % 10;
                maxCount++;
                num /= 10;
            }
            System.out.println("\n---------------");
            System.out.println("The max count " + maxCount);

            if (maxCount % 2 == 0) count++;

            System.out.println("\n---------------");
        }

        return count;

    }

    public int findNumbers3(int[] nums) {
        int count = 0;

        if (nums == null || nums.length == 0) return count;

        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                count++;
            }
        }

        return count;

    }

    public int findNumbers4(int[] nums) {
        int count = 0;

        if (nums == null || nums.length == 0) return count;

        return (int) Arrays.stream(nums)
                .filter(num -> String.valueOf(num).length() % 2 == 0)
                .count();

    }
}
