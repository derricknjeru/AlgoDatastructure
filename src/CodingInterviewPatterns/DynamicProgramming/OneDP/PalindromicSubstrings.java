package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class PalindromicSubstrings {
    //https://leetcode.com/problems/palindromic-substrings/description/
    //This approach has a time complexity of O(n^2), where n is the length of the input string.

    /**
     * The time complexity of the given code is O(n^2), where n is the length of the input string s.
     *
     * The code uses a nested loop to iterate over each character in the string s and expands around each center to find palindromic substrings.
     *
     * The outer loop iterates through each character in the string, which takes O(n) time.
     *
     * For each character, two expansions are performed using the expandAroundCenter method. Each expansion extends the substring to both the left and right as long as the characters match. The expansion loop terminates when the characters no longer match or when the indices go out of bounds.
     *
     * In the worst case, the expansion loop can go up to length n/2 for odd-length palindromes and up to length n/2 for even-length palindromes. Therefore, the total time complexity of expanding around each center is O(n/2 + n/2) = O(n).
     *
     * Since the expansion loop is nested within the outer loop, the total time complexity is O(n * n) = O(n^2).
     *
     * Therefore, the overall time complexity of the countSubstrings method is O(n^2).
     */
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
        /**
         * The time complexity of the updated code is also O(n^3), where n is the length of the input string s.
         *
         * The code uses a nested loop to generate all possible substrings of the string s and checks if each substring is a palindrome using the isPalindrome method.
         *
         * The outer loop iterates over each character in the string, which takes O(n) time.
         *
         * The inner loop iterates from the current index to the end of the string, which also takes O(n) time.
         *
         * Inside the nested loops, the substring method is used to extract each substring, which takes O(k) time, where k is the length of the substring. In the worst case, k can be n, so the substring operation takes O(n) time.
         *
         * Finally, for each generated substring, the isPalindrome method is called to check if it is a palindrome. The isPalindrome method checks if the characters at the left and right indices are the same, and it performs this check for half of the substring length, so it takes O(n/2) = O(n) time.
         *
         * Combining all these steps, the time complexity of the nested loops and palindrome check is O(n * n * n) = O(n^3).
         *
         * Therefore, the overall time complexity of the countSubstrings method is O(n^3).
         * @param s
         * @return
         */
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
