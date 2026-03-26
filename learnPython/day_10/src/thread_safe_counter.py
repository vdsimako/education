"""Thread-safe counter starter for Day 10.

Your task:
1. Store an integer value.
2. Protect updates with a threading.Lock.
3. Make increment() safe under concurrent access.
4. Expose the current value via get_value().
"""

from __future__ import annotations

import threading


class ThreadSafeCounter:
    _value: int
    _lock: threading.Lock
    """A counter whose state should be safe to update from many threads.

    Starter notes:
    - Keep the counter value in a private attribute.
    - Keep a Lock in another private attribute.
    - Wrap mutations in a `with self._lock:` block.
    """

    def __init__(self, initial: int = 0) -> None:
        self._value = initial
        self._lock = threading.Lock()

    def increment(self, amount: int = 1) -> None:
        """Increase the counter by `amount` in a thread-safe way."""
        with self._lock:
            self._value += amount

    def get_value(self) -> int:
        """Return the current counter value."""
        with self._lock:
            return self._value
