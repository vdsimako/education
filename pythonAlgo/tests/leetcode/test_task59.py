from src.leetcode.task59 import Solution


def test_example1():
    assert Solution().generateMatrix(3) == [[1, 2, 3], [8, 9, 4], [7, 6, 5]]


def test_example2():
    assert Solution().generateMatrix(1) == [[1]]
