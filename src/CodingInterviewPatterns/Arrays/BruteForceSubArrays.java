package CodingInterviewPatterns.Arrays;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSubArrays {
    //Therefore, the time complexity of the brute force approach is O(n^2), where n is the length of the input array.
    public static List<List<Integer>> getSubArrays(int[] arr) {
        List<List<Integer>> subArrays = new ArrayList<>();
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                List<Integer> subarray = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    subarray.add(arr[k]);
                }
                subArrays.add(subarray);
            }

        }

        return subArrays;
    }

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
