import pytest

from src.day_13.models import Task
from src.day_13.services import TaskService


class FakeTaskRepository:
    def __init__(self) -> None:
        self.add_called_with = None
        self.get_called_with = None
        self.mark_done_called_with = None
        self.delete_called_with = None
        self.get_by_is_done_called_with = None
        self.get_page_called_with = None
        self.tasks = {}
        self.next_id = 1

    async def add(self, task: Task) -> Task:
        self.add_called_with = task
        task.id = self.next_id
        self.tasks[task.id] = task
        self.next_id += 1
        return task

    async def get_by_id(self, task_id: int) -> Task | None:
        self.get_called_with = task_id
        return self.tasks.get(task_id)

    async def list_all(self) -> list[Task]:
        return list(self.tasks.values())

    async def mark_done(self, task_id: int) -> Task | None:
        self.mark_done_called_with = task_id
        task = self.tasks.get(task_id)
        if task is None:
            return None
        task.is_done = True
        return task

    async def delete(self, task_id: int) -> bool:
        self.delete_called_with = task_id
        return self.tasks.pop(task_id, None) is not None

    async def get_by_is_done(self, is_done: bool) -> list[Task]:
        self.get_by_is_done_called_with = is_done
        return [task for task in self.tasks.values() if task.is_done == is_done]

    async def get_page(self, page: int, page_size: int) -> list[Task]:
        self.get_page_called_with = (page, page_size)
        return list(self.tasks.values())


@pytest.mark.asyncio
async def test_create_task_builds_and_saves_task() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)

    task = await service.create_task("Read Alembic", "Day 13")

    assert task.id == 1
    assert task.title == "Read Alembic"
    assert task.description == "Day 13"
    assert repo.add_called_with is task


@pytest.mark.asyncio
async def test_get_task_delegates_to_repository() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)
    created = await service.create_task("Find me")

    loaded = await service.get_task(created.id)

    assert repo.get_called_with == created.id
    assert loaded is created


@pytest.mark.asyncio
async def test_list_tasks_delegates_to_repository() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)
    await service.create_task("A")
    await service.create_task("B")

    tasks = await service.list_tasks()

    assert len(tasks) == 2


@pytest.mark.asyncio
async def test_complete_task_delegates_to_repository() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)
    created = await service.create_task("Complete me")

    completed = await service.complete_task(created.id)

    assert repo.mark_done_called_with == created.id
    assert completed is not None
    assert completed.is_done is True


@pytest.mark.asyncio
async def test_remove_task_delegates_to_repository() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)
    created = await service.create_task("Remove me")

    deleted = await service.remove_task(created.id)

    assert repo.delete_called_with == created.id
    assert deleted is True


@pytest.mark.asyncio
async def test_get_by_is_done_delegates_to_repository() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)
    created = await service.create_task("Remove me")

    tasks = await service.get_by_is_done(created.is_done)

    assert repo.get_by_is_done_called_with is created.is_done
    assert tasks == [created]


@pytest.mark.asyncio
async def test_get_page_delegates_to_repository() -> None:
    repo = FakeTaskRepository()
    service = TaskService(repo)
    created = await service.create_task("Remove me")

    tasks = await service.get_page(1, 1)

    assert repo.get_page_called_with == (1, 1)
    assert tasks == [created]
