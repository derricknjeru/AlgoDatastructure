package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;

public class kthLargestElementInArray {
    //https://leetcode.com/problems/kth-largest-element-in-an-array/

    /**
     * Time complexity: O(n⋅logk)
     *
     * Operations on a heap cost logarithmic time relative to its size. Because our heap is limited to a size of k,
     * operations cost at most O(logk). We iterate over nums, performing one or two heap operations at each iteration.
     *
     * We iterate n times, performing up to log k work at each iteration, giving us a time complexity of O(n⋅log k).
     *
     * Because k≤nn, this is an improvement on the previous approach.
     *
     * Space complexity: O(k)
     *
     * The heap uses O(k)
     */
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for(int a: nums){
                minHeap.add(a);
                if(minHeap.size()>k){
                    minHeap.remove();
                }
            }

            return minHeap.poll();

        }
    }
}
