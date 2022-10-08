package arrays_and_strings;

public class PalindromeNumber {
    //https://leetcode.com/problems/palindrome-number/
    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }


        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }

    public boolean isPalindrome2(int x) {

        String s = String.valueOf(x);

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
    //java script
    /**
     *  function(x) {
     *     if(x<0) return false;
     *
     *     var s =x.toString();
     *
     *     var i =0;
     *     var j =s.length-1;
     *
     *     while(i<j){
     *         if(s.charAt(i++)!=s.charAt(j--)){
     *            return false;
     *         }
     *     }
     *
     *     return true;
     *
     * };
     */
}
