package leetcode.arrayandhashing

fun main() {
    val codec = EncodeDecode()

    val input = listOf("hello", "world", "", "a#b")

    val encoded = codec.encode(input)
    println(encoded) // 5#hello5#world0#3#a#b

    val decoded = codec.decode(encoded)
    println(decoded) // [hello, world, , a#b]
}

class EncodeDecode {

    fun encode(strs: List<String>): String {
        val result = StringBuilder()

        for (str in strs) {
            result.append(str.length)
                .append('#')
                .append(str)
        }

        return result.toString()
    }

    fun decode(s: String): List<String> {
        val result = mutableListOf<String>()
        var i = 0

        while (i < s.length) {
            val delimiter = s.indexOf('#', i)
            val length = s.substring(i, delimiter).toInt()

            val start = delimiter + 1
            val end = start + length

            result.add(s.substring(start, end))
            i = end
        }

        return result
    }

}


class EncodeDecode2 {

    fun encode(strs: List<String>): String {
        if (strs.isEmpty()) return ""

        // Format: "7,5#DerrickNjeru"
        val sizes = strs.map { it.length }

        return sizes.joinToString(",") + "#" + strs.joinToString("")
    }

    fun decode(str: String): List<String> {
        if (str.isEmpty()) return emptyList()

        // Split only at the first "#"
        val (sizesPart, payload) = str.split("#", limit = 2)

        val result = mutableListOf<String>()
        var index = 0

        for (size in sizesPart.split(",")) {
            val length = size.toInt()

            result += payload.substring(index, index + length)
            index += length
        }

        return result
    }
}