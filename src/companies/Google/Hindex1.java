package companies.Google;

import java.util.Arrays;

public class Hindex1 {
    //https://leetcode.com/discuss/interview-question/1892555/google-phone-interview
    //https://www.youtube.com/watch?v=CjKJDloMnwE&ab_channel=Techdose
    //https://www.youtube.com/watch?v=wjOjCfkv1mg&ab_channel=Techdose
    class Solution {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);

            int n = citations.length;
            if (n == 0) return 0;
            if (citations[0] >= n) return n;
            int high = n - 1;
            int low = 0;
            int mid;
            int candidate = 0;

            while (low <= high) {
                mid = low + (high - low) / 2;
                if (citations[mid] >= n - mid) {
                    candidate = n - mid;
                    high = mid - 1;
                } else low = mid + 1;
            }
            return candidate;
        }
    }
}
