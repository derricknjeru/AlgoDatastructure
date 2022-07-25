package revision_v2.Interesting;

import java.util.PriorityQueue;

public class SingleRowKeyBoard {
    /**
     * There is a special keyboard with all keys in a single row.
     * <p>
     * You have given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25),
     * initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character.
     * The time taken to move your finger from index i to index j is |i – j|.
     * <p>
     * You want to type a string word. Write a program to calculate how much time it takes to type it with one finger.
     * <p>
     * Example
     * Single Row Keyboard
     * <p>
     * Input :- Keyboard = "abcdefghijklmnopqrstuvwxyz", Word = "cba"
     * Output :- 4
     * Input :- Keyboard = "pqrstuvwxyzabcdefghijklmno", Word = "hello"
     * Output :- 31
     */

    public static void main(String[] args) {

        //String Keyboard = "abcdefghijklmnopqrstuvwxyz", Word = "cba";
        String Keyboard = "pqrstuvwxyzabcdefghijklmno", Word = "hello";


        System.out.println(calculateTime(Keyboard, Word));
    }

    public static int calculateTime(String Keyboard, String Word) {
        int[] map = new int[26];

        for (int i = 0; i < Keyboard.length(); i++) {
            char c = Keyboard.charAt(i);
            map[c - 'a'] = i;
        }
        char prev = Word.charAt(0);
        int res = Math.abs(-map[prev - 'a']);

        for (int i = 1; i < Word.length(); i++) {
            char c = Word.charAt(i);
            res += Math.abs(map[prev - 'a'] - map[c - 'a']);
            prev = c;
        }

        return res;

    }


}
