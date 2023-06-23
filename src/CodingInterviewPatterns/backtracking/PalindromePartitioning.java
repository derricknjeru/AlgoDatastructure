package CodingInterviewPatterns.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    //https://leetcode.com/problems/palindrome-partitioning/
    //https://chat.openai.com/c/c69578a1-5911-482f-80e0-70d9836f6225
    //https://www.youtube.com/watch?v=3jvWodd7ht0&ab_channel=NeetCode

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        String s1 = "aab";
        List<List<String>> result1 = solution.partition(s1);
        System.out.println(result1);
        // Output: [["a","a","b"],["aa","b"]]

        // Example 2
        String s2 = "a";
        List<List<String>> result2 = solution.partition(s2);
        System.out.println(result2);
        // Output: [["a"]]
    }


    static class Solution {
        /**
         * The time complexity of the algorithm is O(n * 2^n), where n is the length of the input string s.
         * <p>
         * The main contributor to the time complexity is the generation of all possible partitions. Since each character in the input string can either be a part of a palindrome substring or not, we have 2^n possible partitions.
         * <p>
         * For each partition, we check if each substring is a palindrome or not, which takes O(n) time in the worst case. Therefore, the overall time complexity is O(n * 2^n).
         * <p>
         * It's worth noting that the isPalindrome function also contributes to the time complexity. However, since the length of each substring is at most n, the time complexity of isPalindrome remains within O(n) for each substring.
         * <p>
         * In practice, the actual number of partitions and the length of each substring will vary depending on the input string. However, the worst-case time complexity gives us an upper bound on the algorithm's performance.
         *
         * @param s
         * @return
         */
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            backtrack(new ArrayList<>(), 0, s, result);
            return result;
        }

        private void backtrack(List<String> partition, int start, String s, List<List<String>> result) {
            if (start == s.length()) {
                result.add(new ArrayList<>(partition));
                return;
            }

            for (int i = start; i < s.length(); i++) {
                String substring = s.substring(start, i + 1);
                if (isPalindrome(substring)) {
                    partition.add(substring);
                    backtrack(partition, i + 1, s, result);
                    partition.remove(partition.size() - 1);
                }
            }
        }

        private boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right))
                    return false;
                left++;
                right--;
            }

            return true;
        }
    }
}
