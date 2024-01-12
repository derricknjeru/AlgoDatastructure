package CodingInterviewPatterns.Maths;

public class RobotReturnToOrigin {
    //https://www.youtube.com/watch?v=MDWonrk4iag
    //https://leetcode.com/problems/robot-return-to-origin/description/

    static class Solution {
        public boolean judgeCircle(String moves) {
            int x = 0, y = 0;

            for (char c : moves.toCharArray()) {
                if (c == 'U') y++;
                else if (c == 'D') y--;
                else if (c == 'R') x++;
                else if (c == 'L') x--;
            }

            return x == 0 && y == 0;
        }
    }

    public static void main(String[] args) {
        String move = "UD";
        String move2 = "LL";
        System.out.println(new Solution().judgeCircle(move));
    }
}
