import pytest

from src.example import factorial


@pytest.mark.parametrize("n, excepted", [(1,1), (2,2), (3,6), (4,24)])
def test_factorial(n, excepted):
    assert factorial(n) == excepted


def test_factorial_raises_value_error():
    with pytest.raises(ValueError):
        factorial(-1)
