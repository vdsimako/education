package leetcode

/*
 * 59. Spiral Matrix II
 * https://leetcode.com/problems/spiral-matrix-ii/
 *
 * Given a positive integer n, generate an n x n matrix filled with elements
 * from 1 to n^2 in spiral order.
 *
 * Example 1:
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 *
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 *
 * Constraints:
 * - 1 <= n <= 20
 */
class Task59 {
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array(n) { IntArray(n) }
        var k = 0
        var top = 0
        var bottom = n - 1
        var left = 0
        var right = n - 1

        while (left <= right && top <= bottom) {
            for (row in left..right) {
                result[top][row] = ++k
            }
            top++
            for (column in top..bottom) {
                result[column][right] = ++k
            }
            right--
            for (row in right downTo left) {
                result[bottom][row] = ++k
            }
            bottom--
            for (column in bottom downTo top) {
                result[column][left] = ++k
            }
            left++
        }
        return result
    }
}
