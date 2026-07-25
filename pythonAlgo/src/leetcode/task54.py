"""
54. Spiral Matrix
https://leetcode.com/problems/spiral-matrix/

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 10
- -100 <= matrix[i][j] <= 100
"""
from typing import List


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        top, bottom, left, right = 0, len(matrix) - 1, 0, len(matrix[0]) - 1
        result = []
        while top <= bottom and left <= right:
            for column in range(left, right + 1):
                result.append(matrix[top][column])
            top += 1

            if top > bottom:
                break

            for row in range(top, bottom + 1):
                result.append(matrix[row][right])
            right -= 1

            for column in range(right, left - 1, -1):
                result.append(matrix[bottom][column])
            bottom -= 1

            if left > right:
                break

            for row in range(bottom, top - 1, -1):
                result.append(matrix[row][left])
            left += 1

        return result
