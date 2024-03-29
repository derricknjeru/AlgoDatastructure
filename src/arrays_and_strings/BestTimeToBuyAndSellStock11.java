package arrays_and_strings;

public class BestTimeToBuyAndSellStock11 {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * Note.md.md.md.md: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     * <p>
     * Example 1:
     * <p>
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     * Example 2:
     * <p>
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (p\rice = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note.md.md.md.md that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     * engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     * <p>
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        BestTimeToBuyAndSellStock11 b = new BestTimeToBuyAndSellStock11();
        System.out.printf("The maximum profit %d", b.maxProfit(arr));
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    //https://www.youtube.com/watch?v=blUwDD6JYaE&list=PLi9RQVmJD2fYMiu6JOL3PiEVh_Is6jAin&index=2&ab_channel=KevinNaughtonJr.

    /**
     * Complexity Analysis
     * Time complexity: O(n). Only a single pass is needed.
     * <p>
     * Space complexity: O(1). Only two variables are used.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                int diff = prices[i + 1] - prices[i];
                maxProfit += diff;
            }

        }
        return maxProfit;
    }
}
