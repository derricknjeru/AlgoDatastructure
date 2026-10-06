package leetcode._4binarysearch

fun main() {

}

// https://www.youtube.com/watch?v=U2SozAs9RzA
fun minEatingSpeed(piles: IntArray, h: Int): Int {
    var left = 1
    var right = piles.maxOrNull() ?: 1
    var res = right

    while (left <= right) {
        val speed = left + (right - left) / 2
        var hours = 0L
        for (pile in piles) {
            hours += (pile / speed) + if (pile % speed == 0) 0 else 1
        }

        if (hours <= h) {
            res = speed
            right = speed - 1
        } else {
            left = speed + 1
        }
    }

    return res
}


fun minEatingSpeed2(piles: IntArray, h: Int): Int {
    var left = 1
    var right = piles.maxOrNull()!!

    while (left < right) {
        val speed = left + (right - left) / 2
        var hours = 0L

        for (pile in piles) {
            hours += (pile + speed - 1) / speed   // integer ceil
        }

        if (hours <= h) {
            right = speed        // speed works, but keep it
        } else {
            left = speed + 1
        }
    }
    return left
}
