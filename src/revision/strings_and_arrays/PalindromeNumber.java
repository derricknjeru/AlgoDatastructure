package revision.strings_and_arrays;

public class PalindromeNumber {
    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {

        String s =String.valueOf(x);

        int i = 0;
        int j = s.length()-1;

        while(i<j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else{
                return false;
            }

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
