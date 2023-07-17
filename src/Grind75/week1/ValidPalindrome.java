package Grind75.week1;

public class ValidPalindrome {
    //https://leetcode.com/problems/valid-palindrome/
    public static void main(String[] args) {

        Solution solution = new Solution();
        String s = "A man, a plan, a canal: Panama"; //true
        String s1 = "race a car"; // false
        String s2 = " "; //true
        System.out.println(solution.isPalindrome(s));
        System.out.println(solution.isPalindrome(s1));
        System.out.println(solution.isPalindrome(s2));

    }

    //This solution has a time complexity of O(n) because the map allows constant-time lookups.
    static class Solution {
        public boolean isPalindrome(String s) {
            int i = 0;
            int j = s.length() - 1;

            while (i < j) {
                while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
                while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;

                if (Character.toLowerCase(s.charAt(i++))
                        != Character.toLowerCase(s.charAt(j--))) return false;
            }

            return true;
        }
    }
}
