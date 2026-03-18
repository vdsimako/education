import os

import pytest

from src.change_work_dir_context import change_work_dir_context, PathNotExistError, PathNotDirError


def test_change_work_dir_context_restore_work_dir(tmp_path):
    current_work_dir = os.getcwd()

    with change_work_dir_context(tmp_path):
        pass

    assert os.getcwd() == current_work_dir

def test_change_work_dir_context(tmp_path):
    current_work_dir = os.getcwd()
    with change_work_dir_context(tmp_path):
        assert os.getcwd() == str(tmp_path)

    assert os.getcwd() == current_work_dir


def test_change_work_dir_context_path_not_exist(tmp_path):
    current_work_dir = os.getcwd()
    
    with pytest.raises(PathNotExistError):
        with change_work_dir_context(tmp_path / "nonexistent"):
            pass

    assert os.getcwd() == current_work_dir

def test_change_work_dir_path_not_dir(tmp_path):
    current_work_dir = os.getcwd()

    d = tmp_path / "sub"
    d.mkdir()
    p = d / "hello.txt"

    p.write_text("CONTENT", encoding="utf-8")

    with pytest.raises(PathNotDirError):
        with change_work_dir_context(str(p)):
            pass

    assert os.getcwd() == current_work_dir

