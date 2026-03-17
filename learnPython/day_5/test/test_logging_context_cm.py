import pytest

from src.logging_context_cm import logging_context


def test_logging_context_prints_start_message(capsys):
    """Test that LoggingContext prints 'START' message when entering context."""
    with logging_context("test_context"):
        captured = capsys.readouterr()
        assert "START - test_context" in captured.out
        assert captured.out.strip() == "START - test_context"

def test_logging_context_prints_success_message_when_no_exception(capsys):
    """Test that LoggingContext prints 'SUCCESS' message when no exception occurs."""
    with logging_context("test_context"):
        pass  # Do nothing, just exit normally

    captured = capsys.readouterr()
    assert "SUCCESS - test_context" in captured.out
    assert "END - test_context" in captured.out

def test_logging_context_print_error_message_when_exception(capsys):
    """Test that LoggingContext prints 'FAILURE' message when no exception occurs."""
    with pytest.raises(Exception):
        with logging_context("test_context"):
            raise Exception("Test exception")

    captured = capsys.readouterr()
    assert "FAILURE: test_context - Exception - Test exception" in captured.out
    assert "END - test_context" in captured.out


def test_end_message_always_printed_on_success(capsys):
    """Test that END message is always printed even on successful completion."""
    with logging_context("test_context"):
        pass

    captured = capsys.readouterr()
    assert "END - test_context" in captured.out


def test_end_message_always_printed_on_exception(capsys):
    """Test that END message is always printed even when exception occurs."""
    with pytest.raises(ValueError):
        with logging_context("test_context"):
            raise ValueError("Test error")

    captured = capsys.readouterr()
    assert "END - test_context" in captured.out


def test_exception_is_not_suppressed(capsys):
    """Test that exceptions are not suppressed by the context manager."""
    with pytest.raises(ValueError, match="Test error"):
        with logging_context("test_context"):
            raise ValueError("Test error")


def test_all_messages_include_context_name(capsys):
    """Test that all printed messages include the context name."""
    try:
        with logging_context("my_test_context"):
            pass
    except:
        pass

    captured = capsys.readouterr()
    assert "START - my_test_context" in captured.out
    assert "SUCCESS - my_test_context" in captured.out
    assert "END - my_test_context" in captured.out


def test_failure_message_includes_context_name(capsys):
    """Test that failure message includes the context name."""
    with pytest.raises(RuntimeError):
        with logging_context("error_context"):
            raise RuntimeError("Something went wrong")

    captured = capsys.readouterr()
    assert "FAILURE: error_context - RuntimeError - Something went wrong" in captured.out