import json
from json import JSONDecodeError


class ConfigError(Exception):
    pass

class ConfigFileError(ConfigError):
    pass

class ConfigParseError(ConfigError):
    pass

class MissingConfigKeyError(ConfigError):
    pass

class InvalidConfigValueError(ConfigError):
    pass

REQUIRED_KEYS = {"host": str, "port": int, "debug": bool}
OPTIONAL_KEYS = {"log_file": str}

def load_config(path: str) -> dict:
    try:
        with open(path) as config_file:
            config_json = config_file.read()
        config = json.loads(config_json)

        for key in REQUIRED_KEYS.keys():
            if key not in config:
                raise MissingConfigKeyError(f"Missing config key: {key}")

            if not isinstance(config[key], REQUIRED_KEYS[key]):
                raise InvalidConfigValueError(f"Invalid config value: {key}")

            if key =="host" and not config[key].strip():
                raise InvalidConfigValueError(f"Invalid config value: {key}")

            if key == "port" and (config[key] < 1 or config[key] > 65535):
                raise InvalidConfigValueError(f"Invalid config value: {key}")

        for key in OPTIONAL_KEYS.keys():
            if key not in config:
                continue

            if not isinstance(config[key], OPTIONAL_KEYS[key]):
                raise InvalidConfigValueError(f"Invalid config value: {key}")

        if not config:
            raise ConfigFileError("Config file is empty")

        return config
    except FileNotFoundError as e:
        raise ConfigFileError("Config file not found") from e
    except JSONDecodeError as e:
        raise ConfigParseError("Config file parse error") from e


