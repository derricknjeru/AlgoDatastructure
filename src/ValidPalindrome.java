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
     *
     */
    static boolean isPalindrome(String x) {
        int j = x.length() - 1;
        int i = 0;

        while (i<j) {
            while (!Character.isLetterOrDigit(x.charAt(i))|| x.charAt(i)==' ') {
                i++;
            }
            while (!Character.isLetterOrDigit(x.charAt(j))||x.charAt(i)==' ') {
                j--;
            }

            System.out.println(Character.toLowerCase(x.charAt(i)) + " and " + Character.toLowerCase(x.charAt(j)));

            if (Character.toLowerCase(x.charAt(i)) != Character.toLowerCase(x.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }


        return true;

    }


}
