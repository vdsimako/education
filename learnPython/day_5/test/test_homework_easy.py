import pytest

from src.homework_easy import convert, ValidationError


def test_convert_valid_value():
    assert convert("123") == 123

def test_convert_invalid_value_raises_validation_error():
    with pytest.raises(ValidationError):
        convert("invalid")