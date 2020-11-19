package revision.strings_and_arrays;

import java.util.ArrayList;

public class ReverseWordsInStringIII {
    public static void main(String[] args) {

    }

    //Approach #0 Simple Solution[Accepted] using split and swapping character
    public String reverseWords(String s) {

        String[] arr = s.split(" ");

        StringBuilder sb = new StringBuilder();

        for (String value : arr) {

            char[] charArray = value.toCharArray();

            int start = 0;

            int end = charArray.length - 1;

            while (start < end) {
                char tmp = charArray[start];
                charArray[start++] = charArray[end];
                charArray[end--] = tmp;
            }

            sb.append(new String(charArray)).append(" ");


        }
        return sb.toString().trim();

    }

    /**
     * Approach #1 Simple Solution[Accepted]
     * The first method is really simple. We simply split up the given string based on whitespaces and put the individual words in an array of strings. Then, we reverse each individual string and concatenate the result. We return the result after removing the additional whitespaces at the end.
     */

    public String reverseWords1(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
    }

    /**
     * Approach #2 Without using pre-defined split and reverse function [Accepted]
     * Algorithm
     * <p>
     * We can create our own split and reverse function. Split function splits the string based on the delimiter " "(space) and returns the array of words. Reverse function returns the string after reversing the characters.
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        String words[] = split(s);
        StringBuilder res = new StringBuilder();
        for (String word : words)
            res.append(reverse(word) + " ");
        return res.toString().trim();
    }

    public String[] split(String s) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(word.toString());
                word = new StringBuilder();
            } else
                word.append(s.charAt(i));
        }
        words.add(word.toString());
        return words.toArray(new String[0]);
    }

    public String[] split2(String s) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        char[] a = s.toCharArray();

        for (char c : a) {
            if (c == ' ') {
                words.add(word.toString());
                //reset StringBuilder;
                word = new StringBuilder();
            } else {
                word.append(c);
            }
        }
        words.add(word.toString());
        return words.toArray(new String[0]);
    }

    public String reverse(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            res.insert(0, s.charAt(i));
        return res.toString();
    }


    // Approach #3 Using StringBuilder and reverse method [Accepted]
    public String reverseWords3(String input) {
        final StringBuilder result = new StringBuilder();
        final StringBuilder word = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                word.append(input.charAt(i));
            } else {
                result.append(word.reverse());
                result.append(" ");
                word.setLength(0);
            }
        }
        result.append(word.reverse());
        return result.toString();
    }
}


