class Solution {
    fun isMonotonic(nums: IntArray): Boolean {
        var isIncreasing = true
        var isDecreasing = true
        for (i in 0 until nums.size - 1) {
            if (nums[i] == nums[i + 1]) {
                continue
            }else{
                if (nums[i] > nums[i + 1]) {
                    isIncreasing = false
                    if (!isDecreasing) {
                        return false
                    }
                }
                if (nums[i] < nums[i + 1]) {
                    isDecreasing = false
                    if (!isIncreasing) {
                        return false
                    }
                }
            }
        }
        return isIncreasing || isDecreasing
    }
}
