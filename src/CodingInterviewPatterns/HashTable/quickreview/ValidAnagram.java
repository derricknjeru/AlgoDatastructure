package CodingInterviewPatterns.HashTable.quickreview;

import java.util.Arrays;
import java.util.HashMap;

//
public class ValidAnagram {
    public static void main(String[] args) {
        // String s = "anagram", t = "nagaram";
        String s = "rat", t = "car";
        System.out.println(isAnagram(s, t));

    }

    /**
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     * <p>
     * Example 1:
     * <p>
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * Example 2:
     * <p>
     * Input: s = "rat", t = "car"
     * Output: false
     * Note.md.md.md.md:
     * You may assume the string contains only lowercase alphabets.
     * <p>
     * Follow up:
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     */
    static public boolean isAnagram(String s, String t) {
        //can handle unicode
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();

        //Find the number of occurrences
        for (int i = 0; i < s.length(); i++) {
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i), 0) + 1);
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (!mapS.get(s.charAt(i)).equals(mapT.get(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Complexity analysis
     * <p>
     * Time complexity : O(n). Time complexity is O(n) because accessing the counter table is a constant time operation.
     * <p>
     * Space complexity : O(1). Although we do use extra space, the space complexity is O(1) because the table's size stays constant no matter how large n is.
     * <p>
     * Follow up
     * <p>
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     * <p>
     * Answer
     * <p>
     * Use a hash table instead of a fixed size counter. Imagine allocating a large size array to fit the entire range of unicode characters, which could go up to more than 1 million. A hash table is a more generic solution and could adapt to any range of characters.
     *
     * @param s
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Complexity analysis
     * <p>
     * Time complexity : O(n \log n)O(nlogn). Assume that nn is the length of ss, sorting costs O(n \log n)O(nlogn) and comparing two strings costs O(n)O(n). Sorting time dominates and the overall time complexity is O(n \log n)O(nlogn).
     * <p>
     * Space complexity : O(1)O(1). Space depends on the sorting implementation which, usually, costs O(1)O(1) auxiliary space if heapsort is used. Note.md.md.md.md that in Java, toCharArray() makes a copy of the string so it costs O(n)O(n) extra space, but we ignore this for complexity analysis because:
     * <p>
     * It is a language dependent detail.
     * It depends on how the function is designed. For example, the function parameter types can be changed to char[].
     *
     * @param s
     * @param t
     * @return
     */

    //sorting
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
