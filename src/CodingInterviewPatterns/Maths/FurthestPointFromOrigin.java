package CodingInterviewPatterns.Maths;

public class FurthestPointFromOrigin {
    //https://leetcode.com/problems/furthest-point-from-origin/description/
    public static void main(String[] args) {
        Solution sln = new Solution();
        // Example 1
        String moves1 = "L_RL__R";
        System.out.println(sln.furthestDistanceFromOrigin(moves1)); // Output: 3

        // Example 2
        String moves2 = "_R__LL_";
        System.out.println(sln.furthestDistanceFromOrigin(moves2)); // Output: 5

        // Example 3
        String moves3 = "_______";
        System.out.println(sln.furthestDistanceFromOrigin(moves3)); // Output: 7

        // Additional example
        String moves4 = "R_";
        System.out.println(sln.furthestDistanceFromOrigin(moves4)); // Output: 2
    }

    static class Solution {
        public int furthestDistanceFromOrigin(String moves) {
            int countL = 0;
            int countR = 0;

            for (int i = 0; i < moves.length(); i++) {
                char c = moves.charAt(i);
                if (c == 'L') {
                    countL++;
                } else if (c == 'R') {
                    countR++;
                }
            }

            int countS = moves.length() - countL - countR;
            return Math.abs(countL - countR) + countS;
        }
    }

    static class Solution2 {
        public int furthestDistanceFromOrigin(String moves) {
            int countL = 0;
            int countR = 0;
            int countS = 0;

            for (int i = 0; i < moves.length(); i++) {
                char c = moves.charAt(i);
                if (c == 'L') countL++;
                else if (c == 'R') countR++;
                else countS++;
            }

            return countL > countR ? countL + countS - countR : countR + countS - countL;
        }
    }
}
