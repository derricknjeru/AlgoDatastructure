package CodingInterviewPatterns.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {
    //https://leetcode.com/problems/find-the-duplicate-number/description/
    //https://www.youtube.com/watch?v=iAe0GgyFrg4&t=0s
    //https://www.youtube.com/watch?v=iAe0GgyFrg4
    //https://www.youtube.com/watch?v=wjYnzkAhcNk
    public static void main(String[] args) {
        Solution sln = new Solution();

        //Example 1:
        int[] nums = {1, 3, 4, 2, 2}; //Output: 2
        System.out.println(sln.findDuplicate(nums));

        //Example 2:
        int[] nums2 = {3, 1, 3, 4, 2}; //Output: 3
        System.out.println(sln.findDuplicate(nums2));
    }

    static class Solution {
        // Time complexity: O(n)
        public int findDuplicate(int[] nums) {
            int hare = nums[0];
            int tortoise = nums[0];

            // Find the intersection point of the two pointers
            do {
                tortoise = nums[tortoise];
                hare = nums[nums[hare]];
            } while (tortoise != hare);  //will execute until this is false.

            /**
             * //https://stackoverflow.com/questions/22512830/what-condition-does-whiletrue-test-when-is-it-true-and-false
             * while(true){
             *             tortoise = nums[tortoise];
             *             hare = nums[nums[hare]];
             *             if(tortoise == hare) break;
             *         }
             */

            // Move one pointer back to the start
            hare = nums[0];

            // Move both pointers at the same pace until they meet at the entrance of the cycle
            while (hare != tortoise) {
                tortoise = nums[tortoise];
                hare = nums[hare];
            }

            return hare; // Duplicate number found
        }
    }


    static class Solution2 {
        //O(n) Time complexity
        //O(n) //space
        public int findDuplicate(int[] nums) {
            Set<Integer> seen = new HashSet<>();
            for (int a : nums) {
                if (!seen.add(a)) return a;
            }
            return -1;
        }
    }

    static class Solution3 {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums); //0(nlogn)
            //space -> (1)
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    return nums[i];
                }
            }
            return -1;
        }
    }
}
