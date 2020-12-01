package revision.stack_queue;

import java.util.*;

public class Nesting {
    public static void main(String[] args) {

    }

    public int solution(String S) {
        // write your code in Java SE 8
        if (S.length() == 0) return 1;

        char[] arr = S.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : arr) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return 0;

                stack.pop();

            }
        }
        return stack.isEmpty() ? 1 : 0;

    }
}
