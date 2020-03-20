package arrays_and_strings;

import java.util.HashMap;

public class FirstUniqueCharacter {
    public static void main(String[] args) {
        //String s = "leetcode";
        String s="loveleetcode";
        FirstUniqueCharacter u = new FirstUniqueCharacter();
        System.out.println(u.firstUniqChar(s));
    }

    /**
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     * <p>
     * Examples:
     * <p>
     * s = "leetcode"
     * return 0.
     * <p>
     * s = "loveleetcode",
     * return 2.
     * Note: You may assume the string contain only lowercase letters.
     */
    public int firstUniqChar(String s) {

        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
