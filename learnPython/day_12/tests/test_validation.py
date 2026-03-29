import pytest
from fastapi.testclient import TestClient

from src.main import app


@pytest.fixture
def client() -> TestClient:
    return TestClient(app)


def test_create_task_rejects_payload_without_title(client: TestClient) -> None:
    response = client.post("/tasks", json={"description": "Test Task"})
    assert response.status_code == 422
    assert "detail" in response.json()


def test_create_task_rejects_empty_title(client: TestClient) -> None:
    response = client.post("/tasks", json={"title": "", "description": "Test Task"})
    assert response.status_code == 422
    assert "detail" in response.json()


def test_update_task_accepts_partial_payload(client: TestClient) -> None:
    response = client.post("/tasks", json={"title": "Test task"})
    response = client.put(f"/tasks/{response.json()["id"]}", json={"title": "Updated Task"})
    assert response.status_code == 200
    assert response.json()["title"] == "Updated Task"
