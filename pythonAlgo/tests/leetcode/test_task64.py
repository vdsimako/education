from src.leetcode.task64 import Solution


def test_example1():
    assert Solution().minPathSum([[1, 3, 1], [1, 5, 1], [4, 2, 1]]) == 7


def test_example2():
    assert Solution().minPathSum([[1, 2, 3], [4, 5, 6]]) == 12


def test_single_cell():
    assert Solution().minPathSum([[5]]) == 5
