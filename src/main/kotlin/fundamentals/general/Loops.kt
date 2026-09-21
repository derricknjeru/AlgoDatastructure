package fundamentals.general

fun main() {
    //for (i in 0..9) println(i)

    val name = "John"
    val age = "21"

    println("Hello, my name is $name & I am $age years old")

    //printAboveShape()

    testForLoop()
}

/*
#
##
###
####
#####
######
 */

fun printAboveShape() {
    for (i in 0..5) {
        println("#".repeat(i))
    }
}

/*
#
##
###
####
#####
######
 */

fun printAboveShape1() {
    for (i in 0..5) {
        for (j in 0..i) {
            print("#")
        }
        println()
    }
}


fun testForLoop() {
    val x: IntArray = intArrayOf(1, 2, 3, 4, 5)
    for ((index, i) in x.withIndex()) {
        println("value $i index = $index")
    }

    val set = HashSet<Int>()
    for ((index, value) in set.withIndex()) {
        println("value $index = $value")
    }

    val nums = intArrayOf(1, 2, 3, 4, 5)
    for ((index, i) in set.withIndex()) {
        println("value $i index = $index")
    }

    for (i in nums.indices) {
        println("num = $i")
    }
    for (i in 1 until nums.size) {
        print("num = ${nums[i]} ")
        //num = 2 num = 3 num = 4 num = 5
    }
    println()
    for (i in 2 downTo 0 step 2) {
        println("i = $i")
        //downTo is inclusive
        /**
         * i = 2
         * i = 0
         */
    }

    for (i in 4..10 step 2) {
        println("i = $i")
        /**
         * i = 4
         * i = 6
         * i = 8
         * i = 10
         *
         */
    }


    for (i in nums.indices) {
        println("i = $i")
    }


}