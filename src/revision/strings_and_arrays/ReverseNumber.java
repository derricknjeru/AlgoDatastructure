package revision.strings_and_arrays;

public class ReverseNumber {
    public static void main(String[] args) {

    }

    /***
     *
     * @param x
     * @return
     */

    //https://leetcode.com/problems/palindrome-number/solution/
    public boolean isPalindrome(int x) {
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



    /**
     * Javascript
     *  function isPalindrome (x) {
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
