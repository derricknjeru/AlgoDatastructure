package CodingInterviewPatterns.HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbreviation {
    public static void main(String[] args) {
        ValidWordAbbr validWordAbbr = new ValidWordAbbr();
        String input = "Hello, World!";
        String key = validWordAbbr.buildKey(input);
        System.out.println("Input: " + input);
        System.out.println("Key: " + key);
    }
    //https://www.youtube.com/watch?v=FJ8LJy6l_ts

    /**
     * An abbreviation of a word follows the form . Below are some examples of word abbreviations:
     * <p>
     * a) it                      --> it    (no abbreviation)
     * <p>
     * 1
     * b) d|o|g                   --> d1g
     * <p>
     * 1    1  1
     * 1---5----0----5--8
     * c) i|nternationalizatio|n  --> i18n
     * <p>
     * 1
     * 1---5----0
     * d) l|ocalizatio|n          --> l10n
     * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
     * <p>
     * Example:
     * Given dictionary = [ "deer", "door", "cake", "card" ]
     * <p>
     * isUnique("dear") -> false
     * isUnique("cart") -> true
     * isUnique("cane") -> false
     * isUnique("make") -> true
     * The tricky part of this question is the case of duplications in the dictionary. if input dictionary is ["a", "a", "a"], and query is isUnique("a"), this should return true; because "a" is unique. another point require attention is to query the exactly word in the dictionary, this also should return true.
     */

    static public class ValidWordAbbr {
        private Map<String, Set<String>> dict = new HashMap<>();
        ValidWordAbbr(){

        }

        public ValidWordAbbr(String[] dictionary) {
            for (String s : dictionary) {

                String key = buildKey(s);
                if (dict.containsKey(key)) {
                    dict.get(key).add(s);
                } else {
                    Set<String> t = new HashSet<>();
                    t.add(s);
                    dict.put(key, t);
                }
            }
        }

        private String buildKey(String s) {
            if (s.length() <= 2) return s;
            else return s.charAt(0) + Integer.toString(s.length() - 2) + s.charAt(s.length() - 1);
        }

        public boolean isUnique(String word) {
            String key = buildKey(word);
            if (!dict.containsKey(key)) {
                return true;
            } else {
                return dict.get(key).contains(word) && dict.get(key).size() <= 1;
            }
        }
    }


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
}
