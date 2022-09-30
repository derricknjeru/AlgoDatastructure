package hackerrack;

import java.util.*;


class Difference {
    private int[] elements;
    public int maximumDifference;

    public Difference(int[] a) {
        elements = a;
    }

    public void computeDifference() {
        // To store the minimum and the maximum
        // elements from the array
        int minEle = elements[0];
        int maxEle = elements[0];
        for (int i = 1; i < elements.length; i++) {
            minEle = Math.min(minEle, elements[i]);
            maxEle = Math.max(maxEle, elements[i]);
        }

        maximumDifference = maxEle - minEle;
    }

    // Add your code here

} // End of Difference class

public class MaxDifference {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}