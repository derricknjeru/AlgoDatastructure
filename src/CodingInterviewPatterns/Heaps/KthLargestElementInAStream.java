package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
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
    class KthLargest {
        /**
         * Given N as the length of nums and M as the number of calls to add(),
         * The time complexity of the KthLargest constructor is O(n log n),
         * The time complexity of the add method is O(m log k),
         * as we add the new value to the min heap, and if the size of the min heap exceeds k,
         * we remove the smallest element. Both of these operations take O(log k) time.
         *
         * Overall, the time complexity of constructing the KthLargest object and calling the add method multiple times is O(n log k), where n is the total number of elements processed and k is the value of the k parameter.
         */
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
