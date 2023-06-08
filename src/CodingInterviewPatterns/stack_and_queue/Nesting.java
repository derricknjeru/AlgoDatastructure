package CodingInterviewPatterns.stack_and_queue;

import java.util.ArrayDeque;
import java.util.Deque;

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
