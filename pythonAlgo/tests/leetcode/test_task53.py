from src.leetcode.task53 import Solution


def test_example1():
    assert Solution().maxSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4]) == 6


def test_example2():
    assert Solution().maxSubArray([1]) == 1


def test_example3():
    assert Solution().maxSubArray([5, 4, -1, 7, 8]) == 23
