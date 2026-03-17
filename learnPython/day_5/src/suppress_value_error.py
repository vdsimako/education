class SuppressValueError:
    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc, tb):
        return exc_type is ValueError


with SuppressValueError():
    int("abc")  # ValueError is suppressed


with SuppressValueError():
    1 / 0