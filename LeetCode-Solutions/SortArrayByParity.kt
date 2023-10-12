class Solution {
    fun sortArrayByParity(nums: IntArray): IntArray {
        var i = 0
        var j = nums.size - 1
        while (i < j) {
            if (nums[i] % 2 == 1) {

                nums[i] = nums[i] xor nums[j]
                nums[j] = nums[i] xor nums[j]
                nums[i] = nums[i] xor nums[j]
                j--
            } else {
                i++
            }
        }
        return nums
    }
}
    fun main(){
        val sol = Solution()
        val nums = intArrayOf(3,1,2,4)
        println(sol.sortArrayByParity(nums).contentToString())
    }
