from typing import Callable


def callable_example(n: int, transformation_func: Callable[[int], int]) -> int:
    return transformation_func(n)
