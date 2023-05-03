package HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    //Strings that have 1 to 1 mapping

    /**
     * Given two strings s and t, determine if they are isomorphic.
     * <p>
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     * <p>
     * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "egg", t = "add"
     * Output: true
     * Example 2:
     * <p>
     * Input: s = "foo", t = "bar"
     * Output: false
     * Example 3:
     * <p>
     * Input: s = "paper", t = "title"
     * Output: true
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 5 * 104
     * t.length == s.length
     * s and t consist of any valid ascii character.
     */

    //https://www.youtube.com/watch?v=iZ2eG3ySN68
    public boolean isIsomorphic3(String s, String t) {
        if (s == null || t == null) return false;

        int[] m1 = new int[256]; //extended Aschii

        int[] m2 = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (m1[c1] != m2[c2]) return false;

            m1[c1] = i + 1;
            m2[c2] = i + 1;

        }

        return true;
    }

    /**
     * Complexity Analysis
     *
     * Here N is the length of each string (if the strings are not the same length, then they cannot be isomorphic).
     *
     * Time Complexity: O(N). We process each character in both the strings exactly once to determine if the strings are isomorphic.
     * Space Complexity: O(1) since the size of the ASCII character set is fixed and the keys in our dictionary are all valid ASCII characters according to the problem statement.
     *
     * @param s
     * @param t
     * @return
     */
    //https://www.youtube.com/watch?v=7yF-U1hLEqQ
    //https://leetcode.com/problems/isomorphic-strings/editorial/

    public boolean isIsomorphic(String s, String t) {
        if (t == null || s == null || s.length() != t.length()) return false;

        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if ((mappingDictStoT[c1] != -1 && mappingDictStoT[c1] != c2)
                    || mappingDictTtoS[c2] != -1 && mappingDictTtoS[c2] != c1) {
                return false;
            }

            mappingDictStoT[c1] = c2;
            mappingDictTtoS[c2] = c1;

        }

        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        if (t == null || s == null || s.length() != t.length()) return false;
        return transform(s).equals(transform(t));
    }

    private String transform(String s) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!map.containsKey(c)) {

                map.put(c, i);
            }
            sb.append(map.get(c));
            sb.append("|");
        }

        return sb.toString();
    }


    // 3  ---> 2 ---> 0 ---> -4 --->2
    //                        s
    //                               f
}
