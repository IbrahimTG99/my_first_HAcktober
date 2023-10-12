class Solution {
  fun searchRange(nums: IntArray, target: Int): IntArray {
      val result = IntArray(2){ -1 }
      var left = 0
      val n = nums.size
      var right = n - 1

      while(left <= right){
          val mid = left + (right - left) / 2

          if(nums[mid] == target){
              result[0] = mid
              right = mid - 1
          }else if(nums[mid] < target){
              left = mid + 1
          }else{
              right = mid - 1
          }
      }

      left = 0
      right = n - 1
      while(left <= right){
          val mid = left + (right - left) / 2

          if(nums[mid] == target){
              result[1] = mid
              left = mid + 1
          }else if(nums[mid] < target){
              left = mid + 1
          }else{
              right = mid - 1
          }
      }

      return result
  }
}
