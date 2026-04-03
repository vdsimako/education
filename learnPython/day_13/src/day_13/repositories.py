from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.day_13.models import Task


class TaskRepository:

    def __init__(self, session: AsyncSession) -> None:
        self.session = session

    async def add(self, task: Task) -> Task:
        self.session.add(task)
        await self.session.commit()
        await self.session.refresh(task)
        return task

    async def get_by_id(self, task_id: int) -> Task | None:
        statement = select(Task).where(Task.id == task_id)
        result = await self.session.execute(statement)
        return result.scalar_one_or_none()

    async def list_all(self) -> list[Task]:
        statement = select(Task).order_by(Task.id)
        result = await self.session.execute(statement)
        return list(result.scalars().all())

    async def mark_done(self, task_id) -> Task | None:
        task = await self.get_by_id(task_id)
        if task is None:
            return None

        task.is_done = True
        await self.session.commit()
        await self.session.refresh(task)
        return task

    async def delete(self, task_id: int) -> bool:
        task = await self.get_by_id(task_id)

        if task is None:
            return False

        await self.session.delete(task)
        await self.session.commit()
        return True

    async def get_by_is_done(self, is_done: bool) -> list[Task]:
        statement = select(Task).filter_by(is_done=is_done).order_by(Task.id)
        result = await self.session.execute(statement)
        return list(result.scalars().all())

    async def get_page(self, page: int, page_size: int) -> list[Task]:
        statement = select(Task).offset((page - 1) * page_size).limit(page_size).order_by(Task.id)
        result = await self.session.execute(statement)
        return list(result.scalars().all())
