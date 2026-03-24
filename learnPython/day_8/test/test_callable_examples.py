import pytest

from src.callable_examples import callable_example


def test_callable_example():
    result = callable_example(1, lambda x: x + 1)
    assert result == 2


def test_callable_example_with_multiplication():
    result = callable_example(1, lambda x: x * 2)
    assert result == 2


def test_callable_with_invalid_argument():
    def str_func(s: str) -> str:
        return s.upper()

    with pytest.raises(AttributeError):
        callable_example(1, str_func)
