from datetime import datetime
from pydantic import BaseModel, Field, ConfigDict
from uuid import UUID

from app.db.models import TaskStatus


class TaskCreateRequest(BaseModel):
    task_type: str = Field(min_length=1, max_length=100)
    payload: dict[str, object] = Field(default_factory=dict)


class TaskResponse(BaseModel):
    id: UUID
    task_type: str
    status: TaskStatus
    payload: dict[str, object]
    result: dict[str, object] | None
    error: dict[str, object] | None
    created_at: datetime
    updated_at: datetime | None

    model_config = ConfigDict(from_attributes=True, strict=True)


class TaskListResponse(BaseModel):
    items: list[TaskResponse]


class RetryTaskResponse(BaseModel):
    id: UUID
    status: TaskStatus
