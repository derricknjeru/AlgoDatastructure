package CodingInterviewPatterns.Arrays;

import java.util.Stack;

public class SimplifyPath {
    //https://leetcode.com/problems/simplify-path/description/
    //https://www.youtube.com/watch?v=qYlHrAKJfyA
    //https://www.youtube.com/watch?v=8fM6D3sMIDs
    public static void main(String[] args) {

        Solution solution = new Solution();

        // Example 1
        String path1 = "/home/";
        System.out.println(solution.simplifyPath(path1)); // Output: "/home"

        // Example 2
        String path2 = "/../";
        System.out.println(solution.simplifyPath(path2)); // Output: "/"

        // Example 3
        String path3 = "/home//foo/";
        System.out.println(solution.simplifyPath(path3)); // Output: "/home/foo"
    }

    static class Solution {
        public String simplifyPath(String path) {
            Stack<String> stack = new Stack<>();
            String[] splitResult = path.split("/");

            for (String component : splitResult) {
                if (component.equals("..")) {
                    if (!stack.isEmpty()) stack.pop();
                } else if (!component.equals(".") && !component.isEmpty()) {
                    stack.push(component);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (String res : stack) {
                sb.append("/").append(res);
            }

            return sb.length() > 0 ? sb.toString() : "/";
        }
    }
}
