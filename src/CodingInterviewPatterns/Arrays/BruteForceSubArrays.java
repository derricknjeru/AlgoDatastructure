package CodingInterviewPatterns.Arrays;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSubArrays {

    /**
     * The time complexity of the given code is O(n^3), where "n" is the length of the input array arr.
     * -
     * Here's the breakdown of the time complexity:
     * -
     * The outermost loop runs from i = 0 to i = n-1, where n is the length of the array. This loop has a time complexity of O(n) because it iterates through all elements of the array.
     * -
     * The second loop runs from j = i to j = n-1 for each value of i in the outer loop. This means that for each iteration of the outer loop, the inner loop runs from j = i to j = n-1. The worst-case scenario is when i is close to n-1, making the inner loop run for nearly the entire length of the array. Therefore, the inner loop also has a time complexity of O(n).
     * -
     * Inside the innermost loop, you are creating a subarray and adding elements to it. The innermost loop runs from k = i to k = j, which represents a subarray of size (j - i + 1). Since this loop is nested inside the second loop, the total number of times it runs is proportional to the sum of all possible subarray sizes, which is O(n^2) in the worst case.
     * -
     * Overall, the time complexity is O(n) * O(n) * O(n^2), which simplifies to O(n^4). However, because the innermost loop's iteration count depends on i and j, the actual number of iterations may be slightly less than n^4, but it is still considered to be O(n^3) in big O notation because it grows significantly with the input size.
     */
    public static List<List<Integer>> getSubArrays(int[] arr) {
        List<List<Integer>> subArrays = new ArrayList<>();
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                List<Integer> subarray = new ArrayList<>();
                //start = i, start = j
                for (int k = i; k <= j; k++) {
                    subarray.add(arr[k]);
                }
                subArrays.add(subarray);
            }

        }

        return subArrays;
    }

    /**
     * The time complexity of the getMaxSubArrayProduct function is O(n^2), where "n" is the length of the input array arr.
     * -
     * Here's the breakdown of the time complexity:
     * -
     * The outer loop runs from i = 0 to i = n-1, where n is the length of the array. This loop has a time complexity of O(n) because it iterates through all elements of the array.
     * -
     * The inner loop runs from j = i to j = n-1 for each value of i in the outer loop. This means that for each iteration of the outer loop, the inner loop runs from j = i to j = n-1. The worst-case scenario is when i is close to n-1, making the inner loop run for nearly the entire length of the array. Therefore, the inner loop also has a time complexity of O(n).
     * -
     * Inside the inner loop, you perform a constant amount of work (multiplying and comparing integers), so it doesn't significantly affect the overall time complexity.
     * -
     * Overall, the time complexity is O(n) * O(n), which simplifies to O(n^2). The function has a quadratic time complexity with respect to the length of the input array.
     *
     * @param arr
     * @return
     */

    public static int getMaxSubArrayProduct(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            int product = 1;
            for (int j = i; j < length; j++) {
                product *= arr[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }

    /**
     * The time complexity of the getMaxSubArrayProduct function is O(n^2), where "n" is the length of the input array arr.
     * -
     * Here's the breakdown of the time complexity:
     * -
     * The outer loop runs from i = 0 to i = n-1, where n is the length of the array. This loop has a time complexity of O(n) because it iterates through all elements of the array.
     * -
     * The inner loop runs from j = i to j = n-1 for each value of i in the outer loop. This means that for each iteration of the outer loop, the inner loop runs from j = i to j = n-1. The worst-case scenario is when i is close to n-1, making the inner loop run for nearly the entire length of the array. Therefore, the inner loop also has a time complexity of O(n).
     * -
     * Inside the inner loop, you perform a constant amount of work (multiplying and comparing integers), so it doesn't significantly affect the overall time complexity.
     * -
     * Overall, the time complexity is O(n) * O(n), which simplifies to O(n^2). The function has a quadratic time complexity with respect to the length of the input array.
     *
     * @param arr
     * @return
     */

    public static int[] getMaxSubArrayIndices(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;
        int length = arr.length;
        int start = -1;
        int end = -1;

        for (int i = 0; i < length; i++) {
            int product = 1;
            for (int j = i; j < length; j++) {
                product *= arr[j];
                if (product > maxProduct) {
                    maxProduct = product;
                    start = i;
                    end = j;
                }
            }
        }
        return new int[]{start, end};
    }


    public static void main(String[] args) {
        //int[] myArray = {1, 2, 3, 4};
        int[] myArray = {2, 3, -2, 4};
        List<List<Integer>> subarrays = getSubArrays(myArray);

        for (List<Integer> subarray : subarrays) {
            System.out.println(subarray);
        }

        int maxProduct = getMaxSubArrayProduct(myArray);
        System.out.println("Max Subarray Product: " + maxProduct);

        int[] indices = getMaxSubArrayIndices(myArray);
        System.out.println("Start Index: " + indices[0]);
        System.out.println("End Index: " + indices[1]);
        System.out.println("Here is the subArray");
        for (int k = indices[0]; k <= indices[1]; k++) {
            System.out.print(myArray[k] + " ");

        }
    }
}
