package arrays_and_strings;

import java.util.Arrays;
import java.util.HashSet;

public class FindTheDuplicateNumber {
    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(n \log n)O(nlogn)
     * <p>
     * Sorting takes O(n \log n)O(nlogn) time. This is followed by a linear scan, resulting in a total of O(n \log n)O(nlogn) + O(n)O(n) = O(n \log n)O(nlogn) time.
     * <p>
     * Space Complexity: O(\log n)O(logn) or O(n)O(n)
     * <p>
     * The space complexity of the sorting algorithm depends on the implementation of each programming language:
     * <p>
     * In Java, Arrays.sort() for primitives is implemented using a variant of the Quick Sort algorithm, which has a space complexity of O(\log n)O(logn)
     * In C++, the sort() function provided by STL uses a hybrid of Quick Sort, Heap Sort and Insertion Sort, with a worst case space complexity of O(\log n)O(logn)
     * In Python, the sort() function is implemented using the Timsort algorithm, which has a worst-case space complexity of O(n)O(n)
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        int ans = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans = nums[i];
                return ans;
            }

        }
        return ans;

    }

    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(n)O(n)
     * <p>
     * HashSet insertions and lookups have amortized constant time complexities. Hence, this algorithm requires linear time, since it consists of a single for loop that iterates over each element, looking up the element and inserting it into the set at most once.
     * <p>
     * Space Complexity: O(n)O(n)
     * <p>
     * We use a set that may need to store at most nn elements, leading to a linear space complexity of O(n)O(n).
     *
     * @param nums
     * @return
     */

    public int findDuplicate3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int a : nums) {
            if (!set.add(a)) {
                return a;
            }
        }

        return -1;

    }

    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(n)O(n)
     * <p>
     * Each element is visited at most twice (once in the first loop to find the duplicate and once in the second loop to restore the numbers).
     * <p>
     * Space Complexity: O(1)O(1)
     * <p>
     * All manipulation is done in place, so no additional storage (barring one variable) is needed.
     */
    public int findDuplicate4(int[] nums) {
        int duplicate = -1;
        //[1,3,4,2,2]
        // 1,-3,4, -2,
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.abs(nums[i]);
            if (nums[cur] < 0) {
                duplicate = cur;
                break;
            }
            nums[cur] *= -1;
        }

        // Restore numbers
        for (int i = 0; i < nums.length; i++)
            nums[i] = Math.abs(nums[i]);

        return duplicate;
    }
    //https://leetcode.com/problems/find-the-duplicate-number/solution/
    public int findDuplicate(int[] nums) {

        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];

        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        tortoise = nums[0];

        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
