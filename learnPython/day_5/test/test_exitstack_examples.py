import pytest

from src.exitstack_examples import read_all_files

CONTENT = "content"

def test_read_all_read_one_file(tmp_path):
    d = tmp_path / "sub"
    d.mkdir()
    p = d / "hello.txt"

    p.write_text(CONTENT, encoding="utf-8")

    result = read_all_files([p])

    assert len(result) == 1
    assert result[0] == CONTENT

def test_read_all_files_read_multiple_files(tmp_path):
    d = tmp_path / "sub"
    d.mkdir()
    p1 = d / "hello.txt"
    p2 = d / "world.txt"

    p1.write_text(CONTENT, encoding="utf-8")
    p2.write_text(CONTENT, encoding="utf-8")

    result = read_all_files([p1, p2])

    assert len(result) == 2
    assert result[0] == CONTENT
    assert result[1] == CONTENT


def test_read_all_files_empty_list():
    assert read_all_files([]) == []


def test_read_all_files_file_not_found(tmp_path):
    pytest.raises(FileNotFoundError, read_all_files, [tmp_path / "hello.txt"])