import java.util.*

class Solution {
    fun removeDuplicateLetters(s: String): String {
        val duplicates = MutableList(26) { 0 }
        val len = s.length

        for (i in 0 until len) {
            duplicates[s[i] - 'a'] += 1
        }

        val stack = Stack<Char>()

        for (i in 0 until len) {
            val c = s[i]
            duplicates[c - 'a'] -= 1

            if (stack.contains(c)) {
                continue
            }

            while (stack.isNotEmpty() && stack.peek() > c && duplicates[stack.peek() - 'a'] > 0) {
                stack.pop()
            }

            stack.push(c)
        }

        // print stack converted to string


        val sb = StringBuilder()

        while (!stack.isEmpty()) {
            sb.append(stack.pop())
        }

        return sb.reverse().toString()

    }
}

fun main(args: Array<String>) {
    println(Solution().removeDuplicateLetters("bcabc"))
    println(Solution().removeDuplicateLetters("cabd"))
}
