import asyncio
import time

import pytest

from src.rate_limiter import AsyncRateLimiter


def test_init_raises_when_max_calls_is_zero():
    with pytest.raises(ValueError):
        AsyncRateLimiter(max_calls=0, period_seconds=1)


def test_init_raises_when_max_calls_is_negative():
    with pytest.raises(ValueError):
        AsyncRateLimiter(max_calls=-1, period_seconds=1)


def test_init_raises_when_period_seconds_is_zero():
    with pytest.raises(ValueError):
        AsyncRateLimiter(max_calls=1, period_seconds=0)


def test_init_raises_when_period_seconds_is_negative():
    with pytest.raises(ValueError):
        AsyncRateLimiter(max_calls=1, period_seconds=-1)


@pytest.mark.asyncio
async def test_first_acquire_returns_immediately():
    limiter = AsyncRateLimiter(max_calls=3, period_seconds=0.1)
    time_from = time.monotonic()
    await limiter.acquire()
    time_to = time.monotonic()
    assert time_to - time_from < 0.1
    assert len(limiter.timestamps) == 1


@pytest.mark.asyncio
async def test_calls_under_limit_do_not_wait():
    limiter = AsyncRateLimiter(max_calls=5, period_seconds=0.1)
    repeat = 4
    for _ in range(repeat):
        time_from = time.monotonic()
        await limiter.acquire()
        time_to = time.monotonic()
        assert time_to - time_from < 0.1

    assert len(limiter.timestamps) == repeat


@pytest.mark.asyncio
async def test_exactly_max_calls_are_allowed_without_waiting():
    max_calls = 3
    limiter = AsyncRateLimiter(max_calls=max_calls, period_seconds=0.1)

    for _ in range(max_calls):
        time_from = time.monotonic()
        await limiter.acquire()
        time_to = time.monotonic()
        assert time_to - time_from < 0.1

    assert len(limiter.timestamps) == max_calls


@pytest.mark.asyncio
async def test_call_above_limit_waits_until_slot_is_free():
    max_calls = 2
    limiter = AsyncRateLimiter(max_calls=max_calls, period_seconds=0.15)

    for _ in range(max_calls):
        await limiter.acquire()

    time_from = time.monotonic()
    await limiter.acquire()
    time_to = time.monotonic()
    assert time_to - time_from >= 0.15


@pytest.mark.asyncio
async def test_extra_call_waits_about_one_period_when_window_is_full():
    max_calls = 2
    limiter = AsyncRateLimiter(max_calls=max_calls, period_seconds=0.2)

    for _ in range(max_calls):
        await limiter.acquire()

    time_from = time.monotonic()
    await limiter.acquire()
    elapsed = time.monotonic() - time_from

    assert 0.15 <= elapsed <= 0.3


@pytest.mark.asyncio
async def test_limiter_works_across_multiple_windows():
    max_calls = 2
    limiter = AsyncRateLimiter(max_calls=max_calls, period_seconds=0.2)

    for _ in range(max_calls):
        await limiter.acquire()

    time_from = time.monotonic()
    await limiter.acquire()
    elapsed = time.monotonic() - time_from

    assert 0.15 <= elapsed <= 0.3

    await asyncio.sleep(0.2)

    for _ in range(max_calls):
        time_from = time.monotonic()
        await limiter.acquire()
        elapsed = time.monotonic() - time_from

        assert elapsed <= 0.1


@pytest.mark.asyncio
async def test_timestamps_remain_ordered():
    limiter = AsyncRateLimiter(max_calls=3, period_seconds=0.1)

    await limiter.acquire()
    await asyncio.sleep(0.05)
    await limiter.acquire()
    await asyncio.sleep(0.05)
    await limiter.acquire()
    await asyncio.sleep(0.21)
    await limiter.acquire()

    values = list(limiter.timestamps)
    assert values == sorted(values)


@pytest.mark.asyncio
async def test_timestamp_count_never_exceeds_max_calls_after_cleanup_check():
    limiter = AsyncRateLimiter(max_calls=3, period_seconds=0.1)

    for i in range(10):
        await limiter.acquire()
        assert len(limiter.timestamps) <= limiter.max_calls

        if i % 2 == 0:
            await asyncio.sleep(0.05)


@pytest.mark.asyncio
async def test_sleep_happens_outside_lock():
    limiter = AsyncRateLimiter(max_calls=1, period_seconds=0.1)

    await limiter.acquire()

    completion_times = []

    async def worker():
        await limiter.acquire()
        completion_times.append(time.monotonic())

    tasks = [asyncio.create_task(worker()) for _ in range(3)]

    await asyncio.wait_for(asyncio.gather(*tasks), timeout=1.0)

    assert len(completion_times) == 3
    assert completion_times == sorted(completion_times)
