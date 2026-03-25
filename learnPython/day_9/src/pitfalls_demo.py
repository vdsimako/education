"""Examples of common async pitfalls.

These functions are intentionally simple so you can experiment with them.
"""

from __future__ import annotations

import asyncio
import time


async def good_sleep(delay: float) -> str:
    """Non-blocking sleep."""
    await asyncio.sleep(delay)
    return "done"


async def bad_sleep(delay: float) -> str:
    """Blocking sleep inside async code.

    This is intentionally bad and exists for demonstration only.
    """
    time.sleep(delay)
    return "done"


async def forgot_await_example() -> object:
    """Return a coroutine object by mistake.

    This demonstrates what happens when you forget `await`.
    """
    return good_sleep(0)
