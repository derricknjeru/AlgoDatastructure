package CodingInterviewPatterns.HashTable.quickreview;

import java.util.*;


public class TopKFrequentElement {
    //https://www.geeksforgeeks.org/priority-queue-class-in-java/

    /**
     * The overall time complexity of the updated solution is O(n),
     * which meets the requirement of being better than O(n log n) for the given problem.
     */
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> occ = new HashMap<>();
            int maxFreq = 0;

            // Count frequencies of each number
            for (int num : nums) {
                int freq = occ.getOrDefault(num, 0) + 1;
                occ.put(num, freq);
                maxFreq = Math.max(maxFreq, freq);
            }

            // Create a list of lists to store numbers with the same frequency
            List<List<Integer>> buckets = new ArrayList<>(maxFreq + 1);
            for (int i = 0; i <= maxFreq; i++) {
                buckets.add(new ArrayList<>());
            }

            // Assign numbers to their respective frequency buckets
            for (int num : occ.keySet()) {
                int freq = occ.get(num);
                buckets.get(freq).add(num);
            }

            // Retrieve the top k frequent numbers
            int[] res = new int[k];
            int index = 0;

            for (int i = maxFreq; i >= 0 && index < k; i--) {
                List<Integer> bucket = buckets.get(i);
                for (int num : bucket) {
                    res[index++] = num;
                    if (index == k) {
                        break;
                    }
                }
            }

            return res;
        }
    }


    /**
     * The time complexity of the topKFrequent method can be analyzed as follows:
     * <p>
     * Constructing the frequency map (occ):
     * <p>
     * Iterating over the nums array takes O(n) time, where n is the length of the nums array.
     * Inserting elements into the occ map using occ.put and occ.getOrDefault operations takes O(1) time on average.
     * Therefore, constructing the frequency map takes O(n) time.
     * Constructing the min-heap (minHeap):
     * <p>
     * Iterating over the occ map takes O(m) time, where m is the number of unique elements in nums.
     * Adding elements to the minHeap takes O(log k) time for each insertion, as the size of the heap is limited to k.
     * Therefore, constructing the min-heap takes O(m log k) time.
     * Constructing the result array (res):
     * <p>
     * Extracting elements from the minHeap and storing them in the res array takes O(k log k) time.
     * Since the size of the minHeap is limited to k, extracting each element takes O(log k) time.
     * Therefore, constructing the result array takes O(k log k) time.
     * Overall, the time complexity of the topKFrequent method is dominated by the step of constructing the min-heap,
     * which is O(m log k). In the worst case, where m is equal to the number of elements in nums,
     * the time complexity would be O(n log k).
     *
     * @param nums
     * @param k
     * @return
     */

    //O(n log k).
    public int[] topKFrequent2(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }

    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : nums) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            pq.add(entry);
            //poll the top item since it's the smallest
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[pq.size()];
        int index = 0;

        while (!pq.isEmpty()) {
            result[index++] = pq.poll().getKey();
        }

        return result;

    }
}
