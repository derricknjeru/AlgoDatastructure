package leetcode_30_days_challenge.week1;

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
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
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

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= min) {
                profit = profit + prices[i] - min;
            }
            min = prices[i]; //move the min to current price

        }
        return profit;
    }
}
