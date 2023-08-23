package sortingAlgorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    //https://www.youtube.com/watch?v=h8eyY7dIiN4
    //https://leetcode.com/problems/sort-an-array/description/
    public static void main(String[] args) {
        Random rand = new Random();
        int[] numbers = new int[100];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(100);
        }

        System.out.println("Array before sorting:");
        System.out.println(Arrays.toString(numbers));

        quickSort(numbers);

        System.out.println("Array after sorting:");
        System.out.println(Arrays.toString(numbers));
    }

    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * We recursively perform 3 steps:
     * - Bring the pivot to it appropriate position such that left of the pivot is smaller
     * and right is greater
     * - quick sort the left side
     * - quick sort the right part
     *
     * @param array
     * @param lowIndex
     * @param highIndex
     */
    private static void quickSort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        //int pivot = numbers[highIndex]; //choosing the last element as the pivot
        //choosing a random pivot
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        //making sure element on the left are less than elements on the right
        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        //At this point partitioning step is done, All the numbers less than the pivot are in the left
        // and all the numbers greater than the pivot are in the right.

        //- quick sort the left side
        quickSort(array, lowIndex, leftPointer - 1);

        //- quick sort the right side
        quickSort(array, leftPointer + 1, highIndex);

    }

    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {
            //move left pointer until you find a number that is higher or equal to the pivot
            while (leftPointer < rightPointer && array[leftPointer] <= pivot) {
                leftPointer++;
            }

            //move right pointer until you find a number that is lower or equal to the pivot
            while (leftPointer < rightPointer && array[rightPointer] >= pivot) {
                rightPointer--;
            }

            //left pointer == number >= pivot and right pointer <= pivot
            //swap them
            swap(array, leftPointer, rightPointer);

        }

        //swap pivot and the item and left & right pointer which is the same;
        swap(array, leftPointer, highIndex);
        return leftPointer;
    }

    private static void swap(int[] array, int low, int high) {
        int temp = array[high];
        array[high] = array[low];
        array[low] = temp;
    }

}
