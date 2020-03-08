public class ValidPalindrome {
    public static void main(String[] args) {
        String x = "A man, a plan, a canal: Panama";
        //String s = "race a car";

        System.out.println(isPalindrome(x));


    }

    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     */
    public static boolean isPalindrome(String s) {
        int j = s.length() - 1;
        int i = 0;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)) || i < j && s.charAt(i) == ' ') {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)) || i < j && s.charAt(i) == ' ') {
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


}
