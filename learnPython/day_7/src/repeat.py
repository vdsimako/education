from functools import wraps


def repeat(n: int):
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            for _ in range(n):
                yield func(*args, **kwargs)

        return wrapper

    return decorator
