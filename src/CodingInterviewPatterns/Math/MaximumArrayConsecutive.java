package CodingInterviewPatterns.Math;

public class MaximumArrayConsecutive {
    /**
     * Ratiorg got statues of different sizes as a present from CodeMaster for his birthday,
     * each statue having an non-negative integer size. Since he likes to make things perfect,
     * he wants to arrange them from smallest to largest so that each statue will be bigger than the previous one exactly by 1.
     * He may need some additional statues to be able to accomplish that. Help him figure out the minimum number of additional statues needed.
     * <p>
     * Example
     * <p>
     * For statues = [6, 2, 3, 8], the output should be
     * solution(statues) = 3.
     * <p>
     * Ratiorg needs statues of sizes 4, 5 and 7
     */

    public static void main(String[] args) {
        /**
         * To find the minimum number of additional statues needed to arrange them from smallest to largest, we can follow these steps:
         *
         * Find the minimum and maximum values in the given array statues. Let's call them minVal and maxVal respectively.
         *
         * Calculate the length of the desired interval as intervalLength = maxVal - minVal + 1.
         *
         * Calculate the number of existing statues as numStatues = statues.length.
         *
         * Calculate the number of additional statues needed as additionalStatues = intervalLength - numStatues.
         *
         * Return the value of additionalStatues.
         */

        int[] statues = {6, 2, 3, 8};
        System.out.println(solution(statues));  // Output: 3
    }

    public static int solution(int[] statues) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        // Find the minimum and maximum values in the array
        for (int statue : statues) {
            minVal = Math.min(minVal, statue);
            maxVal = Math.max(maxVal, statue);
        }

        int intervalLength = maxVal - minVal + 1;
        int numStatues = statues.length;

        return intervalLength - numStatues;
    }
}
