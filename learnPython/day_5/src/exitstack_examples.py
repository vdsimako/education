from contextlib import ExitStack


def read_all_files(files):
    with ExitStack() as stack:
        files = [stack.enter_context(open(f)) for f in files]
        return [f.read() for f in files]