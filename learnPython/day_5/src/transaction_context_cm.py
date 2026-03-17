from contextlib import contextmanager


@contextmanager
def transaction_context():
    print("BEGIN")
    try:
        yield
    except Exception as e:
        print("ROLLBACK")
        raise e
    else:
        print("COMMIT")
    finally:
        print("CLEANUP RESOURCES")