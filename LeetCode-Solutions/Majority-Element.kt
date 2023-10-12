class Solution {
  fun majorityElement(nums: IntArray): List<Int> {
      val n = nums.size
      val len: Int = n/3
      // if(n == 1){
      //     return listOf(nums[0])
      // }
      var candidate1 = 0
      var candidate2 = 0
      var count1 = 0
      var count2 = 0

      for(num in nums){
          if(num == candidate1){
              count1++
          }else if(num == candidate2){
              count2++
          }else if(count1 == 0){
              candidate1 = num
              count1 = 1
          }else if(count2 == 0){
              candidate2 = num
              count2 = 1
          }else{
              count1--
              count2--
          }
      }

      count1 = 0
      count2 = 0
      for(num in nums){
          if(num == candidate1){
              count1++
          }else if(num == candidate2){
              count2++
          }
      }
      val result = mutableListOf<Int>()
      if(count1 > len){
          result.add(candidate1)
      }
      if (count2 > n / 3) {
          result.add(candidate2)
      }

      return result
  }
}
