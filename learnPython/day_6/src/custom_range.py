class CustomRange:
    def __init__(self, start: int, stop: int, step: int = 1):
        if step <= 0:
            raise ValueError("step must be positive")

        if start > stop:
            raise ValueError("start must be less than stop")

        self.start = start
        self.stop = stop
        self.step = step

        self.current = start

    def __iter__(self):
        return self

    def __next__(self):
        if self.current >= self.stop:
            raise StopIteration

        value = self.current
        self.current += self.step
        return value
