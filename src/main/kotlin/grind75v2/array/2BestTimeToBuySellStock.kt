package grind75v2.array

import kotlin.math.max


fun main() {
    val maxProfit = maxProfit(listOf(7, 1, 5, 3, 6, 4).toIntArray())
    println(maxProfit)
}
// space complexity is O(1)
// Time complexity is O(n)
fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0
    var minPrice = prices[0]
    for (i in 1 until prices.size) {
        if (minPrice > prices[i]) minPrice = prices[i]
        maxProfit = max(maxProfit, prices[i] - minPrice)
    }
    return maxProfit
}
// space complexity is O(1)
// Time complexity is O(n)
fun maxProfit2(prices: IntArray): Int {
    var maxProfit = 0
    var minPrice = Int.MAX_VALUE
    for (price in prices) {
        if (minPrice > price) minPrice = price
        maxProfit = max(maxProfit, price - minPrice)
    }
    return maxProfit
}