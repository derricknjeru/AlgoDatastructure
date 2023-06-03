package CodingInterviewPatterns.Heaps;
// Java code for k largest/ smallest elements in an array

import java.util.Collections;
import java.util.PriorityQueue;

public class KLargestOrSmallestElements {
    /**
     * Write an efficient program for printing k largest elements in an array. Elements in an array can be in any order.
     * For example, if the given array is [1, 23, 12, 9, 30, 2, 50]
     * and you are asked for the largest 3 elements i.e., k = 3 then your program should print 50, 30, and 23.
     */

    // Function to find k largest array element
    static void kLargest(int a[], int n, int k) {
        // Implementation using
        // a Priority Queue
        PriorityQueue<Integer> pq
                = new PriorityQueue<Integer>();

        for (int i = 0; i < n; ++i) {

            // Insert elements into
            // the priority queue
            pq.add(a[i]);

            // If size of the priority
            // queue exceeds k
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Print the k largest element
        while (!pq.isEmpty()) {
            System.out.print(pq.peek() + " ");
            pq.poll();
        }
        System.out.println();
    }

    // Function to find k smallest array element
    static void kSmallest(int a[], int n, int k) {
        // Implementation using
        // a Priority Queue
        PriorityQueue<Integer> pq
                = new PriorityQueue<Integer>(
                Collections.reverseOrder());

        for (int i = 0; i < n; ++i) {

            // Insert elements into
            // the priority queue
            pq.add(a[i]);

            // If size of the priority
            // queue exceeds k
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Print the k largest element
        while (!pq.isEmpty()) {
            System.out.print(pq.peek() + " ");
            pq.poll();
        }
    }

    public int findKthLargest(int[] nums, int k) {

        return getKthSmallestElement(nums, k, 0, nums.length - 1);

    }

    private int getKthSmallestElement(int[] arr, int k, int low, int high) {
        int pivotPoint = getPivotPoint(arr, low, high);
        if (pivotPoint == k - 1) return arr[pivotPoint];
        else if (pivotPoint < k - 1) return getKthSmallestElement(arr, k, pivotPoint + 1, high);
            //4,5,10,11,1,3,19,8,7
            // 4 5 1 3 7 10 11 19 8
        else return getKthSmallestElement(arr, k, low, pivotPoint - 1);
    }

    private int getPivotPoint(int[] arr, int low, int high) {
        int pivotElement = arr[high];
        int pivotPoint = low;

        for (int i = low; i <= high; i++) {
            if (arr[i] > pivotElement) { //change to < in case of small
                int tmp = arr[i];
                arr[i] = arr[pivotPoint];
                arr[pivotPoint] = tmp;
                pivotPoint++;
            }
            //4,5,10,11,1,3,19,8,7
            //4 5 1 11 10 3 19 8 7
            // 4 5 1 3 10 11 19 8 7
            //pivotPoint =5
            //i=5

        }
        int tmp = arr[pivotPoint];
        arr[pivotPoint] = arr[high];
        arr[high] = tmp;

        return pivotPoint;
    }

    // Driver Code
    public static void main(String[] args) {
        int a[] = {11, 3, 2, 1, 15, 5, 4, 45, 88, 96, 50, 45};
        int n = a.length;
        int k = 3;
        System.out.print(k + " largest elements are : ");
        // Function Call
        kLargest(a, n, k);
        System.out.print(k + " smallest elements are : ");
        // Function Call
        kSmallest(a, n, k);
    }
}
