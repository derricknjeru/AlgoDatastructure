package revision_v2.codility;

public class Task2 {
    public static void main(String[] args) {
        /**
         * Derrick Njeru, [30.01.21 14:11]
         * Task
         *
         * Java
         *
         * A non-negative integer variable V is given. There are two actions available that modify
         *
         * its value:
         *
         * • if V is odd, subtract 1 from it; • if V is even, divide it by 2.
         *
         * These actions are performed until the value of V becomes 0.
         *
         * For example, if V initially contains value 28, it will become 0 after seven steps:
         *
         * • V contains value 28, which is even: divide by 2 and obtain 14; • V contains value 14, which is even: divide by 2 and obtain 7;
         *
         * • V contains value 7, which is odd: subtract 1 and obtain 6; • V contains value 6, which is even: divide by 2 and obtain 3;
         *
         * V contains value 3, which is odd: subtract 1 and obtain 2;
         *
         * • V contains value 2, which is even: divide by 2 and obtain 1; • V contains value 1, which is odd: subtract 1 and obtain 0.
         *
         * Write a function:
         *
         * class Solution { public int solution (String S); }
         *
         * that, given a zero-indexed string S consisting of N characters containing a binary representation of the initial value of variable V, returns the number of steps after which the value of V will become 0, as described above.
         *
         * For example, given string S = "011100" the function should return 7, because string S represents the number 28 and 28 becomes 0 after seven steps, as explained above.
         *
         * Write an efficient algorithm for the following assumptions:
         *
         * • N is an integer within the range (1..1,000,000);
         *
         * • string S consists only of the characters "0" and/or "1"; • the binary representation is big-endian, i.e. the first character of string S
         *
         * corresponds to the most significant bit; • the binary representation may contain leading zeros.
         *
         * Copyright 2009-2020 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
         */

        String s = "011100";

        System.out.println(solution(s));

    }

    public static int solution(String S) {
        int steps = 0;
        int n;
        try {
            n = Integer.parseInt(S, 2); //binary to int
        } catch (NumberFormatException e) {
            return steps;
        }

        while (n > 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
            steps++;
        }

        return steps;
    }
}
