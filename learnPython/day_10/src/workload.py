"""CPU-bound workload helpers for Day 10.

These helpers are intentionally simple starters.
You can replace them with your own workload if you prefer.
"""

from __future__ import annotations


def heavy_square(number: int) -> int:
    """Return a derived value after doing some CPU work.

    Suggested approach:
    - run a loop many times
    - do arithmetic inside the loop
    - finally return a deterministic result based on `number`

    Keep it pure:
    - same input -> same output
    - no external state
    """
    result = 0
    for _ in range(100_000):
        result = number * number
    return result


# Optional extra practice.
def is_prime(number: int) -> bool:
    """Return True if `number` is prime, else False."""
    if number < 2:
        return False

    for i in range(2, number):
        if number % i == 0:
            return False
    return True
