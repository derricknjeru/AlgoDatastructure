package revision_v2.codility;

import java.util.Stack;
import java.util.regex.Pattern;

public class Task12 {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 0xFFFFF;
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {

        String s = "4 5 6 - 7 +";
        //String s = "13 DUP 4 POP 5 DUP + DUP + -";
        //String s = "5 6 + -";
        //String s = "3 DUP 5 - -";
        //String s = "2147483647";

        Task12 sb = new Task12();

        System.out.println(sb.solution(s));
    }

    public int solution(String S) {

        String[] arr = S.split(" ");

        for (String c : arr) {
            switch (c) {
                case "POP":
                    if (stack.isEmpty()) {
                        return -1;
                    }
                    pop();
                    break;
                case "DUP":
                    if (stack.isEmpty()) {
                        return -1;
                    }
                    dup();
                    break;
                case "+":
                    if (canNotAddOrSub()) {
                        return -1;
                    }
                    add();
                    break;
                case "-":
                    if (canNotAddOrSub()) {
                        return -1;
                    }
                    sub();
                    break;
                default:
                    if (isInt(c)) {
                        push(c);
                    }
                    break;
            }
        }

        return stack.isEmpty() ? -1 : stack.peek();
    }

    private void sub() {
        stack.push(stack.pop() - stack.pop());
    }

    private boolean canNotAddOrSub() {
        return stack.size() < 2;
    }

    private void add() {
        stack.push(stack.pop() + stack.pop());
    }

    private void push(String no) {
        stack.push(innRange(Integer.parseInt(no)));
    }

    private void pop() {
        stack.pop();
    }

    private void dup() {
        int top = stack.peek();
        stack.push(top);
    }

    public boolean isInt(String s) {
        if (s == null) {
            return false;
        }
        return pattern.matcher(s).matches();
    }

    private int innRange(int i) {
        if (i < MIN_VALUE || i > MAX_VALUE) {
            return -1;
        }
        return i;
    }
}
