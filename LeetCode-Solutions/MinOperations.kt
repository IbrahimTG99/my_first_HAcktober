// You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

// nums is considered continuous if both of the following conditions are fulfilled:

// All elements in nums are unique.
// The difference between the maximum element and the minimum element in nums equals nums.length - 1.
// For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

// Return the minimum number of operations to make nums continuous.
// Input: nums = [4,2,5,3]
// Output: 0
// Explanation: nums is already continuous.

// Input: nums = [1,2,3,5,6]
// Output: 1
// Explanation: One possible solution is to change the last element to 4.
// The resulting array is [1,2,3,5,4], which is continuous.

// Input: nums = [1,10,100,1000]
// Output: 3
// Explanation: One possible solution is to:
// - Change the second element to 2.
// - Change the third element to 3.
// - Change the fourth element to 4.
// The resulting array is [1,2,3,4], which is continuous.

class Solution {
  fun minOperations(nums: IntArray): Int {
    // remove duplicates
    val set = nums.toSet()
    // sort to get continuous elements together
    val sorted = set.sorted().toMutableList()

    val n = sorted.size
    val len = nums.size
    // duplicate elements are going to be counted as operations as they will be changed
    var operations = len - n

    // find the longest continuous subarray
    var currentLength = 1
    var maxLength = 1
    var start = 0
    var end = 0
    var currentStart = 0

    for (i in 1 until sorted.size) {
      if (sorted[i] == sorted[i - 1] + 1) {
        currentLength++
        if (currentLength > maxLength) {
          maxLength = currentLength
          start = currentStart
          end = i
        }
      } else {
        currentLength = 1
        currentStart = i
      }
    }

    // add new elements before start and after start + n - 1 to make it longer
    var tempOperations = operations
    if (sorted[start] > 1) {
      while(tempOperations > 0 && sorted[start] > 1 && start > 0) {
        // check if sorted[start] - 1 is already in sorted
        for (i in start downTo 0) {
          if (sorted[i] == sorted[start] - 1) {
            start--
            continue
          }
        }
        sorted.add(sorted[start] - 1)
        tempOperations--
        if (start > 0) {
          start--
        }

        // do one iteration of slecetion sort to get the new element in the right place
        for (i in sorted.size - 1 downTo 1) {
          if (sorted[i] < sorted[i - 1]) {
            val temp = sorted[i]
            sorted[i] = sorted[i - 1]
            sorted[i - 1] = temp
          }
        }
      }
    }

    if (sorted[end] < len){
      while(tempOperations > 0 && sorted[end] < len && end < sorted.size - 1) {
        // check if sorted[end] + 1 is already in sorted
        for (i in end until sorted.size) {
          if (sorted[i] == sorted[end] + 1) {
            end++
            continue
          }
        }

        sorted.add(sorted[end] + 1)
        tempOperations--
        end++



        // do one iteration of slecetion sort to get the new element in the right place
        for (i in sorted.size -1 downTo end) {
          if (sorted[i] < sorted[i - 1]) {
            val temp = sorted[i]
            sorted[i] = sorted[i - 1]
            sorted[i - 1] = temp
          }
        }
      }
    }


    // loop through sorted and count elements not in range on start + n -1
    // if element is not in range, increment operations
    var boundary = sorted[start]
    var idx = 0
    while (idx < sorted.size) {
      if (sorted[idx] !in boundary..(boundary + len - 1)) {

        println(sorted)
        println("idx: $idx")
        println("boundary: $boundary")
        println("start: $start")
        println("end: $end")
        operations++
        // remove elem from sorted
        sorted.remove(sorted[idx])
        idx--




        tempOperations = 1
        if (sorted[start] > 1) {
          while(tempOperations > 0 && sorted[start] > 1) {
            // check if sorted[start] - 1 is already in sorted
            for (i in start downTo 0) {
              if (sorted[i] == sorted[start] - 1) {
                start--
                boundary = sorted[start]
                continue
              }
            }
            sorted.add(sorted[start] - 1)
            tempOperations--

            // do one iteration of slecetion sort to get the new element in the right place
            for (i in sorted.size - 1 downTo 1) {
              if (sorted[i] < sorted[i - 1]) {
                val temp = sorted[i]
                sorted[i] = sorted[i - 1]
                sorted[i - 1] = temp
                start = i - 1
                boundary = sorted[start]
              }
            }
          }
        }

        if (sorted[end] < len){
          while(tempOperations > 0 && sorted[end] < len && end < sorted.size - 1) {
            // check if sorted[end] + 1 is already in sorted
            for (i in end until sorted.size) {
              if (sorted[i] == sorted[end] + 1) {
                end++
                continue
              }
            }

            sorted.add(sorted[end] + 1)
            tempOperations--
            end++



            // do one iteration of slecetion sort to get the new element in the right place
            for (i in sorted.size -1 downTo end) {
              if (sorted[i] < sorted[i - 1]) {
                val temp = sorted[i]
                sorted[i] = sorted[i - 1]
                sorted[i - 1] = temp
              }
            }
          }
        }

      }
      idx++
    }



    return operations

  }
}


fun main() {
  val solution = Solution()
  val nums = intArrayOf(10,49,16,22,28,34,35,7)
  val minOperations = solution.minOperations(nums)
  println(minOperations)
}

import java.util.LinkedList
import java.util.Queue

class Solution {
	fun minOperations(nums: IntArray): Int {
		nums.sort()
		val queue: Queue<Int> = LinkedList<Int>()
		var maxQueueSize = 0
		val delta = nums.size - 1
		for (value in nums) {
			if (queue.lastOrNull() == value) continue
			while (!(queue.isEmpty() || queue.peek() + delta >= value)) {
				queue.poll()
			}
			queue.add(value)
			maxQueueSize = maxOf(maxQueueSize, queue.size)
		}
		return nums.size - maxQueueSize
	}
}
