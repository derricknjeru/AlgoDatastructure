package dynamic_programming_and_recursion;

public class ClimbingStairs {
    //https://www.youtube.com/watch?v=AWezsCJDHSk&t=1s
    //https://www.youtube.com/watch?v=Y0lT9Fck7qI&t=1s
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int x = 1;
        int y = 2;

        for (int i = 3; i < n; i++) {
            int temp = y;
            y = y + x;
            x = temp;
        }

        return (x + y);

    }
}
