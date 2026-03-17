import pytest

from src.suppress_context import SuppressContext


def test_suppress_context():
    with SuppressContext():
        pass

def test_suppress_context_suppresses_value_error():
        with SuppressContext():
            raise ValueError("Test error")

def test_suppress_context_raise_error():
    with pytest.raises(RuntimeError):
        with SuppressContext():
            raise RuntimeError("Test error")