package CodingInterviewPatterns.HashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElement {
    //https://www.geeksforgeeks.org/priority-queue-class-in-java/

    /**
     * The time complexity of the given solution to find the k most frequent elements in an integer array is O(n + k log k),
     * where n is the length of the input array nums and k is the value specified.
     * <p>
     * Here's the breakdown of the time complexity:
     * <p>
     * Counting the frequency of each element: O(n)
     * <p>
     * In the first loop, we iterate through the input array nums of length n and count the
     * frequency of each element using a HashMap. This operation takes O(n) time complexity.
     * Building the Min Heap: O(n log k)
     * <p>
     * In the second loop, we iterate through the key set of the frequency map,
     * which has at most k unique elements. Inserting an element into the Min Heap takes O(log k) time complexity, and we perform this operation for at most n elements. Hence, building the Min Heap takes O(n log k) time complexity.
     * Retrieving the k most frequent elements: O(k log k)
     * <p>
     * In the third loop, we retrieve the k most frequent elements
     * from the Min Heap by removing elements k times. Removing an element from the Min Heap takes O(log k)
     * time complexity, and we perform this operation k times.
     * Therefore, retrieving the k most frequent elements takes O(k log k) time complexity.
     * Overall, the time complexity of the algorithm is dominated by the step of building the Min Heap,
     * resulting in a time complexity of O(n + k log k).
     * This complexity is better than O(n log n), as required by the problem's constraints.
     *
     * @param nums
     * @param k
     * @return
     */

    //O(n + k log k)
    public int[] topKFrequent(int[] nums, int k) {
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

    public int[] topKFrequent2(int[] nums, int k) {
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
