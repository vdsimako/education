import os

from src.stream_file_reader import stream_lines

# Get the directory of this test file
TEST_DIR = os.path.dirname(os.path.abspath(__file__))
RESOURCES_DIR = os.path.join(TEST_DIR, "resources")


def test_stream_lines_read_one_line():
    result = list(stream_lines("test/resources/one_line.txt"))

    assert result == ["a"]


def test_stream_lines_read_empty_file():
    result = list(stream_lines("test/resources/empty.txt"))

    assert result == []


def test_stream_lines_read_multiple_lines():
    result = list(stream_lines("test/resources/multiple_lines.txt"))

    assert result == ["a", "b", "c"]


def test_stream_lines_read_blank_lines():
    result = list(stream_lines("test/resources/blank_lines.txt"))

    assert result == ["", "", ""]
