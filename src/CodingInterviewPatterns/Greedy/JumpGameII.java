package CodingInterviewPatterns.Greedy;

public class JumpGameII {
    //https://leetcode.com/problems/jump-game-ii/description/
    //https://www.youtube.com/watch?v=dJ7sWiOoK7g

    public class Solution {
        public int jump(int[] nums) {
            int l = 0;
            int r = 0;
            int res = 0;
            while (r < (nums.length - 1)) {
                int maxJump = 0;
                for (int i = l; i <= r; i++) {
                    maxJump = Math.max(maxJump, i + nums[i]);
                }
                l = r + 1;
                r = maxJump;
                res++;
            }
            return res;
        }
    }

    class Solution2 {
        public int jump(int[] nums) {
            int res = 0;
            int l = 0, r = 0;
            int n = nums.length;

            while (r < n - 1) {
                int maxJump = 0;
                int i = l;
                while (i <= r) {
                    maxJump = Math.max(maxJump, i + nums[i]);
                    i++;
                }

                l = r + 1;
                r = maxJump;
                res++;
            }

            return res;
        }
    }
}
