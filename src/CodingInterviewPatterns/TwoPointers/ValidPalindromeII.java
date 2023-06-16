package CodingInterviewPatterns.TwoPointers;

public class ValidPalindromeII {
    //https://leetcode.com/problems/valid-palindrome-ii/description/

    /**
     * The time complexity of the validPalindrome method in the provided code is O(n),
     * where n is the length of the input string s.
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int n = s.length();
        if (n == 1) return true;

        int i = 0;
        int j = n - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (validPalindrome(s, i + 1, j)) {
                    return true;
                } else if (validPalindrome(s, i, j - 1)) {
                    return true;
                } else {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;

    }

    private boolean validPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

}
