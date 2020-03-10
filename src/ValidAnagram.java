import java.util.HashMap;

public class ValidAnagram {
    public static void main(String[] args) {
        // String s = "anagram", t = "nagaram";
        String s = "rat", t = "car";
        System.out.println(isAnagram(s, t));
    }

    static public boolean isAnagram(String s, String t) {
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
     * To examine if tt is a rearrangement of ss, we can count occurrences of each letter in the two strings and compare them. Since both ss and tt contain only letters from a-za−z, a simple counter table of size 26 is suffice.
     *
     * Do we need two counter tables for comparison? Actually no, because we could increment the counter for each letter in ss and decrement the counter for each letter in tt, then check if the counter reaches back to zero.
     *
     * Complexity analysis
     *
     * Time complexity : O(n)O(n). Time complexity is O(n)O(n) because accessing the counter table is a constant time operation.
     *
     * Space complexity : O(1)O(1). Although we do use extra space, the space complexity is O(1)O(1) because the table's size stays constant no matter how large nn is.
     */

    public boolean isAnagram2(String s, String t) {
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
}
