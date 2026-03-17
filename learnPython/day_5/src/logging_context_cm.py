from contextlib import contextmanager


@contextmanager
def logging_context(name: str):
    print(f"START - {name}")
    try:
        yield name
    except Exception as e:
        print(f"FAILURE: {name} - {type(e).__name__} - {e}")
        raise e
    else:
        print(f"SUCCESS - {name}")
    finally:
        print(f"END - {name}")