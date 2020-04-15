package Tips;

public class Test {
    public static void main(String[] args) {
        Test pt = new Test();

        String s = "A man, a plan, a canal: Panama";
        //String s = "A mike";

        boolean isPalindrome = pt.isPalindrome(s);

        System.out.println("isPalindrome:"+isPalindrome);
    }

    public boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z]", "").toLowerCase();

        //System.out.println("Only letters: " + s);

        char sToChar[] = s.toCharArray();
        char oppToChar[] = new char[sToChar.length];
        String palindrome = "";

        int sizeOfSToChar = sToChar.length;
        while (sizeOfSToChar > 0) {
            sizeOfSToChar--;

            oppToChar[sizeOfSToChar] = sToChar[sizeOfSToChar];

            palindrome = palindrome + oppToChar[sizeOfSToChar] + "";
        }

        //System.out.println("Palindrome: " + palindrome);

        if (s.equals(palindrome)) {
            return true;
        }

        return false;
    }


}

