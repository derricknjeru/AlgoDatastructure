package companies.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KLargestSubSetSum {

    /**
     * A combo is defined as a subset of the given n terms. The total priority is the sum of the individual items of the combo. Design an algorithm that can find the k combos with highest priority. Two combos are considered different if they have different subsets of items.
     * Return the array of k integers where the ith term denotes the priority of the best combo. Combos should be returned arranged best to worst.
     *
     * Example: n = 3, arr = [3,5,-2], k=3
     * All possible priority of combos are 0,3,5,-2,8,3,1,6
     * The best 3 are [8,6,5]
     */

    /**
     * Another phrasing
     * How to generate k largest subset sums for a given array (contains positive and negative numbers)
     * 6
     * <p>
     * <p>
     * So the question is pretty straight forward, given an array of size N( N<=10^5) , we want to generate k greatest subset sums where k is in worst case MIN of (2000 and 2^N).
     * <p>
     * We have to output in the decreasing order.
     * <p>
     * Is there any way to do this in less than exponential complexity. My thinking is that If we have to generate 2^N items , how can the complexity be less than 2^N,
     * <p>
     * Asked in amazon OA(question is called find k maximum priority)
     */
    /**
     * This algorithm will take O(N+k) memory and O(N log(N) + k log(k)) work to generate the top k answers.
     * It depends on both N and k but is exponential in neither.
     *
     * @param nums
     * @return
     */
    private static List<Integer> findKLargestSubsetSum(int[] nums, int k) {
        //Algo https://stackoverflow.com/questions/72114300/how-to-generate-k-largest-subset-sums-for-a-given-array-contains-positive-and-n
        //Implementation https://leetcode.com/playground/Gm2v6aee
        List<Integer> ans = new ArrayList<>();
        //First, in a single pass, we find the sum of the positive numbers.
        // This is the maximum sum. We initialize our answer array with [maximum_sum].

        int maximum_sum = 0;
        for (int num : nums) {
            if (num > 0)
                maximum_sum += num;
        }

        ans.add(maximum_sum);


        //Next, we create an array av of the absolute values, sorted from smallest to largest.

        int len = nums.length;
        //we can reuse the loop above
        int[] av = new int[len];
        int index = 0;
        for (int num : nums) {
            av[index++] = Math.abs(num);
        }

        Arrays.sort(av);

        //Next, we create a priority queue upcoming. It will start with one pair in it.
        // That pair will be (maximum_sum - av[0], 0). The pairs are compared lexicographically with
        // largestt sum first.

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        maxHeap.add(new int[]{maximum_sum - av[0], 0});

        /**
         * Until we have enough elements in answer we will:
         *
         * get (next_sum, i) from upcoming
         * add next_sum to answer
         * if i < N:
         *     add (next_sum + av[i] - av[i+1], i+1) to upcoming
         *     add (next_sum - av[i+1], i+1) to upcoming
         */


        while (ans.size() < k) {
            int cur[] = maxHeap.poll();
            int curSum = cur[0];
            int i = cur[1];
            ans.add(curSum);

            if (i + 1 < len) {
                maxHeap.add(new int[]{curSum + av[i] - av[i + 1], i + 1});
                maxHeap.add(new int[]{curSum - av[i + 1], i + 1});
            }

        }


        return ans;
    }


    private static List<Integer> findKLargestSubsetSumOpt(int[] nums, int k) {
        //Algo https://stackoverflow.com/questions/72114300/how-to-generate-k-largest-subset-sums-for-a-given-array-contains-positive-and-n
        //Implementation https://leetcode.com/playground/Gm2v6aee
        List<Integer> ans = new ArrayList<>();
        //First, in a single pass, we find the sum of the positive numbers.
        // This is the maximum sum. We initialize our answer array with [maximum_sum].

        int maximum_sum = 0;
        int len = nums.length;
        //we can reuse the loop above
        int[] av = new int[len];
        int index = 0;
        for (int num : nums) {
            if (num > 0) {
                maximum_sum += num;
            }
            av[index++] = Math.abs(num);
        }

        ans.add(maximum_sum);

        //Next, we create an array av of the absolute values, sorted from smallest to largest.
        Arrays.sort(av);

        //Next, we create a priority queue upcoming. It will start with one pair in it.
        // That pair will be (maximum_sum - av[0], 0). The pairs are compared lexicographically with
        // largestt sum first.

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        maxHeap.add(new int[]{maximum_sum - av[0], 0});

        /**
         * Until we have enough elements in answer we will:
         *
         * get (next_sum, i) from upcoming
         * add next_sum to answer
         * if i < N:
         *     add (next_sum + av[i] - av[i+1], i+1) to upcoming
         *     add (next_sum - av[i+1], i+1) to upcoming
         */


        while (ans.size() < k) {
            int cur[] = maxHeap.poll();
            int curSum = cur[0];
            int i = cur[1];
            ans.add(curSum);

            if (i + 1 < len) {
                maxHeap.add(new int[]{curSum + av[i] - av[i + 1], i + 1});
                maxHeap.add(new int[]{curSum - av[i + 1], i + 1});
            }

        }


        return ans;
    }

    public static void main(String[] args) {

    }

 }
