from src.config import load_settings
from fastapi.testclient import TestClient
from src.main import app

client = TestClient(app)


def test_settings_use_defaults_when_env_vars_missing(monkeypatch) -> None:
    monkeypatch.delenv("APP_NAME", raising=False)
    monkeypatch.delenv("APP_ENV", raising=False)
    monkeypatch.delenv("LOG_LEVEL", raising=False)
    monkeypatch.delenv("PORT", raising=False)

    settings = load_settings()

    assert settings.app_name == "infra-basics-app"
    assert settings.app_env == "local"
    assert settings.log_level == "INFO"
    assert settings.port == 8000


def test_port_is_parsed_as_integer(monkeypatch) -> None:
    monkeypatch.setenv("PORT", "9000")

    settings = load_settings()

    assert settings.port == 9000
    assert isinstance(settings.port, int)


def test_config_endpoint_returns_safe_values() -> None:
    test_client = TestClient(app)
    response = test_client.get("/config")
    assert response.status_code == 200
    assert response.json() == {'app_env': 'local',
                               'app_name': 'infra-basics-app',
                               'log_level': 'INFO',
                               'port': 8000}
