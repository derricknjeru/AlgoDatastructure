package arrays_and_strings;

import java.util.Arrays;

public class TotalAppealAString {
    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=HCA4SAly3mI&ab_channel=PrakharAgrawal
    }

    public long appealSum(String s) {
        long ans = 0;

        //Array to store last index of character
        int[] ind = new int[26]; //lower letter characters
        Arrays.fill(ind, -1);

        //total substring are equal to i+1 (0 index based)
        int prevSum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int lastPositionCSeen = ind[c - 'a']; //last position this character was seen
            if (lastPositionCSeen == -1) {
                prevSum = i + 1;
            } else {
                prevSum = i - lastPositionCSeen;
            }

            ans += prevSum;

            ind[c - 'a'] = i;
        }

        return ans;

    }
    //Dynamic programming
    //https://www.youtube.com/watch?v=D3JXFdGTizg&t=353s&ab_channel=CodingDecoded
    public long appealSum2(String s) {
        int n = s.length();

        long[] dp = new long[n+1];


        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);


        long ans = 0l;

        for(int i = 0; i < n; i ++) {
            int c = s.charAt(i)-'a';
            dp[i+1] = dp[i]+ i- lastIndex[c];
            ans += dp[i+1];

            lastIndex[c] = i;
        }
        return ans;
    }

}
