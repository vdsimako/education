"""
59. Spiral Matrix II
https://leetcode.com/problems/spiral-matrix-ii/

Given a positive integer n, generate an n x n matrix filled with elements
from 1 to n^2 in spiral order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:
- 1 <= n <= 20
"""
from typing import List


class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        result = [[0] * n for _ in range(n)]
        left = 0
        right = n - 1
        top = 0
        bottom = n - 1
        k = 0
        while left <= right and top <= bottom:
            for row in range(left, right + 1):
                k += 1
                result[top][row] = k
            top += 1
            for column in range(top, bottom + 1):
                k += 1
                result[column][right] = k
            right -= 1
            for row in range(right, left - 1, -1):
                k += 1
                result[bottom][row] = k
            bottom -= 1
            for column in range(bottom, top - 1, -1):
                k += 1
                result[column][left] = k
            left += 1
        return result
