package CodingInterviewPatterns.HashTable;

public class ShuffleString {
    //https://leetcode.com/problems/shuffle-string/
    //cycle sort
    public String restoreString(String s, int[] indices) {
        char ch[] = new char[s.length()];
        for (int i = 0; i < ch.length; i++) {
            ch[indices[i]] = s.charAt(i);
        }
        return new String(ch);
    }

}
