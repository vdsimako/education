package leetcode;

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
public class Task59 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int right = n - 1;
        int bottom = n - 1;
        int top = 0;
        int left = 0;
        int k = 0;
        while (left <= right && top <= bottom) {
            for (int row = left; row <= right; row++) {
                result[top][row] = ++k;
            }
            top++;
            for (int column = top; column <= bottom; column++) {
                result[column][right] = ++k;
            }
            right--;
            for (int row = right; row >= left; row--) {
                result[bottom][row] = ++k;
            }
            bottom--;
            for (int column = bottom; column >= top; column--) {
                result[column][left] = ++k;
            }
            left++;
        }

        return result;
    }
}
