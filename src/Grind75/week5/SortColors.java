package Grind75.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortColors {
    //https://leetcode.com/problems/sort-colors/description/
    //https://www.youtube.com/watch?v=4xbWSRZHqac
    //https://www.youtube.com/watch?v=sEQk8xgjx64
    //https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};

        Solution solution = new Solution();
        solution.sortColors(nums);

        System.out.print("Sorted Colors: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    static class Solution {
        //Dutch National Flag algorithm by Edsger Dijkstra

        /**
         * The time complexity of the sortColors function in the provided Solution class is O(n), where 'n' is the number of elements in the input array nums.
         *
         * Here's the breakdown of the time complexity:
         *
         * The main loop iterates through the array nums at most 'n' times, as each element is visited exactly once.
         * Inside the loop, the swap operation takes constant time O(1).
         * Since the overall time complexity of the sortColors function is determined by the main loop, which iterates through the array once, the time complexity is O(n). This implementation is efficient and meets the linear time complexity requirement for sorting colors as specified by the Dutch National Flag algorithm.
         * @param nums
         */
        public void sortColors(int[] nums) {
            int mid = 0; // to navigate the list
            int l = 0;   // pointer for the left boundary of 0's
            int r = nums.length - 1;  // pointer for the right boundary of 2's

            while (mid <= r) {
                if (nums[mid] == 0) {
                    swap(nums, mid, l);
                    mid++;
                    l++;
                } else if (nums[mid] == 1) {
                    // we are in the right place; no need to do anything
                    mid++;
                } else if (nums[mid] == 2) {
                    swap(nums, mid, r);
                    r--;
                }
            }
        }

        private void swap(int[] nums, int l, int r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
    }

    class Solution2 {
        /**
         * The time complexity of your sortColors function using a HashMap would be O(n), where 'n' is the number of elements in the input array nums.
         *
         * @param nums
         */
        //bucket sort
        public void sortColors(int[] nums) {
            // o(n)
            Map<Integer, Integer> occ = new HashMap<>();
            for (int a : nums) {
                occ.put(a, occ.getOrDefault(a, 0) + 1);
            }

            int index = 0;
            for (Map.Entry<Integer, Integer> entry : occ.entrySet()) {
                int color = entry.getKey();
                int times = entry.getValue();
                while (times-- > 0) nums[index++] = color;
            }

        }
    }

    class Solution3 {
        /**
         * The time complexity of your sortColors function using a PriorityQueue would be O(n log n), where 'n' is the number of elements in the input array nums.
         *
         * @param nums
         */
        public void sortColors(int[] nums) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int a : nums) minHeap.add(a);

            int index = 0;
            while (!minHeap.isEmpty()) {
                nums[index++] = minHeap.remove();
            }

        }
    }
}
