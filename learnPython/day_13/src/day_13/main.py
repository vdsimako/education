from src.day_13.db import AsyncSessionFactory
from src.day_13.models import Task
from src.day_13.repositories import TaskRepository
from src.day_13.services import TaskService


async def practice_session_usage() -> None:
    task = Task(title="Task 1", description="Task 1 description")
    async with AsyncSessionFactory() as session:
        repository = TaskRepository(session)
        service = TaskService(repository)

        task = await service.create_task(task.title, task.description)
        print(task)


async def main() -> None:
    # async with engine.begin() as conn:
    #     await conn.run_sync(Base.metadata.create_all)
    await practice_session_usage()


if __name__ == "__main__":
    import asyncio

    asyncio.run(main())
