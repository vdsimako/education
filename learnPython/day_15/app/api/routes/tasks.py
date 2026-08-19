from fastapi import APIRouter, Query, status
from typing import Annotated
from uuid import UUID

from app.db.models import TaskStatus
from app.dependecies.dependencies import TaskServiceDep
from app.schemas.tasks import TaskResponse, TaskCreateRequest, TaskListResponse

router = APIRouter(prefix="/tasks", tags=["tasks"])


@router.post("", response_model=TaskResponse, status_code=status.HTTP_201_CREATED)
async def create_task(payload: TaskCreateRequest,
                      service: TaskServiceDep) -> TaskResponse:
    task = await service.create_task(payload)
    return TaskResponse.model_validate(task)


@router.get("/{task_id}", response_model=TaskResponse, status_code=status.HTTP_200_OK)
async def get_tasks(task_id: UUID,
                    service: TaskServiceDep) -> TaskResponse:
    task = await service.get_task(task_id)
    return TaskResponse.model_validate(task)


@router.get("", response_model=TaskListResponse, status_code=status.HTTP_200_OK)
async def get_tasks(service: TaskServiceDep,
                    status_filter: Annotated[TaskStatus | None, Query(alias="status")] = None) -> TaskListResponse:
    tasks = await service.list_tasks(status_filter)
    return TaskListResponse(items=[TaskResponse.model_validate(task) for task in tasks])

@router.post("/{task_id}/retry", response_model=TaskResponse, status_code=status.HTTP_200_OK)
async def retry_task(task_id: UUID, service: TaskServiceDep) -> TaskResponse:
    task = await service.retry_task(task_id)
    return TaskResponse.model_validate(task)
