from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from app.db.models import TaskModel, TaskStatus


class TaskRepository:
    def __init__(self, session: AsyncSession) -> None:
        self.session = session

    async def create(self, task_type: str, payload: dict[str, object]):
        task = TaskModel(task_type=task_type, payload=payload)
        self.session.add(task)
        await self.session.commit()
        await self.session.refresh(task)
        return task

    async def get(self, task_id):
        statement = select(TaskModel).where(TaskModel.id == task_id)
        task = await self.session.execute(statement)
        return task.scalar_one_or_none()

    async def list_all(self, status_filter: TaskStatus | None = None):
        statement = select(TaskModel)
        if status_filter is not None:
            statement = statement.where(TaskModel.status == status_filter)
        tasks = await self.session.execute(statement)
        return tasks.scalars().all()