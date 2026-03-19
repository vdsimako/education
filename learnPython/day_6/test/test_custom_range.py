import pytest

from src.custom_range import CustomRange


def test_custom_range():
    result = list(CustomRange(1, 5))
    assert result == [1, 2, 3, 4]

def test_custom_range_empty():
    result = list(CustomRange(1, 1))
    assert result == []

def test_custom_range_positive_step():
    result = list(CustomRange(1, 10, 2))
    assert result == [1, 3, 5, 7, 9]

def test_custom_range_negative_step():
    pytest.raises(ValueError, CustomRange, 1, 5, -1)

def test_custom_range_zero_step():
    pytest.raises(ValueError, CustomRange, 0, 1, 0)