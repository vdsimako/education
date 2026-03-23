from src.timing import timing


def test_timing_returns_original_result():
    @timing
    def add(a, b):
        return a + b

    result = add(1, 2)
    assert result == 3


def test_timing_preserves_metadata():
    @timing
    def greet(name):
        """Return greeting message."""
        return f"Hello, {name}"

    assert greet.__name__ == "greet"
    assert greet.__doc__ == "Return greeting message."


def test_timing_prints_execution_info(capsys):
    @timing
    def say_hello():
        return "hello"

    say_hello()

    captured = capsys.readouterr()

    assert "say_hello" in captured.out
    assert "took" in captured.out


def test_timing_works_with_positional_and_keyword_arguments():
    @timing
    def format_user(name, age=0):
        return f"{name}:{age}"

    result1 = format_user("Alice", 30)
    result2 = format_user(name="Bob", age=25)

    assert result1 == "Alice:30"
    assert result2 == "Bob:25"
