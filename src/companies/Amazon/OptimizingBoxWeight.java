package companies.Amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class OptimizingBoxWeight {
    /**
     * An companies.Amazon Fulfillment Associate has a set of items
     * that need to be packed into two boxes. Given an
     * integer array of the item weights (arr) to be packed,
     * divide the item weights into two subsets, A and B, for
     * packing into the associated boxes, while respecting
     * the following conditions:
     * <p>
     * The intersection of A and B is null.
     * • The union A and B is equal to the original array.
     * • The number of elements in subset A is minimal.
     * • The sum of A's weights is greater than the sum of B's
     * weights.
     * <p>
     * Return the subset A in increasing order where the
     * sum of A's weights is greater than the sum of B's
     * weights. If more than one subset A exists, return the
     * one with the maximal total weight.
     * <p>
     * Example
     * n = 5
     * arr = (3, 7, 5, 6, 2)
     * //,5,3,2 === 10 < (7,6)
     * <p>
     * The 2 subsets in arr that satisfy the conditions for A
     * are (5, 7] and [6, 7):
     * • A is minimal (size 2)
     * • Sum(A) = (5 + 7) = 12 > Sum(B) = (2 + 3 + 6) = 11
     * • Sum(A) = (6 + 7) = 13 > Sum(B) = (2 + 3 + 5) = 10
     * • The intersection of A and B is null and their union is
     * equal to arr.
     * • The subset A where the sum of its weight is maximal
     * is [6, 71.
     * Function Description
     * Complete the minimalHeaviestSetA function in the
     * editor below.
     * minimalHeaviestSetA has the following
     * parameter(s):
     * int arr[]: an integer array of the weights of each
     * item in the set
     */

    public static List<Integer> minimalHeaviestSetA(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        long totalWeight = 0;
        for (int a : arr) {
            totalWeight += a;
            maxHeap.add(a);
        }
        long currentWeight = 0;
        while (!maxHeap.isEmpty()) {
            int highWeight = maxHeap.poll();
            currentWeight += highWeight;
            res.add(highWeight);
            if (currentWeight > totalWeight - currentWeight) {
                break;
            }
        }

        return res;
    }

    //2412




}
