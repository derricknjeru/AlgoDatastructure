package CodingInterviewPatterns.Recursion;

public class KthSymbolInGrammar {
    //https://www.youtube.com/watch?v=BAsiYnnE3V8
    //https://leetcode.com/explore/learn/card/recursion-i/253/conclusion/1675/
    //https://www.youtube.com/watch?v=tuMWyDsIpEQ

    /**
     * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
     * <p>
     * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
     * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: n = 1, k = 1
     * Output: 0
     * Explanation: row 1: 0
     * Example 2:
     * <p>
     * Input: n = 2, k = 1
     * Output: 0
     * Explanation:
     * row 1: 0
     * row 2: 01
     * Example 3:
     * <p>
     * Input: n = 2, k = 2
     * Output: 1
     * Explanation:
     * row 1: 0
     * row 2: 01
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= n <= 30
     * 1 <= k <= 2n - 1
     *
     * @param n
     * @param k
     * @return
     */

    public int kthGrammar(int n, int k) {
        if (n == 1 && k == 1) {
            return 0;
        }

        int length = (int) Math.pow(2, n - 1);
        int halfLength = length / 2;

        if (k <= halfLength) {
            return kthGrammar(n - 1, k);
        } else {
            int result = kthGrammar(n - 1, k - halfLength);
            return result == 0 ? 1 : 0;
        }
    }


}
