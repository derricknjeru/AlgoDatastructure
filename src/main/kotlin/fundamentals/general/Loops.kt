package fundamentals.general

fun main() {
    for (i in 0..9) println(i)

    val name = "John"
    val age = "21"

    println("Hello, my name is $name & I am $age years old")

    printAboveShape()

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


fun testForLoop(){
    val x: IntArray = intArrayOf(1, 2, 3, 4, 5)
    for ((index,i) in x.withIndex()) {
        println("value $i index = $index")
    }

    val set = HashSet<Int>()
}