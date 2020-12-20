package arrays_and_strings;

import java.util.*;

public class RansomNote {
    public static void main(String[] args) {

    }

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
