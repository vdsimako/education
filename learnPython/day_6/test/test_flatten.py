from src.flatten import flatten_with_loop, flatten


def test_flatten_with_loop():
    assert list(flatten_with_loop([[1, 2], [3], [4, 5]])) == [1, 2, 3, 4, 5]

def test_flatten():
    assert list(flatten([[1, 2], [3], [4, 5]])) == [1, 2, 3, 4, 5]