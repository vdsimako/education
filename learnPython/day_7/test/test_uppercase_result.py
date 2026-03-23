from src.uppercase_result import uppercase_result


def test_uppercase_result():
    @uppercase_result
    def func(a, b):
        return a + b
    assert func("a", "b") == "AB"

def test_uppercase_result_preserves_metadata():
    @uppercase_result
    def func(a, b):
        """TEST"""
        return a + b
    assert func.__name__ == "func"
    assert func.__doc__ == "TEST"