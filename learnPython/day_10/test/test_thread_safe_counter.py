import threading

from src.thread_safe_counter import ThreadSafeCounter


def test_counter_starts_with_initial_value() -> None:
    counter = ThreadSafeCounter(initial=10)

    assert counter.get_value() == 10


def test_increment_increases_value_by_one() -> None:
    counter = ThreadSafeCounter()

    counter.increment()

    assert counter.get_value() == 1


def test_increment_supports_custom_amount() -> None:
    counter = ThreadSafeCounter()

    counter.increment(amount=5)

    assert counter.get_value() == 5


def test_many_threads_update_counter_correctly() -> None:
    counter = ThreadSafeCounter()
    thread_count = 10
    increments_per_thread = 1000

    def worker() -> None:
        for _ in range(increments_per_thread):
            counter.increment()

    threads = [threading.Thread(target=worker) for _ in range(thread_count)]

    for thread in threads:
        thread.start()

    for thread in threads:
        thread.join()

    expected = thread_count * increments_per_thread
    assert counter.get_value() == expected


def test_counter_remains_correct_across_multiple_rounds() -> None:
    counter = ThreadSafeCounter()

    for _ in range(3):
        threads = [threading.Thread(target=counter.increment) for _ in range(100)]
        for thread in threads:
            thread.start()
        for thread in threads:
            thread.join()

    assert counter.get_value() == 300
