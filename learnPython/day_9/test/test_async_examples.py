import asyncio
import time

import pytest

from src.async_examples import async_add, gather_example, countdown, AsyncResource


@pytest.mark.asyncio
async def test_async_function_returns_correct_value() -> None:
    add_result = await async_add(1, 2)
    assert add_result == 3


@pytest.mark.asyncio
async def test_gather_returns_all_expected_results() -> None:
    result = await gather_example([1, 2, 3])
    assert result == [2, 4, 6]


@pytest.mark.asyncio
async def test_async_generator_yields_items_in_order() -> None:
    result = countdown(5)
    result_list = [i async for i in result]
    assert result_list == [5, 4, 3, 2, 1]


@pytest.mark.asyncio
async def test_async_context_manager_performs_setup_and_cleanup() -> None:
    async with AsyncResource() as resource:
        assert resource.is_open

    assert not resource.is_open


async def blocking_worker(delay: float) -> None:
    time.sleep(delay)


async def async_friendly_worker(delay: float) -> None:
    await asyncio.sleep(delay)


@pytest.mark.asyncio
async def test_blocking_code_behaves_differently_from_async_friendly_code():
    start = time.monotonic()
    await asyncio.gather(
        blocking_worker(0.1),
        blocking_worker(0.1),
    )
    blocking_elapsed = time.monotonic() - start

    start = time.monotonic()
    await asyncio.gather(
        async_friendly_worker(0.1),
        async_friendly_worker(0.1),
    )
    async_elapsed = time.monotonic() - start

    assert blocking_elapsed > 0.18
    assert async_elapsed < 0.15
    assert blocking_elapsed > async_elapsed


