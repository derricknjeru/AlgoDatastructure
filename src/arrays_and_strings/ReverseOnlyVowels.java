package arrays_and_strings;

public class ReverseOnlyVowels {
    //https://leetcode.com/problems/reverse-vowels-of-a-string/

    /**
     * Given a string s, reverse only all the vowels in the string and return it.
     * <p>
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "hello"
     * Output: "holle"
     * Example 2:
     * <p>
     * Input: s = "leetcode"
     * Output: "leotcede"
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 3 * 105
     * s consist of printable ASCII characters.
     */

    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length() - 1;

        char[] res = s.toCharArray();

        while (i < j) {
            // Find the leftmost vowel
            while (i < s.length() && !isVowel(res[i])) {
                i++;
            }
            // Find the rightmost vowel
            while (j >= 0 && !isVowel(res[j])) {
                j--;
            }

            // Swap them if start is left of end
            if (i < j) {
                char temp = res[i];
                res[i++] = res[j];
                res[j--] = temp;
            }

        }

        return new String(res);
    }


    // Return true if the character is a vowel (case-insensitive)
    boolean isVowel(char c) {
        String vowels = "aeiou";
        return vowels.indexOf(Character.toLowerCase(c)) != -1;
    }
}
