from functools import wraps


def log_args(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        print(f"Args: {args}")
        print(f"Kwargs: {kwargs}")
        return func(*args, **kwargs)
    return wrapper