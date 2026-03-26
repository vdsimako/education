from __future__ import annotations

from collections.abc import Callable, Iterable
from multiprocessing import Pool


def run_sequential(
        inputs: Iterable[int],
        worker: Callable[[int], int],
) -> list[int]:
    """Run `worker` for each input one-by-one and return a list of results."""
    results: list[int] = []
    for item in inputs:
        results.append(worker(item))
    return results


def run_with_process_pool(
        inputs: Iterable[int],
        worker: Callable[[int], int],
        processes: int | None = None,
) -> list[int]:
    """Run `worker` for each input via a process pool."""
    with Pool(processes=processes) as pool:
        return pool.map(worker, inputs)