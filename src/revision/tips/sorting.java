package revision.tips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class sorting {
    public static void main(String[] args) {
        /**
         * Sorting in java https://www.geeksforgeeks.org/sorting-in-java/?ref=lbp}
         */

        //There are two in-built methods to sort in Java.
        // Arrays.sort(arr);
        //Collections.sort(); works for objects Collections like ArrayList and LinkedList.
        //Arrays.sort method and Collection.sort() uses Timsort.

        /**
         * Which order of sorting is done by default?
         * It by default sorts in ascending order.
         * How to sort array or list in descending order?
         * It can be done with the help of Collections.reverseOrder().
         */

        arraysSortFunction();

        collectionsSortFunction();

        arraysSortFunctionDesc();

        collectionsSortFunctionDesc();
    }

    private static void collectionsSortFunctionDesc() {
        // Note that we have Integer here instead of
        // int[] as Collections.reverseOrder doesn't
        // work for primitive types.
        Integer[] arr = {13, 7, 6, 45, 21, 9, 2, 100};

        // Sorts arr[] in descending order
        Arrays.sort(arr, Collections.reverseOrder());

        System.out.printf("\nModified arr[] : %s",
                Arrays.toString(arr));
    }

    private static void arraysSortFunctionDesc() {

        // Create a list of strings
        ArrayList<String> al = new ArrayList<String>();
        al.add("Geeks For Geeks");
        al.add("Friends");
        al.add("Dear");
        al.add("Is");
        al.add("Superb");

        /* Collections.sort method is sorting the
        elements of ArrayList in ascending order. */
        Collections.sort(al, Collections.reverseOrder());

        //al.sort(Collections.reverseOrder());

        // Let us print the sorted list
        System.out.println("\nList after the use of"
                + " Collection.sort() :\n" + al);
    }

    private static void collectionsSortFunction() {
        /* Create a list of strings*/
        ArrayList<String> al = new ArrayList<String>();
        al.add("Geeks For Geeks");
        al.add("Friends");
        al.add("Dear");
        al.add("Is");
        al.add("Superb");

        /* Collections.sort method is sorting the
        elements of ArrayList in ascending order. */
        Collections.sort(al);

        // Let us print the sorted list
        System.out.println("/nList after the use of"
                + " Collection.sort() :\n" + al);
    }

    private static void arraysSortFunction() {
        //String[] arr = {"Dear", "Friends", "Geeks For Geeks", "Is", "Superb"};

        int[] arr = {13, 7, 6, 45, 21, 9, 101, 102};

        Arrays.sort(arr);

        System.out.printf("\nModified arr[] : %s",
                Arrays.toString(arr));
    }
}
