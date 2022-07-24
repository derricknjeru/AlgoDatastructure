package arrays_and_strings;

public class RedistributeCharacters {
    public boolean makeEqual(String[] words) {
        int[] map = new int[26];

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                map[c - 'a']++;
            }
        }

        int len = words.length;

        for (int val : map) {
            if (val % len != 0) return false;
        }

        return true;


    }
}
