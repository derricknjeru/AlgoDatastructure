package CodingInterviewPatterns.Maths;

public class AlmostIncreasingSequence {
    /**
     * Given a sequence of integers as an array, determine whether it is possible to obtain a strictly increasing sequence by removing no more than one element from the array.
     * <p>
     * Note: sequence a0, a1, ..., an is considered to be a strictly increasing if a0 < a1 < ... < an. Sequence containing only one element is also considered to be strictly increasing.
     * <p>
     * Example
     * <p>
     * For sequence = [1, 3, 2, 1], the output should be
     * solution(sequence) = false.
     * <p>
     * There is no one element in this array that can be removed in order to get a strictly increasing sequence.
     * <p>
     * For sequence = [1, 3, 2], the output should be
     * solution(sequence) = true.
     * <p>
     * You can remove 3 from the array to get the strictly increasing sequence [1, 2]. Alternately, you can remove 2 to get the strictly increasing sequence [1, 3]
     *
     * @param sequence
     * @return
     */
    static boolean solution(int[] sequence) {
        int count = 0; // Counter for removed elements

        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] <= sequence[i - 1]) {
                count++;
                if (count > 1) {
                    return false;
                }

                // Check if removing the current element makes the sequence increasing
                if (i > 1 && sequence[i] <= sequence[i - 2]) {
                    sequence[i] = sequence[i - 1]; // Remove the current element
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        /**
         * In this approach, we iterate through the array from the beginning (i = 0) to the second-to-last element. If an element is found that is not strictly less than the next element, we increment the count variable to keep track of the number of removed elements.
         *
         * If count becomes greater than 1, it means we need to remove more than one element to make the sequence strictly increasing, so we return false.
         *
         * Additionally, we have an extra condition to handle cases where removing the current element alone does not make the sequence strictly increasing. This condition checks if removing the previous element and keeping the current element, or removing the next element and keeping the current element, still results in a non-strictly increasing sequence. If this condition is met, it means we need to remove more than one element to achieve a strictly increasing sequence, so we return false.
         *
         * If the loop completes without encountering more than one removed element or violating the additional condition, it means we can obtain a strictly increasing sequence by removing no more than one element, so we return true.
         *
         * The main function provides the same examples as before to demonstrate the usage of the isStrictlyIncreasing function.
         */
        int[] sequence1 = {1, 3, 2, 1};
        System.out.println(solution(sequence1)); // false

        int[] sequence2 = {1, 3, 2};
        System.out.println(solution(sequence2)); // true
    }
}
