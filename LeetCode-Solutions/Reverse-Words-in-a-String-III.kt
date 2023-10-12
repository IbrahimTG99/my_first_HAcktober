class Solution {
  fun reverseWords(s: String): String {
      val sb = StringBuilder()
      var prevSpace = 0
      val len = s.length - 1
      for(i in 0..len){
          if(s[i] == ' '){
              var currIndex = i-1
              while(currIndex >= prevSpace){
                  sb.append(s[currIndex])
                  currIndex--
              }
              prevSpace = i + 1
              sb.append(" ")
          }
      }
      for(i in len downTo prevSpace){
          sb.append(s[i])
      }
      return sb.toString()
  }
}
