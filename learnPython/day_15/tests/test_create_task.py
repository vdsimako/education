from __future__ import annotations

import pytest
from fastapi import status


@pytest.mark.asyncio
async def test_create_task_returns_201_and_task_schema(client) -> None:
    request = {
        "task_type": "test",
        "payload": {"test": 2}
    }
    response = await client.post("/tasks", json=request)

    assert response.status_code == status.HTTP_201_CREATED
    assert response.json().get("task_type") == "test"
    assert response.json().get("payload") == {"test": 2}
    assert response.json().get("status") == "pending"
    assert response.json().get("id") is not None
    assert response.json().get("created_at") is not None
    assert response.json().get("updated_at") is not None


@pytest.mark.asyncio
async def test_create_task_rejects_invalid_payload(client) -> None:
    request = {
        "task_type": "",
    }
    response = await client.post("/tasks", json=request)

    assert response.status_code == status.HTTP_422_UNPROCESSABLE_CONTENT
    assert response.json() == {'detail': [
        {'type': 'string_too_short', 'loc': ['body', 'task_type'], 'msg': 'String should have at least 1 character',
         'input': '', 'ctx': {'min_length': 1}}]}
