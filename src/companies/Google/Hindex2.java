package companies.Google;

public class Hindex2 {
    //https://leetcode.com/discuss/interview-question/1892555/google-phone-interview
    //https://leetcode.com/discuss/interview-question/1892555/google-phone-interview
    //https://www.youtube.com/watch?v=CjKJDloMnwE&ab_channel=Techdose
    //https://www.youtube.com/watch?v=wjOjCfkv1mg&ab_channel=Techdose
    class Solution {
        public int hIndex(int[] citations) {
            int n = citations.length;
            int low = 0;
            int high = n - 1;
            int mid;

            while (low <= high) {
                mid = low + (high - low) / 2;
                if (citations[mid] == (n - mid))
                    return citations[mid];
                else if (citations[mid] > (n - mid))
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return n - low;
        }
    }

}
