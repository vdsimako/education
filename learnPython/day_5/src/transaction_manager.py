

class TransactionManager:

    def __init__(self, name: str):
        self.name = name


    def __enter__(self):
        print("BEGIN")
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_type is None:
            print("COMMIT")
        else:
            print("ROLLBACK")

        print("CLEANUP RESOURCES")

        return False