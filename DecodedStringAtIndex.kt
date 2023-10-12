class Solution {
    fun decodeAtIndex(s: String, k: Int): String {
        var size = 0L
        var i = 0
        var myK = k.toLong()
        while (size < myK) {
            size = if (s[i].isDigit()) size * (s[i] - '0') else size + 1
            i++
        }
        i--
        while (true) {
            if (s[i].isDigit()) {
                size /= (s[i] - '0')
                myK %= size.toInt()
            } else {
                if (myK % size == 0L) {
                    return s[i].toString()
                }
                size--
            }
            i--
        }
    }
}

fun main(args: Array<String>) {
    val sol = Solution()
    val s ="y959q969u3hb22odq595"
    val k =222280369
    println(sol.decodeAtIndex(s, k))
    // println(s.decodeAtIndex("ha22", 5))
    // println(s.decodeAtIndex("a2345678999999999999999", 1))
}
