package revision.strings_and_arrays;

public class ReverseWordsInStringII {

    //Reverse words in a given string
    //https://practice.geeksforgeeks.org/problems/reverse-words-in-a-given-string5459/1#
    //https://www.geeksforgeeks.org/reverse-words-in-a-given-string/

    /**
     * Given a String S, reverse the string without reversing its individual words. Words are separated by dots.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * S = i.like.this.program.very.much
     * Output: much.very.program.this.like.i
     * Explanation: After reversing the whole
     * string(not individual words), the input
     * string becomes
     * much.very.program.this.like.i
     * Example 2:
     * <p>
     * Input:
     * S = pqr.mno
     * Output: mno.pqr
     * Explanation: After reversing the whole
     * string , the input string becomes
     * mno.pqr
     * <p>
     * Your Task:
     * You dont need to read input or print anything.
     * Complete the function reverseWords() which takes string S as input parameter and returns a string containing the words in reversed order. Each word in the returning string should also be separated by '.'
     * <p>
     * <p>
     * Expected Time Complexity: O(|S|)
     * Expected Auxiliary Space: O(|S|)
     * <p>
     * <p>
     * Constraints:
     * 1 <= |S| <= 2000
     */

    public static void main(String[] args) {

    }

    public String reverseWords(String s) {

        String[] arr = s.split(" ");

        int i = 0;
        int j = arr.length - 1;

        StringBuilder sb = new StringBuilder();

        while (i <= j) {
            sb.append(reverse(arr[i++]));
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);

    }

    private String reverse(String word) {
        //return new StringBuilder(word).reverse().toString();
        char[] arr = word.toCharArray();

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            char c = arr[i];
            arr[i++] = arr[j];
            arr[j--] = c;
        }

        return new String(arr);
    }

    private String reverse1(String word) {
        return new StringBuilder(word).reverse().toString();
    }
}
