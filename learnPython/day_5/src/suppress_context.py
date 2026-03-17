class SuppressContext:
    def __enter__(self):
        pass

    def __exit__(self, exc_type, exc_val, exc_tb):
        return exc_type is ValueError