from contextlib import ExitStack
from pathlib import Path


def read_all_files_in_dir(dir_path: str) -> list[str]:
    p = Path(dir_path)
    if not p.exists():
        raise RuntimeError("Directory does not exist")

    if not p.is_dir():
        raise RuntimeError("Path is not a directory")

    with ExitStack() as stack:
        files = [stack.enter_context(open(f)) for f in sorted(p.iterdir(), key=lambda x: x.name, reverse=True) if f.is_file() and f.name.endswith(".txt")]
        return [f.read() for f in files]
