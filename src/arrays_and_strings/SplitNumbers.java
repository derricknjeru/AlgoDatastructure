package arrays_and_strings;

import java.util.Random;

public class SplitNumbers {
    public static void main(String[] args) {
        SplitNumbers sb = new SplitNumbers();
        //int N = 15;
        //int N = 1557;
        //int N =12356;
        int N = 5000;
        sb.splitNumbers(N);
    }

    /**
     * Recursive function that calls itself
     *
     * @param N represent the number
     */
    private void splitNumbers(int N) {
        //Randomly generate a number that is smaller than the original number
        int rNo = getAnInt(N);
        //Subtract the random number from the original number inorder to get the second number
        int A = N - rNo;

        if (!String.valueOf(A).contains("5") && !String.valueOf(rNo).contains("5")) {
            System.out.printf("%d , %d \n", A, rNo);
        } else {
            //recursively call the method whenever any of the numbers has 5
            splitNumbers(N);
        }

    }

    /**
     * This function generate a unique random number
     * exclusive of N but inclusive of 1;
     *
     * @param N no we are splitting.
     * @return a random number that is less than N
     */
    private int getAnInt(int N) {
        Random r = new Random();
        int low = 1;
        return r.nextInt(N - low) + low;
    }

}
