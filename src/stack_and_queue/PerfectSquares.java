package stack_and_queue;

import java.util.*;

public class PerfectSquares {
    //Starting from zero, recursivery add current + square of each number between 1 and n and compare against n.
    //![Explanation image](../images/perfectSquaresExplanation2.png)
    //Example //https://www.youtube.com/watch?v=FhRruQz9ktE

    //More examples.
    //https://www.youtube.com/watch?v=wZqW204FC6k
    //https://www.youtube.com/watch?v=HLZLwjzIVGo&t=746s

    public int numSquares(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        q.add(0); //we start at zero
        seen.add(0);

        int level = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {

                int top = q.poll();

                //Starting from zero, recursivery add current + square of each number between 1 and n and compare against n.
                for (int i = 0; i * i <= n; i++) {
                    int curr = top + i * i;

                    if (curr == n) {
                        return level;
                    }

                    if (curr > n) {
                        break;
                    }

                    if (!seen.contains(curr)) {
                        q.add(curr);
                        seen.add(curr);
                    }

                }

            }

            level++;
        }

        return level;
    }

}
