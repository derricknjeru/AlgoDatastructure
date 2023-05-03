package Two_pointer;

public class ValidPalindrome {
    public static void main(String[] args) {
        String x = "A man, a plan, a canal: Panama";
        //String s = "race a car";
        x.toLowerCase();

        System.out.println(isPalindrome(x));


    }

    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * Note.md: For the purpose of this problem, we define empty string as valid palindrome.
     */
    public static boolean isPalindrome2(String s) {
        int j = s.length() - 1;
        int i = 0;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)) || i < j && s.charAt(i) == ' ') {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)) || i < j && s.charAt(j) == ' ') {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }


        return true;
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }

        //we convert it to lower cas
        s = s.toLowerCase();

        char[] arr = s.toCharArray();

        int i = 0; //start pointer

        int j = arr.length - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(arr[i])) {
                i++;
            }

            while (i < j && !Character.isLetterOrDigit(arr[j])) {
                j--;
            }

            if (arr[i++] != arr[j--]) return false;

        }

        return true;

    }


    //Java script

    /**
     * function(s) {
     *     var res = s.toLowerCase();
     *     var i =0;
     *     var j = res.length-1;
     *
     *     console.log("J "+j)
     *
     *     while(i<j){
     *         while(i<j && !isAlphaNumeric(res.charAt(i))){
     *             i++;
     *         }
     *         while(i<j && !isAlphaNumeric(res.charAt(j))){
     *             j--;
     *         }
     *
     *         if(res[i]!=res[j]){
     *            return false;
     *         }
     *
     *         i++;
     *         j--;
     *
     *     }
     *
     *     return true;
     * };
     *
     * function isAlphaNumeric(code) {
     *      return (/^[a-zA-Z0-9]+$/).test(code)
     * };
     */


}
