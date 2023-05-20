package arrays_and_strings;

public class SearchArrayOfUnknownSize {
    //https://just4once.gitbooks.io/leetcode-notes/content/leetcode/binary-search/702-search-in-a-sorted-array-of-unknown-size.html
    //https://goodtecher.com/leetcode-702-search-in-a-sorted-array-of-unknown-size/
    //https://leetcode.ca/2017-11-01-702-Search-in-a-Sorted-Array-of-Unknown-Size/
    //https://www.youtube.com/watch?v=XntJ3zKRT74

    /**
     * Given an integer array sorted in ascending order, write a function to searchtargetinnums. Iftargetexists, then return its index, otherwise return-1.However, the array size is unknown to you.
     * You may only access the array using an ArrayReader interface, where ArrayReader.get(k)returns the element of the array at index k (0-indexed).
     * You may assume all integers in the array are less than 10000, and if you access the array out of bounds,ArrayReader.get will return 2147483647.
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

    /**
     * The time complexity of this algorithm is O(log n), where n is the index of the target element.
     * This is because we double the value of high at each iteration until it is greater than or equal
     * to the target element, which takes O(log n) time. The binary search within the boundaries takes O(log n) time,
     * resulting in a total time complexity of O(log n).
     *
     * @param reader
     * @param target
     * @return
     */
    private static int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) return 0;

        int left = 0, right = 1;
        //Time complexity is O(log n)
        while (reader.get(right) < target) {
            left = right;
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


    static class SortedArrayReader implements ArrayReader {
        private final int[] arr;

        public SortedArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int index) {
            if (index >= arr.length) {
                return Integer.MAX_VALUE;
            } else {
                return arr[index];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        SortedArrayReader reader = new SortedArrayReader(arr);
        int target = 19;
        int index = search(reader, target);
        if (index == -1) {
            System.out.println(target + " not found in the array.");
        } else {
            System.out.println(target + " found at index " + index + " in the array.");
        }
    }

}
