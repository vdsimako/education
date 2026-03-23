import time

import pytest

from src.retry import retry


def test_retry_fails_fails_on_first_call_and_success_on_last_call():
    count = 0

    @retry(max_attempts=3, delay=0.1, backoff=2, exceptions=(Exception,))
    def test_func():
        nonlocal count
        count += 1
        if count < 3:
            raise Exception("Failed")
        return 1

    result = test_func()
    assert result == 1
    assert count == 3


def test_retry_stop_after_success():
    count = 0

    @retry(max_attempts=3, delay=0.1, backoff=2, exceptions=(Exception,))
    def test_func():
        nonlocal count
        count += 1
        if count == 1:
            raise Exception("Failed")
        return count

    result = test_func()
    assert result == 2
    assert count == 2


def test_retry_raises_original_exception():
    @retry(max_attempts=3, delay=0.1, backoff=2, exceptions=(Exception,))
    def test_func():
        raise ValueError("Failed")

    with pytest.raises(ValueError) as e:
        test_func()
    assert str(e.value) == "Failed"


def test_retry_check_retry_interval(monkeypatch):
    delay_list = []
    monkeypatch.setattr(time, "sleep", lambda delay: delay_list.append(delay))

    @retry(max_attempts=3, delay=1, backoff=2, exceptions=(Exception,))
    def test_func():
        raise Exception("Failed")

    with pytest.raises(Exception) as e:
        test_func()

    assert delay_list == [1, 2, 4]
    assert str(e.value) == "Failed"


def test_retry_accept_tuple_exception():
    count = 0

    @retry(max_attempts=3, delay=0.1, backoff=2, exceptions=(RuntimeError, ValueError))
    def func():
        nonlocal count
        count += 1
        if count == 1:
            raise RuntimeError("Failed")
        if count == 2:
            raise ValueError("Failed")

        return count

    result = func()
    assert result == 3
    assert count == 3


def test_retry_raises_exception():
    count = 0

    @retry(max_attempts=3, delay=0.1, backoff=2, exceptions=(ValueError,))
    def func():
        nonlocal count
        count += 1
        raise RuntimeError("Failed")

    with pytest.raises(RuntimeError) as e:
        func()
    assert str(e.value) == "Failed"
    assert count == 1


def test_retry_preserves_metadata():
    @retry(max_attempts=3, delay=0.1, backoff=2, exceptions=(Exception,))
    def test_func():
        """Test function"""
        return 1

    assert test_func.__name__ == "test_func"
    assert test_func.__doc__ == "Test function"
