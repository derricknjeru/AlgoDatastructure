package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class PalindromicSubstrings {
    //https://leetcode.com/problems/palindromic-substrings/description/
    //This approach has a time complexity of O(n^2), where n is the length of the input string.
    class Solution {
        public int countSubstrings(String s) {
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                // Expand around center for odd-length palindromes
                count += expandAroundCenter(s, i, i);

                // Expand around center for even-length palindromes
                count += expandAroundCenter(s, i, i + 1);
            }

            return count;
        }

        private int expandAroundCenter(String s, int left, int right) {
            int count = 0;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }

            return count;
        }
    }

    class Solution2 {
        public int countSubstrings(String s) {
            int count = 0;
            boolean[][] dp = new boolean[s.length()][s.length()];

            // Single characters are palindromes
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
                count++;
            }

            // Palindromes of length 2
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    count++;
                }
            }

            // Palindromes of length >= 3
            for (int length = 3; length <= s.length(); length++) {
                for (int start = 0; start <= s.length() - length; start++) {
                    int end = start + length - 1;
                    if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1]) {
                        dp[start][end] = true;
                        count++;
                    }
                }
            }

            return count;
        }
    }

    //brute force
    class Solution3 {
        public int countSubstrings(String s) {
            int count = 0;

            if (s == null) {
                return count;
            }
            int n = s.length();
            if (n == 1) return 1;

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    String substring = s.substring(i, j + 1);
                    if (isPalindrome(substring)) count++;
                }
            }

            return count;
        }

        private boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }
    }
}
