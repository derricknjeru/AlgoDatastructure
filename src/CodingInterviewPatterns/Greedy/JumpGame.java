package CodingInterviewPatterns.Greedy;

public class JumpGame {
    public static void main(String[] args) {

    }

    //https://leetcode.com/problems/jump-game/description/
    //https://www.youtube.com/watch?v=Yan0cv2cLy8
    //https://www.youtube.com/watch?v=muDPTDrpS28
    class Solution {
        //Time complexity O(n)
        public boolean canJump(int[] nums) {
            int lastGoodPosition = nums.length - 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (i + nums[i] >= lastGoodPosition) {
                    lastGoodPosition = i;
                }
            }
            return lastGoodPosition == 0;
        }
    }

    class Solution2 {
        //Time complexity O(n)
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int reachable = 0;

            for (int i = 0; i < n; i++) {
                if (reachable < i) return false;
                reachable = Math.max(reachable, i + nums[i]);
            }
            return true;
        }
    }
}
