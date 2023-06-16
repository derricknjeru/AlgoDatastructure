package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    class KthLargest {
        int k;
        PriorityQueue<Integer> minHeap;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            minHeap = new PriorityQueue<>();
            for (int a : nums) {
                minHeap.add(a);
                if (minHeap.size() > k) {
                    minHeap.remove();
                }
            }
        }

        public int add(int val) {
            minHeap.add(val);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
            return minHeap.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}
