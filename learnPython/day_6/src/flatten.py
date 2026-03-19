from typing import Generator


def flatten_with_loop(items: list[list[int]]) -> Generator[int, None, None]:
    for item in items:
        for i in item:
            yield i

def flatten(items: list[list[int]]) -> Generator[int, None, None]:
    for item in items:
        yield from item