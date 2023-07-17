package Grind75.week1;

public class BestTimeToBuyAndSellStock {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int maxProfit = new Solution().maxProfit(prices);
        System.out.println("Maximum profit: " + maxProfit);

    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int min = Integer.MAX_VALUE;
            int maxProfit = 0;

            for (int price : prices) {
                if (min > price) {
                    min = price;
                }
                maxProfit = Math.max(maxProfit, price - min);
            }

            return maxProfit;
        }
    }
}
