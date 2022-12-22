import java.util.Arrays;

public class AngryFlogs {
    public static void main(String[] args) {
        //1,5,5,2,6

        int[] blocks = {1, 5, 5, 2, 6};
        int[] block1 = {2,6,8,5};
        int[] block2 = {1,1};
        System.out.println(solution(block2));
    }

    public static int solution(int[] blocks) {
        // write your code in Java SE 8

        int n = blocks.length;
        int[] jumpRight = new int[n];
        int[] jumpLeft = new int[n];

        for (int i = 1; i < n; i++)
            if (blocks[i] <= blocks[i - 1])
                jumpRight[i] = jumpRight[i - 1] + 1;

        for (int i = n - 2; i >= 0; i--)
            if (blocks[i] <= blocks[i + 1])
                jumpLeft[i] = jumpLeft[i + 1] + 1;

        System.out.println(Arrays.toString(jumpRight));
        System.out.println(Arrays.toString(jumpLeft));

        int maxJump = 0;

        for (int i = 0; i < n; i++)
            if (jumpLeft[i] + jumpRight[i] > maxJump)
                maxJump = jumpLeft[i] + jumpRight[i];

        return maxJump + 1;
    }
}
