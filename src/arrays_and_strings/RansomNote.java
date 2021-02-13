package arrays_and_strings;

import java.util.*;

public class RansomNote {
    public static void main(String[] args) {

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
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        // better approach without using hash
        int[] count = new int[26];
        for (char ch : ransomNote.toCharArray())
            count[ch - 'a']++;
        for (char ch : magazine.toCharArray()) {
            count[ch - 'a']--;
        }
        for (int c : count)
            if (c > 0)
                return false;
        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        char m[] = magazine.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char a : m) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int count = map.getOrDefault(ransomNote.charAt(i), 0);
            System.out.println(count);
            char c = ransomNote.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            } else if (count == 0) {
                return false;
            } else {
                map.put(c, count - 1);
            }
        }

        return true;
    }

}
