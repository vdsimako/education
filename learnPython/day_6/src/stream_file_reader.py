from typing import Any, Generator, Iterable


def stream_lines(path: str) -> Generator[str, Any, None]:
    with open(path, "r") as file:
        for line in file:
            yield line.rstrip("\n")


def strip_lines(lines: Iterable[str]) -> Generator[str, Any, None]:
    for line in lines:
        yield line.strip()

def filter_empty(lines: Iterable[str]) -> Generator[str, Any, None]:
    for line in lines:
        if line:
            yield line

def to_upper(lines: Iterable[str]) -> Generator[str, Any, None]:
    for line in lines:
        yield line.upper()

def filter_by_word(lines: Iterable[str], word: str) -> Generator[str, Any, None]:
    for line in lines:
        if word in line:
            yield line