import pytest

from src.existack_homework import read_all_files_in_dir


def test_read_all_files_in_dir_read_single_file(tmp_path):
    (tmp_path / "file.txt").write_text("content")
    assert read_all_files_in_dir(str(tmp_path)) == ["content"]

def test_read_all_files_in_dir_read_multiple_files(tmp_path):
    (tmp_path / "file1.txt").write_text("content1")
    (tmp_path / "file2.txt").write_text("content2")
    assert read_all_files_in_dir(str(tmp_path)) == ["content2", "content1"]

def test_read_all_files_ib_dir_read_only_txt_files(tmp_path):
    (tmp_path / "file1.txt").write_text("content1")
    (tmp_path / "file2.txt").write_text("content2")
    (tmp_path / "file3.csv").write_text("content3")
    assert read_all_files_in_dir(str(tmp_path)) == ["content2", "content1"]

def test_read_all_files_in_dir_empty_dir(tmp_path):
    assert read_all_files_in_dir(str(tmp_path)) == []

def test_read_all_files_in_dir_directory_not_exists(tmp_path):
    with pytest.raises(RuntimeError):
        read_all_files_in_dir(str(tmp_path / "notexist"))

def test_read_all_files_in_dir_file(tmp_path):
    with pytest.raises(RuntimeError):
        read_all_files_in_dir(str(tmp_path / "file.txt"))
