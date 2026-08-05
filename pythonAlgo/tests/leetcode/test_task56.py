from src.leetcode.task56 import Solution


def test_example1():
    assert Solution().merge([[1, 3], [2, 6], [8, 10], [15, 18]]) == [[1, 6], [8, 10], [15, 18]]


def test_example2():
    assert Solution().merge([[1, 4], [4, 5]]) == [[1, 5]]


def test_example3():
    assert Solution().merge([[1, 4], [0, 4]]) == [[0, 4]]


def test_example4():
    assert Solution().merge([[1, 10], [2, 3], [4, 5]]) == [[1, 10]]