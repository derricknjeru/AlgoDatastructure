package Google;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinAmplitude {
    //https://leetcode.com/discuss/interview-question/553399/
    //https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/description/
    //https://www.youtube.com/watch?v=Ht6hc3UsvY0

    /**
     * You are given an integer array nums.
     * <p>
     * In one move, you can choose one element of nums and change it to any value.
     * <p>
     * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [5,3,2,4]
     * Output: 0
     * Explanation: We can make at most 3 moves.
     * In the first move, change 2 to 3. nums becomes [5,3,3,4].
     * In the second move, change 4 to 3. nums becomes [5,3,3,3].
     * In the third move, change 5 to 3. nums becomes [3,3,3,3].
     * After performing 3 moves, the difference between the minimum and maximum is 3 - 3 = 0.
     * Example 2:
     * <p>
     * Input: nums = [1,5,0,10,14]
     * Output: 1
     * Explanation: We can make at most 3 moves.
     * In the first move, change 5 to 0. nums becomes [1,0,0,10,14].
     * In the second move, change 10 to 0. nums becomes [1,0,0,0,14].
     * In the third move, change 14 to 1. nums becomes [1,0,0,0,1].
     * After performing 3 moves, the difference between the minimum and maximum is 1 - 0 = 0.
     * It can be shown that there is no way to make the difference 0 in 3 moves.
     * Example 3:
     * <p>
     * Input: nums = [3,100,20]
     * Output: 0
     * Explanation: We can make at most 3 moves.
     * In the first move, change 100 to 7. nums becomes [4,7,20].
     * In the second move, change 20 to 7. nums becomes [4,7,7].
     * In the third move, change 4 to 3. nums becomes [7,7,7].
     * After performing 3 moves, the difference between the minimum and maximum is 7 - 7 = 0.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     */

    //Time complexity O(nlogn) because of sorting
    //Space complexity --no new space needed
    //Sliding Window
    public static int minDifference(int[] nums) {
        int len = nums.length;
        if (len <= 4) return 0;
        Arrays.sort(nums);

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            //at start - (len - 1 - 3 + i) is the 4th element from end of the list
            res = Math.min(res, nums[len - 1 - 3 + i] - nums[i]);
        }
        return res;
    }

    // //Time complexity O(mlogn) where m is the size of the array
    //Space complexity O(n)

    public static int minDifference2(int[] nums) {
        int len = nums.length;
        if (len <= 4) return 0;

        PriorityQueue<Integer> minLargest = new PriorityQueue<>(); //from smallest to largest
        PriorityQueue<Integer> maxSmallest = new PriorityQueue<>((a, b) -> b - a); //from largest to smallest
        //Time complexity nlogn
        for (int num : nums) {
            minLargest.offer(num);
            maxSmallest.offer(num);

            if (minLargest.size() > 4) minLargest.poll();
            if (maxSmallest.size() > 4) maxSmallest.poll();
        }

        int[] smallNumArray = new int[4];
        int index = 0;
        //constant time - 4
        while (!maxSmallest.isEmpty()) {
            smallNumArray[index++] = maxSmallest.poll();
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            res = Math.min(res, minLargest.poll() - smallNumArray[3 - i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num = {1, 5, 0, 10, 14};
        System.out.println(minDifference2(num));
    }
}
