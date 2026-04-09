from fastapi.testclient import TestClient

from src.main import app

client = TestClient(app)


def test_health_returns_200() -> None:
    response = client.get("/health")
    assert response.status_code == 200


def test_health_returns_expected_payload() -> None:
    response = client.get("/health")
    assert response.json() == {"status": "ok"}
