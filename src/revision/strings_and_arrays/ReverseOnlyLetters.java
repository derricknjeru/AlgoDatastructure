package revision.strings_and_arrays;

import java.util.Arrays;
import java.util.HashMap;

public class ReverseOnlyLetters {
    public static void main(String[] args) {

    }

    /**
     * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: "ab-cd"
     * Output: "dc-ba"
     * Example 2:
     * <p>
     * Input: "a-bC-dEf-ghIj"
     * Output: "j-Ih-gfE-dCba"
     * Example 3:
     * <p>
     * Input: "Test1ng-Leet=code-Q!"
     * Output: "Qedo1ct-eeLg=ntse-T!"
     *
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        if (S.length() == 0) return S;

        S = S.trim();

        int i = 0;
        int j = S.length() - 1;

        char[] a = S.toCharArray();

        while (i < j) {
            while (i < j && !Character.isLetter(S.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetter(S.charAt(j))) {
                j--;
            }

            char temp = S.charAt(j);
            a[j--] = S.charAt(i);
            a[i++] = temp;

        }

        return new String(a); // or  String.valueOf(arr);

    }
}
