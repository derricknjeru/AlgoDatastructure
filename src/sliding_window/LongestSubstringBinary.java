package sliding_window;

public class LongestSubstringBinary {
    public static void main(String[] args) {
        /**
         * Example 2: You are given a binary substring s (a string containing only "0" and "1"). '
         * An operation involves flipping a "0" into a "1".
         * What is the length of the longest substring containing only "1" after performing at most one operation?
         *
         * For example, given s = "1101100111", the answer is 5. If you perform the operation at index 2,
         * the string becomes 1111100111.
         */
    }


    public int findLength(String s) {
        int left = 0;
        int curr = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '0') {
                curr++;
            }

            while (curr > 1) {
                if (s.charAt(left) == '0') {
                    curr--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }


}
