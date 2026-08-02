from src.leetcode.task55 import Solution


def test_example1():
    assert Solution().canJump([2, 3, 1, 1, 4]) is True


def test_example2():
    assert Solution().canJump([3, 2, 1, 0, 4]) is False


def test_example3():
    assert Solution().canJump([1, 1, 1, 0]) is True
