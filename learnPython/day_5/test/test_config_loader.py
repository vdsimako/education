import json
import os

import pytest

from src.config_loader import load_config, ConfigFileError, ConfigParseError, MissingConfigKeyError, \
    InvalidConfigValueError

# Get the directory of this test file
TEST_DIR = os.path.dirname(os.path.abspath(__file__))
RESOURCES_DIR = os.path.join(TEST_DIR, "resources")


def test_load_config_missing_file_raises_config_file_error():
    with pytest.raises(ConfigFileError):
        load_config("missing_file.json")


def test_load_config_invalid_file_raises_config_parse_error():
    with pytest.raises(ConfigParseError):
        load_config(os.path.join(RESOURCES_DIR, "invalid_config.json"))


def test_load_config_missing_host_raises_missing_key_error():
    with pytest.raises(MissingConfigKeyError):
        load_config(os.path.join(RESOURCES_DIR, "missing_host.json"))


def test_load_config_invalid_host_value_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "invalid_host_value.json"))


def test_load_config_empty_host_value_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "empty_host_value.json"))


def test_load_config_missing_port_value_raises_missing_key_error():
    with pytest.raises(MissingConfigKeyError):
        load_config(os.path.join(RESOURCES_DIR, "missing_port_value.json"))


def test_load_config_non_integer_port_value_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "non_integer_port_value.json"))


def test_load_config_missing_debug_value_raises_missing_key_error():
    with pytest.raises(MissingConfigKeyError):
        load_config(os.path.join(RESOURCES_DIR, "missing_debug_value.json"))


def test_load_config_port_too_low_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "port_too_low.json"))


def test_load_config_port_too_high_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "port_too_high.json"))


def test_load_config_missing_file_preserves_original_cause():
    with pytest.raises(ConfigFileError) as exc_info:
        load_config("nonexistent.json")
    assert exc_info.value.__cause__ is not None
    assert isinstance(exc_info.value.__cause__, FileNotFoundError)


def test_load_config_invalid_json_preserves_original_cause():
    with pytest.raises(ConfigParseError) as exc_info:
        load_config(os.path.join(RESOURCES_DIR, "invalid_config.json"))
    assert exc_info.value.__cause__ is not None
    assert isinstance(exc_info.value.__cause__, json.JSONDecodeError)


def test_load_config_valid_config_returns_dictionary():
    result = load_config(os.path.join(RESOURCES_DIR, "valid_config.json"))
    assert isinstance(result, dict)
    assert "host" in result
    assert "port" in result
    assert "debug" in result


def test_load_config_invalid_debug_value_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "invalid_debug_value.json"))


def test_load_config_invalid_log_file_value_raises_invalid_value_error():
    with pytest.raises(InvalidConfigValueError):
        load_config(os.path.join(RESOURCES_DIR, "invalid_log_file_value.json"))

def test_load_config_valid_config_returns_dictionary_with_optional_log_file():
    result = load_config(os.path.join(RESOURCES_DIR, "valid_config_with_optional_log_file.json"))
    assert isinstance(result, dict)
    assert "host" in result
    assert "port" in result
    assert "debug" in result
    assert "log_file" in result
