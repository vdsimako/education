from datetime import datetime, UTC


class LoggingContext:
    def __init__(self, name: str):
        self.name = name

    def __enter__(self):
        print(f"START: {datetime.now(UTC).isoformat()} - {self.name}")
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_type is None:
            print(f"SUCCESS: {datetime.now(UTC).isoformat()} - {self.name}")
        else:
            print(f"FAILURE: {datetime.now(UTC).isoformat()} - {self.name} - {exc_type} - {exc_val} - {exc_tb}")

        print(f"END: {datetime.now(UTC).isoformat()} - {self.name}")

        return False
