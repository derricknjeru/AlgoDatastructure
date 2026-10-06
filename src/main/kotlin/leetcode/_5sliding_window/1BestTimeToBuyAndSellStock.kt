package leetcode._5sliding_window

fun main() {

}

// https://www.youtube.com/watch?v=1pkOgXD63yU
fun maxProfit(prices: IntArray): Int {
    // Space Complexity: O(1) - We only store two primitive variables (minPrice and maxProfit),
    // requiring a constant amount of extra memory regardless of the input size.
    if (prices.isEmpty()) return 0

    var minPrice = prices[0]
    var maxProfit = 0

    // Time Complexity: O(n) - We iterate through the 'prices' array of size n exactly once.
    // The loop runs n - 1 times, performing constant-time O(1) operations (comparisons and arithmetic)
    // at each iteration.
    for (i in 1 until prices.size) {
        // Update maxProfit if selling today yields a higher profit than previous maximum
        maxProfit = maxOf(maxProfit, prices[i] - minPrice)

        // Update minPrice if we find a lower buying price moving forward
        minPrice = minOf(minPrice, prices[i])
    }

    return maxProfit
}


fun maxProfit2(prices: IntArray): Int {
    // Space Complexity: O(1) - Only two primitive tracking variables (minPrice and maxProfit)
    // are maintained in memory, requiring constant auxiliary space regardless of input size.
    var minPrice = Int.MAX_VALUE
    var maxProfit = 0

    // Time Complexity: O(n) - We iterate through all elements of the 'prices' collection of length n once.
    // Each element evaluation involves constant-time O(1) comparisons and arithmetic updates.
    for (price in prices) {
        // Update the running minimum price seen so far
        minPrice = minOf(minPrice, price)

        // Update the maximum profit possible using the current price minus the lowest price seen so far
        maxProfit = maxOf(maxProfit, price - minPrice)
    }

    return maxProfit
}
