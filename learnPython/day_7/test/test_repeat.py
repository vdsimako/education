from src.repeat import repeat


def test_repeat():
    @repeat(3)
    def func():
        return "a"
    assert list(func()) == ["a", "a", "a"]

def test_repeat_preserves_metadata():
    @repeat(3)
    def func():
        """TEST"""
        return "a"
    assert func.__name__ == "func"
    assert func.__doc__ == "TEST"