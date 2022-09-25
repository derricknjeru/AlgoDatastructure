package hackerrack;

public class SherlockAndValidString {
    //https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem?isFullScreen=true
    public static String isValid(String s) {
        // Write your code here
        int [] alphabets = new int[26];

        for (int c : s.toCharArray()) {
            alphabets[c-'a']++;
        }

        int freq = alphabets[0];
        int count = 0;
        for (int i = 1; i < alphabets.length; i++) {
            if(alphabets[i] - freq != 0  && alphabets[i] != 0){
                count++;
            }
        }

        return (count>1) ? "NO" : "YES";

    }
}
