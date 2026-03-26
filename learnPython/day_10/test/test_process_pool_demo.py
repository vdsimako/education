from src.process_pool_demo import run_sequential, run_with_process_pool
from src.workload import heavy_square, is_prime


def test_run_sequential_returns_list_of_results() -> None:
    inputs = [1, 2, 3]

    results = run_sequential(inputs, worker=heavy_square)

    assert isinstance(results, list)
    assert len(results) == len(inputs)


def test_run_sequential_handles_empty_input() -> None:
    assert run_sequential([], worker=heavy_square) == []


def test_process_pool_handles_empty_input() -> None:
    assert run_with_process_pool([], worker=heavy_square) == []


def test_process_pool_matches_sequential_results() -> None:
    inputs = [5, 10, 15]

    sequential = run_sequential(inputs, worker=heavy_square)
    parallel = run_with_process_pool(inputs, worker=heavy_square, processes=2)

    assert parallel == sequential


def test_process_pool_preserves_result_order() -> None:
    inputs = [2, 4, 6, 8]

    sequential = run_sequential(inputs, worker=heavy_square)
    parallel = run_with_process_pool(inputs, worker=heavy_square, processes=2)

    assert parallel == sequential


def test_heavy_square_is_deterministic() -> None:
    value = heavy_square(7)

    assert heavy_square(7) == value


def test_is_prime_is_deterministic() -> None:
    assert is_prime(7) == is_prime(7)

def test_sequential_is_prime():
    sequential = run_sequential([7, 10092123413794001], worker=is_prime)
    assert sequential == [True, False]

def test_parallel_is_prime():
    parallel = run_with_process_pool([7, 10092123413794001], worker=is_prime, processes=2)
    assert parallel == [True, False]