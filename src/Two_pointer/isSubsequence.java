package Two_pointer;

public class isSubsequence {
    //https://leetcode.com/problems/is-subsequence/
    //https://www.youtube.com/watch?v=Vch3pFgmKD8&ab_channel=CodingDecoded
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();

        int i = 0, j = 0;

        while (j < m && i < n) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            i++;
        }

        return j == m;

    }
}
