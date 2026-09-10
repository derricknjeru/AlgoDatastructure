package grind75v1.array


fun main() {
    val s = "Derrick".toCharArray()

   val x = s[0].also {
        s[0] = s[1]
    }
   // above block returns the original s[0]
    //hence we can assign it to s[1]
    val y = s[0] //s[0] is already e
    println(x)
    println(y)
}

class ReverseString {
    fun reverseString(s1: CharArray): Unit {
        //Kotlin 2.0
        //s1.reverse()
        var left = 0
        var right = s1.lastIndex

        while (left < right) {
            s1[left] = s1[right].also {
                s1[right] = s1[left]
            }
            left++
            right--
        }
    }
}
