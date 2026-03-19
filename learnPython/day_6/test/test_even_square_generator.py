from src.even_square_generator import square_generator


def test_even_square_generator():
    a = square_generator()
    assert next(a) == 0
    assert next(a) == 4
    assert next(a) == 16
    assert next(a) == 36
    assert next(a) == 64