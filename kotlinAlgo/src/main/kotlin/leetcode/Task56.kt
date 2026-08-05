package leetcode

import java.util.Arrays
import kotlin.math.max

/*
 * 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 * - 1 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^5
 */
class Task56 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result: MutableList<IntArray> = mutableListOf()
        Arrays.sort(intervals, Comparator.comparing { it[0] })
        for (interval in intervals) {
            if (result.isEmpty() || result[result.size - 1][1] < interval[0]) {
                result.add(interval)
            } else {
                result[result.size - 1][1] = max(result[result.size - 1][1], interval[1])
            }
        }
        return result.toTypedArray()
    }
}
