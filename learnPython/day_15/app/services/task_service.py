from app.core.exceptions import TaskNotFoundError
from app.db.models import TaskModel, TaskStatus
from app.db.repositories.task_repository import TaskRepository
from app.schemas.tasks import TaskCreateRequest


class TaskService:
    def __init__(self, task_repository: TaskRepository) -> None:
        self.task_repository = task_repository

    async def create_task(self, payload: TaskCreateRequest) -> TaskModel:
        return await self.task_repository.create(task_type=payload.task_type, payload=payload.payload)

    async def get_task(self, task_id) -> TaskModel:
        task = await self.task_repository.get(task_id)
        if task is None:
            raise TaskNotFoundError(str(task_id))
        return await self.task_repository.get(task_id)

    async def list_tasks(self, status_filter: TaskStatus | None = None):
        return await self.task_repository.list_all(status_filter)

    async def retry_task(self, task_id):
        task = await self.get_task(task_id)
        if task.status != TaskStatus.FAILED:
            raise HTTPException(status_code=400, detail="Task is not in failed state")
        return await self.create_task(TaskCreateRequest(task_type=task.task_type, payload=task.payload))
