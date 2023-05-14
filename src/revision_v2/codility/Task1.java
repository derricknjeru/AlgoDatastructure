package revision_v2.codility;

public class Task1 {
    public static void main(String[] args) {
        /**
         * Write a function solution that, given an integer N, returns the smallest number with the same number of digits. You can assume N is between 1 and 10° (a billion).
         *
         * For example, given N = 125, the function should return 100. Given N = 10, the function should return 10. Given N = 1, the function should return 0.
         *
         * Copyright 2009-2020 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
         */
        //int N = 125;
        //int N = 999999999;
        //int N = 10;
        int N = 1;
        System.out.println(solution2(N));

        //Interesting question
        //https://www.geeksforgeeks.org/find-smallest-possible-number-from-a-given-large-number-with-same-count-of-digits/
    }

    public static int solution(int N) {
        int smallestInt = 0;

        int length = String.valueOf(N).length();

        if (length <= 1) {
            return smallestInt;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(1);

        for (int i = 0; i < length - 1; i++) sb.append("0");

        return Integer.parseInt(sb.toString());
    }

    public static int solution2(int N) {
        int smallestInt = 0;

        int length = String.valueOf(N).length();

        if (length <= 1) {
            return smallestInt;
        }

        return (int) Math.pow(10, length - 1);
    }
}
