package revision.strings_and_arrays;

public class ValidPalindromeII {
    public static void main(String[] args) {
        //String s = "aba";
        //String s = "abca";
        //String s = "abcbea";
        String s = "abecbea";
        System.out.println(validPalindrome(s));
    }

    /**
     * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     * <p>
     * Example 1:
     * Input: "aba"
     * Output: True
     * Example 2:
     * Input: "abca" or abcbea
     * Output: True
     * Explanation: You could delete the character 'c'.
     * Note:
     * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
     */
    //{@link https://www.geeksforgeeks.org/remove-character-string-make-palindrome/}
    public static boolean validPalindrome(String s) {

        // Initialize low and right
        // by both the ends of the string
        int low = 0, high = s.length() - 1;

        // loop untill low and
        // high cross each other
        while (low < high) {

            // If both characters are equal then
            // move both pointer towards end
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                /**
                 * At mismatch, either remove character pointed by left pointer
                 * or remove character pointed by right pointer.
                 *
                 *
                 *
                 *
                 * We will check both the cases, remember as we have traversed equal number
                 * of steps from both sides, this mid string should also be a palindrome after
                 * removing one character, so we check two substrings, one by removing left
                 * character and one by removing right character and if one of them is palindrome then
                 * we can make complete string palindrome by removing corresponding character,
                 * and if both substrings are not palindrome then it is not possible to make
                 * complete string a palindrome under given constraint.
                 */

                /*
                 * If removing str[low] makes the
                 * whole string palindrome. We basically
                 * check if substring str[low+1..high]
                 * is palindrome or not.
                 */
                if (isPalindrome(s, low + 1, high)) {
                    low++;
                }
                /*
                 * If removing str[high] makes the whole string
                 * palindrome. We basically check if substring
                 * str[low+1..high] is palindrome or not.
                 */
                else if (isPalindrome(s, low, high - 1)) {
                    high--;
                } else {
                    return false;
                }
            }
        }
        return true;

    }

    // Utility method to check if
    // substring from low to high is
    // palindrome or not.
    static boolean isPalindrome(String str,
                                int low, int high) {
        while (low < high) {
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
    //https://www.youtube.com/watch?v=L_74qbyPHXE&ab_channel=KevinNaughtonJr.
    public boolean validPalindrome2(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {

            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }

            i++;
            j--;

        }

        return true;

    }


}
