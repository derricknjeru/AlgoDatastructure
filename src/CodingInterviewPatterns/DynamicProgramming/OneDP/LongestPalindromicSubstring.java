package CodingInterviewPatterns.DynamicProgramming.OneDP;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        //https://leetcode.com/problems/longest-palindromic-substring/editorial/
        //https://leetcode.com/problems/longest-palindromic-substring/description/
        /**
         * Time complexity: O(n^2)
         *
         * There are 2n−1=O(n) centers. For each center, we call expand, which costs up to O(n)
         *
         * Although the time complexity is the same as in the DP approach, the average/practical runtime of the algorithm is much faster.
         * This is because most centers will not produce long palindromes, so most of the O(n) calls to expand will cost far less than n iterations.
         *
         * The worst case scenario is when every character in the string is the same.
         *
         * Space complexity: O(1)
         *
         * We don't use any extra space other than a few integers. This is a big improvement on the DP approach.
         */

    }

    class Solution {
        public String longestPalindrome(String s) {
            String longestPalindrome = "";
            int longestLength = 0;

            for (int i = 0; i < s.length(); i++) {
                // Find odd length palindrome
                String oddPalindrome = expandAroundCenter(s, i, i);
                if (oddPalindrome.length() > longestLength) {
                    longestPalindrome = oddPalindrome;
                    longestLength = oddPalindrome.length();
                }

                // Find even length palindrome
                String evenPalindrome = expandAroundCenter(s, i, i + 1);
                if (evenPalindrome.length() > longestLength) {
                    longestPalindrome = evenPalindrome;
                    longestLength = evenPalindrome.length();
                }
            }

            return longestPalindrome;
        }

        private String expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            // Since the above loop will move one step beyond the valid palindrome,
            // we need to use substring with left+1 and right-1 to get the actual palindrome.
            return s.substring(left + 1, right);
        }

    }

    //brute force.---> Not accepted by leetcode n^3 --the 2 for loops and the while loop
    class Solution2 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }

            int maxLength = 0;
            String longestPalindrome = "";

            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    String substring = s.substring(i, j + 1);

                    if (isPalindrome(substring) && substring.length() > maxLength) {
                        maxLength = substring.length();
                        longestPalindrome = substring;
                    }
                }
            }

            return longestPalindrome;
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
