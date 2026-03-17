from contextlib import contextmanager, ExitStack


@contextmanager
def log_block(name):
    print(f"[START] {name}")
    try:
        yield name
    finally:
        print(f"[END] {name}")


with log_block("import-users"):
    print("Working...")


with log_block("import-users") as block_name:
    print(block_name)

def cleanup():
    print("cleanup called")


with ExitStack() as stack:
    stack.callback(cleanup)
    print("working")