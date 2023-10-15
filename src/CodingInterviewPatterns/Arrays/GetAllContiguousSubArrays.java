package CodingInterviewPatterns.Arrays;

import java.util.ArrayList;
import java.util.*;

public class GetAllContiguousSubArrays {
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 3};
        int length = arr.length;

        /**
         * The time complexity of the given code is O(n^3), where "n" is the length of the input array arr.
         *-
         * Here's the breakdown of the time complexity:
         *-
         * The outermost loop runs from i = 0 to i = n-1, where n is the length of the array. This loop has a time complexity of O(n) because it iterates through all elements of the array.
         *-
         * The second loop runs from j = i to j = n-1 for each value of i in the outer loop. This means that for each iteration of the outer loop, the inner loop runs from j = i to j = n-1. The worst-case scenario is when i is close to n-1, making the inner loop run for nearly the entire length of the array. Therefore, the inner loop also has a time complexity of O(n).
         *-
         * Inside the innermost loop, you are creating a subarray and adding elements to it. The innermost loop runs from k = i to k = j, which represents a subarray of size (j - i + 1). Since this loop is nested inside the second loop, the total number of times it runs is proportional to the sum of all possible subarray sizes, which is O(n^2) in the worst case.
         *-
         * Overall, the time complexity is O(n) * O(n) * O(n^2), which simplifies to O(n^4). However, because the innermost loop's iteration count depends on i and j, the actual number of iterations may be slightly less than n^4, but it is still considered to be O(n^3) in big O notation because it grows significantly with the input size.
         */

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                //start = i;
                //end = j;
                List<Integer> subArray = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    subArray.add(arr[k]);
                }
                System.out.println(subArray);
            }

        }


    }
}
