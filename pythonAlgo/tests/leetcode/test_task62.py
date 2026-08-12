from src.leetcode.task62 import Solution


def test_example1():
    assert Solution().uniquePaths(3, 7) == 28


def test_example2():
    assert Solution().uniquePaths(3, 2) == 3


def test_single_cell():
    assert Solution().uniquePaths(1, 1) == 1
