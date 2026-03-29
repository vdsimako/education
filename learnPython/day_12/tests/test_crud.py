import pytest
from fastapi.testclient import TestClient

from src.dependencies import get_task_service
from src.main import app
from src.services import TaskService


@pytest.fixture
def service() -> TaskService:
    return TaskService()


@pytest.fixture
def client(service) -> TestClient:
    def override_repository() -> TaskService:
        return service

    app.dependency_overrides[get_task_service] = override_repository
    return TestClient(app)


def test_healthcheck_returns_ok(client: TestClient) -> None:
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_create_task_returns_created_task(client: TestClient) -> None:
    response = client.post("/tasks", json={"title": "Test Task"})
    assert response.status_code == 201
    assert response.json() == {'description': None, 'id': 1, 'is_done': False, 'title': 'Test Task'}


def test_list_tasks_returns_all_items(client: TestClient) -> None:
    response = client.get("/tasks")
    assert response.status_code == 200
    assert response.json() == []


def test_list_tasks_returns_created_tasks(client: TestClient) -> None:
    # First create a task
    create_response = client.post("/tasks", json={"title": "Test Task"})
    assert create_response.status_code == 201

    # Then list tasks to verify it appears
    list_response = client.get("/tasks")
    assert list_response.status_code == 200
    tasks = list_response.json()
    assert len(tasks) == 1
    assert tasks[0]['title'] == "Test Task"


def test_get_task_returns_item_when_it_exists(client: TestClient) -> None:
    # First create a task
    create_response = client.post("/tasks", json={"title": "Test Task"})
    assert create_response.status_code == 201

    # Then get the task by ID
    get_response = client.get(f"/tasks/{create_response.json()['id']}")
    assert get_response.status_code == 200
    assert get_response.json()['title'] == "Test Task"


def test_get_task_returns_404_when_item_is_missing(client: TestClient) -> None:
    get_response = client.get("/tasks/1")
    assert get_response.status_code == 404
    assert get_response.json() == {'detail': 'Task with id=1 was not found'}


def test_update_task_changes_existing_item(client: TestClient) -> None:
    client.post("/tasks", json={"title": "Test Task"})
    update_response = client.put("/tasks/1", json={"title": "Updated Task"})
    assert update_response.status_code == 200
    assert update_response.json()['title'] == "Updated Task"


def test_delete_task_removes_item(client: TestClient) -> None:
    client.post("/tasks", json={"title": "Test Task"})
    delete_response = client.delete("/tasks/1")
    assert delete_response.status_code == 204

    list_response = client.get("/tasks")
    assert list_response.status_code == 200
    assert list_response.json() == []
