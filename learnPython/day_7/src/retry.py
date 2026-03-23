import time
from functools import wraps


def retry(max_attempts: int = 1,
          delay: float = 0.1,
          backoff: float = 2,
          exceptions: tuple = (Exception,)):
    if backoff < 1:
        raise ValueError("Backoff must be greater than 1")

    def decorator_retry(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            attempt = 0
            current_delay = delay
            for _ in range(max_attempts):
                try:
                    return func(*args, **kwargs)
                except exceptions as e:
                    time.sleep(current_delay)
                    current_delay *= backoff
                    attempt += 1
                    if attempt >= max_attempts:
                        raise e

            raise RuntimeError("Max attempts exceeded")

        return wrapper

    return decorator_retry
