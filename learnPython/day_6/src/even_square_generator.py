from typing import Generator


def square_generator() -> Generator[int, None, None]:
    return (x * x for x in range(20) if x % 2 == 0)