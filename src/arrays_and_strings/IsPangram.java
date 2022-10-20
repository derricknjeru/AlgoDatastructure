package arrays_and_strings;

import java.util.HashSet;
import java.util.Set;

public class IsPangram {
    public static void main(String[] args) {

    }

    /**
     * Let nn be the length of the input string sentence.
     * <p>
     * Time complexity: O(n)
     * <p>
     * We only need one iteration over sentence.
     * In each step, we add the current letter currChar to seen, which takes constant time.
     * In summary, the overall time complexity is O(1)
     * Space complexity: O(1)
     * <p>
     * We use a hash set seen to store all the unique letters we encounter. There are at most 26 unique lowercase letters, so the space complexity is O(1)
     *
     * @param sentence
     * @return
     */
    public boolean checkIfPangram2(String sentence) {
        Set<Character> letters = new HashSet<>();

        char[] arr = sentence.toCharArray();

        for (char c : arr) {
            letters.add(c);
        }

        return letters.size() == 26;
    }

    /**
     * Let nn be the length of the input string sentence.
     * <p>
     * Time complexity: O(n)
     * <p>
     * We need to iterate over sentence for 26 times.
     * Each iteration takes at most O(n)O(n) time.
     * To sum up, the overall time complexity is O(n)
     * Space complexity: O(1)
     * <p>
     * We just need to find letters one by one, thus the overall space complexity is O(1)O(1).
     *
     * @param sentence
     * @return
     */

    public boolean checkIfPangram(String sentence) {
        // We iterate over 'sentence' for 26 times, one for each letter 'currChar'.
        for (int i = 0; i < 26; ++i) {
            char currChar = (char) ('a' + i);

            // If 'sentence' doesn't contain currChar, it is not a pangram.
            if (sentence.indexOf(currChar) == -1)
                return false;
        }

        // If we manage to find all 26 letters, it is a pangram.
        return true;
    }

    /**
     * Let nn be the length of input string sentence.
     * <p>
     * Time complexity: O(n)
     * <p>
     * Similarly, we just need to traverse over sentence for once.
     * In each step, we calculate the ASCII code of the current letter currChar and update the value at the mapped index in seen. This takes constant time.
     * To sum up, the overall time complexity is O(n)
     * Space complexity: O(1)
     * <p>
     * We use an array of size 26. Thus, the space complexity is O(1)
     *
     * @param sentence
     * @return
     */

    public boolean checkIfPangram3(String sentence) {
        int[] occurrences = new int[26];

        for (char c : sentence.toCharArray()) {
            occurrences[c - 'a'] = 1;
        }

        for (int a : occurrences) {
            if (a == 0) return false;
        }

        return true;
    }


    public boolean checkIfPangram4(String sentence) {
        // Array 'seen' of size 26.
        boolean[] seen = new boolean[26];

        // For every letter 'currChar', we find its ASCII code,
        // and update value at the mapped index as true.
        for (char currChar : sentence.toCharArray())
            seen[currChar - 'a'] = true;

        // Once we finish iterating, check if 'seen' contains false.
        for (boolean status : seen)
            if (!status)
                return false;
        return true;
    }
}
