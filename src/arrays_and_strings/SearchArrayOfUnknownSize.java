package arrays_and_strings;

public class SearchArrayOfUnknownSize {
    //https://just4once.gitbooks.io/leetcode-notes/content/leetcode/binary-search/702-search-in-a-sorted-array-of-unknown-size.html
    //https://goodtecher.com/leetcode-702-search-in-a-sorted-array-of-unknown-size/
    //https://leetcode.ca/2017-11-01-702-Search-in-a-Sorted-Array-of-Unknown-Size/
    //https://www.youtube.com/watch?v=LQYYkSe_9CY&t=69s
    //https://www.youtube.com/watch?v=XntJ3zKRT74
    /**
     * Given an integer array sorted in ascending order, write a function to searchtargetinnums. Iftargetexists, then return its index, otherwise return-1.However, the array size is unknown to you. You may only access the array using anArrayReader interface, where ArrayReader.get(k)returns the element of the array at indexk (0-indexed).
     * You may assume all integers in the array are less than 10000, and if you access the array out of bounds,ArrayReader.getwill return2147483647.
     * Example 1:
     * Input:
     * array
     * = [-1,0,3,5,9,12],
     * target
     * = 9
     * <p>
     * Output:
     * 4
     * <p>
     * Explanation:
     * 9 exists in
     * nums
     * and its index is 4
     * Example 2:
     * Input:
     * array
     * = [-1,0,3,5,9,12],
     * target
     * = 2
     * <p>
     * Output:
     * -1
     * <p>
     * Explanation:
     * 2 does not exist in
     * nums
     * so return -1
     * Note.md.md:
     * You may assume that all elements in the array are unique.
     * The value of each element in the array will be in the range[-9999, 9999].
     */

    interface ArrayReader {
        public int get(int index);
    }

    //Total time complexity 2 O(logn) == log(n)
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target)
            return 0;

        int left = 0, right = 1;
        //Time complexity is O(log n)
        while (reader.get(right) < target) {
            right *= 2;
        }
        //Time complexity is O(log n) //Normal binary search
        while (left <= right) {
            int mid = left + (right - left) / 2; //to avoid integer overflow
            int value = reader.get(mid);
            if (value == target) return mid;

            if (value > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
