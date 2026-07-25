package leetcode

/*
 * 54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 10
 * - -100 <= matrix[i][j] <= 100
 */
class Task54 {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        var top = 0
        var bottom = matrix.size - 1
        var left = 0
        var right = matrix[0].size - 1

        while (top <= bottom && left <= right) {
            for (column in left..right) {
                result.add(matrix[top][column])
            }
            top++

            if (top > bottom) {
                break
            }

            for (row in top..bottom) {
                result.add(matrix[row][right])
            }
            right--

            for (column in right downTo left) {
                result.add(matrix[bottom][column])
            }
            bottom--

            if (left > right) {
                break
            }

            for (row in bottom downTo top) {
                result.add(matrix[row][left])
            }
            left++
        }

        return result
    }
}
