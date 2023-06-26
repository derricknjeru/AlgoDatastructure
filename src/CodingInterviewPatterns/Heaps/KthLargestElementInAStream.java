package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    /**
     * The time complexity of the KthLargest constructor is O(n log k), where n is the number of elements in the nums array and k is the value of the k parameter. This is because for each element in the nums array, we add it to the min heap, which takes O(log k) time. If the size of the min heap exceeds k, we remove the smallest element, which also takes O(log k) time. Therefore, adding each element to the min heap and maintaining its size at most k takes O(n log k) time.
     *
     * The time complexity of the add method is O(log k), as we add the new value to the min heap, and if the size of the min heap exceeds k, we remove the smallest element. Both of these operations take O(log k) time.
     *
     * Overall, the time complexity of constructing the KthLargest object and calling the add method multiple times is O(n log k), where n is the total number of elements processed and k is the value of the k parameter.
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
