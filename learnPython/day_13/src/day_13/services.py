from src.day_13.models import Task
from src.day_13.repositories import TaskRepository


class TaskService:
    def __init__(self, repository: TaskRepository) -> None:
        self.repository = repository

    async def create_task(self,
                          title: str,
                          description: str | None = None) -> Task:
        task = Task(title=title, description=description)
        return await self.repository.add(task)

    async def get_task(self, task_id: int) -> Task | None:
        return await self.repository.get_by_id(task_id)

    async def list_tasks(self) -> list[Task]:
        return await self.repository.list_all()

    async def complete_task(self, task_id: int) -> Task | None:
        return await self.repository.mark_done(task_id)

    async def remove_task(self, task_id: int) -> bool:
        return await self.repository.delete(task_id)

    async def get_by_is_done(self, is_done: bool) -> list[Task]:
        return await self.repository.get_by_is_done(is_done)

    async def get_page(self, page: int, page_size: int) -> list[Task]:
        return await self.repository.get_page(page, page_size)
