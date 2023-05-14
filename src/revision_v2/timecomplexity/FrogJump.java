package revision_v2.timecomplexity;

public class FrogJump {
    public static void main(String[] args) {
        //https://stackoverflow.com/questions/28178460/increasing-code-performance-of-codility

        /**
         * A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.
         *
         * Count the minimal number of jumps that the small frog must perform to reach its target.
         *
         * Write a function:
         *
         * class Solution { public int solution(int X, int Y, int D); }
         *
         * that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
         *
         * For example, given:
         *
         *   X = 10
         *   Y = 85
         *   D = 30
         * the function should return 3, because the frog will be positioned as follows:
         *
         * after the first jump, at position 10 + 30 = 40
         * after the second jump, at position 10 + 30 + 30 = 70
         * after the third jump, at position 10 + 30 + 30 + 30 = 100
         * Write an efficient algorithm for the following assumptions:
         *
         * X, Y and D are integers within the range [1..1,000,000,000];
         * X ≤ Y.
         * Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
         */
    }

    public int solution(int X, int Y, int D) {
        // write your code in Java SE 8
        /**
         X + nD >= Y
         nD >= Y - X
         n >= (Y - X) / D
         **/
        //The minimum value for n will be the
        //result of rounding up the division of (Y - X) by D.


        return (int) Math.ceil((double) (Y - X) / (double) D);

    }

    public int solution2(int X, int Y, int D) {
        int distanceToJump = Y - X;
        int jumpsRequired = distanceToJump / D;
        if (distanceToJump % D != 0) {
            jumpsRequired++; //only add extra jump if remainder exists
        }
        return jumpsRequired;
    }


}
