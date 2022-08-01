package arrays_and_strings;

public class ReversePrefixOfWord {
    //https://leetcode.com/problems/reverse-prefix-of-word/
    public String reversePrefix(String word, char ch) {
        int i = 0;
        int j = word.indexOf(ch);
        char[] arr = word.toCharArray();

        while (i < j) {
            char c = arr[i];
            arr[i++] = arr[j];
            arr[j--] = c;
        }
        return new String(arr);
    }
}
