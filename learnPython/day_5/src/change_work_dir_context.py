import os
from contextlib import contextmanager
from pathlib import Path


class PathNotExistError(Exception):
    pass


class PathNotDirError(Exception):
    pass


@contextmanager
def change_work_dir_context(path):
    current_work_dir = os.getcwd()
    path = Path(path)

    if not path.exists():
        raise PathNotExistError(f"Path does not exist {path}")

    if not path.is_dir():
        raise PathNotDirError(f"Path is not a directory {path}")

    os.chdir(path)
    try:
        yield path
    finally:
        os.chdir(current_work_dir)
