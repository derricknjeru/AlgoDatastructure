package Two_pointer;

import java.util.HashMap;

public class NumberOfMatchingSubsequences {

    // https://leetcode.com/problems/number-of-matching-subsequences/

    public int numMatchingSubseq(String s, String[] words) {

        int matchingSub = 0;

        HashMap<String, Integer> frequencyOfWords = new HashMap<>();
        for (String word : words)
            frequencyOfWords.put(word, frequencyOfWords.getOrDefault(word, 0) + 1);

        for (String word : frequencyOfWords.keySet()) {
            if (isSubsequence(word, s)) {
                matchingSub += frequencyOfWords.get(word);
            }
        }

        return matchingSub;

    }

    private boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }
}
