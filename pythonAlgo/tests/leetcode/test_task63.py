from src.leetcode.task63 import Solution


def test_example1():
    assert Solution().uniquePathsWithObstacles([[0, 0, 0], [0, 1, 0], [0, 0, 0]]) == 2


def test_example2():
    assert Solution().uniquePathsWithObstacles([[0, 1], [0, 0]]) == 1


def test_start_blocked():
    assert Solution().uniquePathsWithObstacles([[1, 0]]) == 0


def test_example3():
    assert Solution().uniquePathsWithObstacles([[0, 1, 0], [0, 0, 0], [0, 0, 0]]) == 3


def test_example4():
    assert Solution().uniquePathsWithObstacles([[0, 1], [0, 0], [0, 0]]) == 2
