package leetcode

// 59. Spiral Matrix II
// https://leetcode.com/problems/spiral-matrix-ii/
//
// Given a positive integer n, generate an n x n matrix filled with elements
// from 1 to n^2 in spiral order.
//
// Example 1:
// Input: n = 3
// Output: [[1,2,3],[8,9,4],[7,6,5]]
//
// Example 2:
// Input: n = 1
// Output: [[1]]
//
// Constraints:
// - 1 <= n <= 20
func generateMatrix(n int) [][]int {
	result := make([][]int, n)
	for i := range n {
		result[i] = make([]int, n)
	}
	k := 0
	left, right := 0, n-1
	top, bottom := 0, n-1
	for left <= right && top <= bottom {
		for row := left; row <= right; row++ {
			k++
			result[top][row] = k
		}
		top++
		for column := top; column <= bottom; column++ {
			k++
			result[column][right] = k
		}
		right--
		for row := right; row >= left; row-- {
			k++
			result[bottom][row] = k
		}
		bottom--
		for column := bottom; column >= top; column-- {
			k++
			result[column][left] = k
		}
		left++
	}
	return result
}
