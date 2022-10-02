package arrays_and_strings;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {

    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     * <p>
     * Note that you cannot sell a stock before you buy one.
     * <p>
     * Example 1:
     * <p>
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     * Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     * <p>
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */
    //https://www.youtube.com/watch?v=mj7N8pLCJ6w&t=2s&ab_channel=KevinNaughtonJr.
    public int maxProfit(int[] prices) {

        int min = Integer.MAX_VALUE; //create maximum space for whatever no we will asign.

        int maxprofit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                maxprofit = Math.max(maxprofit, prices[i] - min);
            }

        }

        return maxprofit;

    }

    public int maxProfit2(int[] prices) {

        int minPrice = prices[0];

        int maxProfit = 0;


        for (int i = 1; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max((prices[i] - minPrice), maxProfit);
            }
        }

        return maxProfit;

    }
}
