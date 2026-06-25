from fastapi import Depends
from sqlalchemy.ext.asyncio import AsyncSession
from typing import Annotated

from app.db.repositories.task_repository import TaskRepository
from app.db.session import get_db_session
from app.services.task_service import TaskService

SessionDep = Annotated[AsyncSession, Depends(get_db_session)]


async def get_task_repository(session: SessionDep) -> TaskRepository:
    return TaskRepository(session)


TaskRepositoryDep = Annotated[TaskRepository, Depends(get_task_repository)]


def get_task_service(repository: TaskRepositoryDep) -> TaskService:
    return TaskService(repository)


TaskServiceDep = Annotated[TaskService, Depends(get_task_service)]
