"""Small starter examples for Async Python."""

from __future__ import annotations

import asyncio
from collections.abc import AsyncIterator


async def async_add(a: int, b: int) -> int:
    """Return the sum after yielding control once.

    This small function exists so you can practice async tests.
    """
    await asyncio.sleep(0)
    return a + b


async def gather_example(values: list[int]) -> list[int]:
    """Return a list of doubled values using concurrent tasks.

    Suggested goal:
    - create one coroutine per value
    - run them with `asyncio.gather`
    """

    async def worker(x: int) -> int:
        return x * 2

    for value in values:
        asyncio.create_task(worker(value))

    tasks = [worker(value) for value in values]

    return await asyncio.gather(*tasks)


class AsyncResource:
    """A tiny async context manager for practice."""

    def __init__(self) -> None:
        self.is_open = False

    async def __aenter__(self) -> "AsyncResource":
        # TODO: simulate async setup and mark resource as open.
        self.is_open = True
        return self

    async def __aexit__(self, exc_type, exc, tb) -> None:
        # TODO: simulate async cleanup and mark resource as closed
        self.is_open = False


async def countdown(start: int) -> AsyncIterator[int]:
    """Yield values from start down to 1.

    Use this to practice `async for` and async generator tests.
    """
    if start <= 0:
        return

    for value in range(start, 0, -1):
        await asyncio.sleep(0)
        yield value
