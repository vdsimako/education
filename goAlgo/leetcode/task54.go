package leetcode

// 54. Spiral Matrix
// https://leetcode.com/problems/spiral-matrix/
//
// Given an m x n matrix, return all elements of the matrix in spiral order.
//
// Example 1:
// Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [1,2,3,6,9,8,7,4,5]
//
// Example 2:
// Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// Output: [1,2,3,4,8,12,11,10,9,5,6,7]
//
// Constraints:
// - m == matrix.length
// - n == matrix[i].length
// - 1 <= m, n <= 10
// - -100 <= matrix[i][j] <= 100
func spiralOrder(matrix [][]int) []int {
	result := make([]int, 0)
	top, bottom := 0, len(matrix)-1
	left, rigth := 0, len(matrix[0])-1

	for top <= bottom && left <= rigth {
		for column := left; column <= rigth; column++ {
			result = append(result, matrix[top][column])
		}
		top++
		if top > bottom {
			break
		}

		for row := top; row <= bottom; row++ {
			result = append(result, matrix[row][rigth])
		}
		rigth--

		for column := rigth; column >= left; column-- {
			result = append(result, matrix[bottom][column])
		}
		bottom--

		if left > rigth {
			break
		}

		for row := bottom; row >= top; row-- {
			result = append(result, matrix[row][left])
		}
		left++
	}

	return result
}
