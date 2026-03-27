def factorial(a: int) -> int:
    if a < 0:
        raise ValueError("a must be non-negative")
    if a == 0:
        return 1
    return a * factorial(a - 1)
