import pytest
import re
from src.logging_context import LoggingContext


def test_logging_context():
    logging_context = LoggingContext("test")
    assert logging_context is logging_context.__enter__()


def test_logging_context_prints_start_message(capsys):
    """Test that LoggingContext prints 'START' message when entering context."""
    with LoggingContext("test_context"):
        captured = capsys.readouterr()
        pattern = r"START: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context"
        assert re.search(pattern, captured.out) is not None


def test_logging_context_prints_success_message_when_no_exception(capsys):
    """Test that LoggingContext prints 'SUCCESS' message when no exception occurs."""
    with LoggingContext("test_context"):
        pass  # Do nothing, just exit normally
    
    captured = capsys.readouterr()
    # Use regex to match timestamp patterns
    success_pattern = r"SUCCESS: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context"
    end_pattern = r"END: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context"
    assert re.search(success_pattern, captured.out) is not None
    assert re.search(end_pattern, captured.out) is not None

def test_logging_context_print_error_message_when_exception(capsys):
    """Test that LoggingContext prints 'FAILURE' message when exception occurs."""
    with pytest.raises(Exception):
        with LoggingContext("test_context"):
            raise Exception("Test exception")

    captured = capsys.readouterr()
    # Use regex to match timestamp patterns
    failure_pattern = r"FAILURE: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context - <class 'Exception'> - Test exception"
    end_pattern = r"END: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context"
    assert re.search(failure_pattern, captured.out) is not None
    assert re.search(end_pattern, captured.out) is not None


def test_end_message_always_printed_on_success(capsys):
    """Test that END message is always printed even on successful completion."""
    with LoggingContext("test_context"):
        pass
    
    captured = capsys.readouterr()
    end_pattern = r"END: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context"
    assert re.search(end_pattern, captured.out) is not None


def test_end_message_always_printed_on_exception(capsys):
    """Test that END message is always printed even when exception occurs."""
    with pytest.raises(ValueError):
        with LoggingContext("test_context"):
            raise ValueError("Test error")
    
    captured = capsys.readouterr()
    end_pattern = r"END: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - test_context"
    assert re.search(end_pattern, captured.out) is not None


def test_exception_is_not_suppressed(capsys):
    """Test that exceptions are not suppressed by the context manager."""
    with pytest.raises(ValueError, match="Test error"):
        with LoggingContext("test_context"):
            raise ValueError("Test error")


def test_all_messages_include_context_name(capsys):
    """Test that all printed messages include the context name."""
    try:
        with LoggingContext("my_test_context"):
            pass
    except:
        pass
    
    captured = capsys.readouterr()
    # Use regex to match timestamp patterns for all messages
    start_pattern = r"START: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - my_test_context"
    success_pattern = r"SUCCESS: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - my_test_context"
    end_pattern = r"END: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - my_test_context"
    assert re.search(start_pattern, captured.out) is not None
    assert re.search(success_pattern, captured.out) is not None
    assert re.search(end_pattern, captured.out) is not None


def test_failure_message_includes_context_name(capsys):
    """Test that failure message includes the context name."""
    with pytest.raises(RuntimeError):
        with LoggingContext("error_context"):
            raise RuntimeError("Something went wrong")
    
    captured = capsys.readouterr()
    failure_pattern = r"FAILURE: \d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{6}\+\d{2}:\d{2} - error_context - <class 'RuntimeError'> - Something went wrong"
    assert re.search(failure_pattern, captured.out) is not None