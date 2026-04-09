import logging

from fastapi.testclient import TestClient

from src.main import app

client = TestClient(app)


def test_logging_is_configured() -> None:
    root_logger = logging.getLogger()
    assert root_logger.handlers


def test_health_endpoint_emits_info_log(caplog) -> None:
    with caplog.at_level(logging.INFO):
        client.get("/health")

    assert "Health endpoint called" in caplog.text


def test_error_demo_emits_exception_log(caplog) -> None:
    with caplog.at_level(logging.ERROR):
        client.get("/error-demo")

    assert "Handled demo exception" in caplog.text
