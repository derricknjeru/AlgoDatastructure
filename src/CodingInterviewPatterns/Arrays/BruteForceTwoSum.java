package CodingInterviewPatterns.Arrays;

public class BruteForceTwoSum {

    public int[] twoSum(int[] nums, int target) {
        // Iterate through each element in the array
        for (int i = 0; i < nums.length; i++) {
            // Iterate through the remaining elements
            for (int j = i + 1; j < nums.length; j++) {
                // Check if the pair of elements adds up to the target
                if (nums[i] + nums[j] == target) {
                    // Return the indices of the two numbers
                    return new int[]{i, j};
                }
            }
        }

        // If no solution is found, return an empty array or throw an exception
        return new int[0];
    }

    public static void main(String[] args) {
        BruteForceTwoSum solution = new BruteForceTwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }


}
