from src.leetcode.task66 import Solution


def test_example1():
    assert Solution().plusOne([1, 2, 3]) == [1, 2, 4]


def test_example2():
    assert Solution().plusOne([4, 3, 2, 1]) == [4, 3, 2, 2]


def test_example3():
    assert Solution().plusOne([9]) == [1, 0]


def test_example4():
    assert Solution().plusOne([1, 2, 8]) == [1, 2, 9]


def test_all_nines():
    assert Solution().plusOne([9, 9, 9]) == [1, 0, 0, 0]
