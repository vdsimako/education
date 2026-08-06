from src.leetcode.task57 import Solution


def test_example1():
    assert Solution().insert([[1, 3], [6, 9]], [2, 5]) == [[1, 5], [6, 9]]


def test_example2():
    assert Solution().insert([[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], [4, 8]) == [[1, 2], [3, 10], [12, 16]]


def test_example3():
    assert Solution().insert([], [5, 7]) == [[5, 7]]


def test_example4():
    assert Solution().insert([[1, 2], [6, 7]], [3, 5]) == [[1, 2], [3, 5], [6, 7]]
