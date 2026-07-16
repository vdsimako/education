import pytest

from src.leetcode.task50 import Solution


def test_example1():
    assert Solution().myPow(2.0, 10) == pytest.approx(1024.0)


def test_example2():
    assert Solution().myPow(2.1, 3) == pytest.approx(9.261)


def test_example3():
    assert Solution().myPow(2.0, -2) == pytest.approx(0.25)


def test_zero_exponent():
    assert Solution().myPow(2.0, 0) == pytest.approx(1.0)
