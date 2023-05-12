package CodingInterviewPatterns.Interesting;

import java.util.Stack;

public class ReverseWordsInString {
    public String reverseWords(String s) {
        s = s.trim(); //trim

        s = s.replaceAll("\\s+", " "); //remove whitespaces

        String[] arr = s.split(" "); //split

        Stack<String> stack = new Stack<>();

        for (String str : arr) {
            stack.push(str);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);

    }
}
