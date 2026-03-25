import asyncio
import time
from collections import deque


class AsyncRateLimiter:
    max_calls: int
    period_seconds: float
    timestamps: deque[float]
    lock: asyncio.Lock

    def __init__(self, max_calls: int, period_seconds: float) -> None:
        if max_calls <= 0:
            raise ValueError("max_calls must be > 0")
        if period_seconds <= 0:
            raise ValueError("period_seconds must be > 0")
        self.max_calls = max_calls
        self.period_seconds = period_seconds
        self.timestamps = deque()
        self.lock = asyncio.Lock()

    async def acquire(self) -> None:
        while True:
            async with (self.lock):
                now = time.monotonic()

                while self.timestamps and now - self.timestamps[0] >= self.period_seconds:
                    self.timestamps.popleft()

                if len(self.timestamps) < self.max_calls:
                    self.timestamps.append(now)
                    return
                delay = max(0.0, self.timestamps[0] + self.period_seconds - now)

            await asyncio.sleep(delay)
