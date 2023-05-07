package arrays_and_strings;

import java.util.Scanner;

public class LinearSearch {
    /**
     * Given an array of N elements and a integer K. Your task is to return the position of first occurrence of K in the given array.
     * Note.md.md: Position of first element is considered as 1.
     * <p>
     * Input:
     * First line of input contains T denoting the number of test cases. For each testcase there will be two space separated integer N and K denoting the size of array and the value of K respectively. The next line contains the N space separated integers denoting the elements of array.
     * <p>
     * Output:
     * For each test case, print the index of first occurrence of given number K. Print -1 if the number is not found in array.
     * <p>
     * Constraints:
     * 1 <= T <= 100
     * 1 <= N <= 10 power 6
     * 1 <= K <= 10 power 6
     * 1 <= A[i] <= 10 power 6
     * <p>
     * Example:
     * Input :
     * 2
     * 5 16
     * 9 7 2 16 4
     * 7 98
     * 1 22 57 47 34 18 66
     * <p>
     * Output :
     * 4
     * -1
     * <p>
     * ** For More Input/Output Examples Use 'Expected Output' option **
     */
    public static void main(String[] args) {
        int testCases;
        Scanner s = new Scanner(System.in);
        testCases = s.nextInt();
        int i = 0;
        while (i < testCases) {
            int size = s.nextInt();
            int number = s.nextInt();
            int[] arr = new int[size];
            int y = 0;
            while (y < size) {
                arr[y] = s.nextInt();
                y++;
            }
            int index = linearSearch(arr, number, size);
            if (index == -1) {
                System.out.println(index);
            } else {
                System.out.println(index + 1);
            }
            i++;
        }

    }

    /**
     * It sequentially checks each element of the list until a match is found or the whole list has been searched.
     */
    static int linearSearch(int[] arr, int x, int size) {
        int i;
        for (i = 0; i < size; i++)
            if (arr[i] == x)
                return i;
        return -1;
    }


}
