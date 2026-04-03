import pytest

from src.day_13.models import Task
from src.day_13.repositories import TaskRepository


@pytest.mark.asyncio
async def test_add_persists_task(session) -> None:
    repo = TaskRepository(session)

    task = Task(title="Write tests", description="Repository add")
    saved = await repo.add(task)

    assert saved.id is not None
    assert saved.title == "Write tests"
    assert saved.description == "Repository add"
    assert saved.is_done is False


@pytest.mark.asyncio
async def test_get_by_id_returns_task_when_found(session) -> None:
    repo = TaskRepository(session)
    saved = await repo.add(Task(title="Find me"))

    loaded = await repo.get_by_id(saved.id)

    assert loaded is not None
    assert loaded.id == saved.id
    assert loaded.title == "Find me"


@pytest.mark.asyncio
async def test_get_by_id_returns_none_when_missing(session) -> None:
    repo = TaskRepository(session)

    loaded = await repo.get_by_id(999)

    assert loaded is None


@pytest.mark.asyncio
async def test_list_all_returns_all_tasks(session) -> None:
    repo = TaskRepository(session)
    await repo.add(Task(title="Task 1"))
    await repo.add(Task(title="Task 2"))

    tasks = await repo.list_all()

    assert len(tasks) == 2
    assert [task.title for task in tasks] == ["Task 1", "Task 2"]


@pytest.mark.asyncio
async def test_mark_done_updates_existing_task(session) -> None:
    repo = TaskRepository(session)
    saved = await repo.add(Task(title="Complete me"))

    updated = await repo.mark_done(saved.id)

    assert updated is not None
    assert updated.is_done is True


@pytest.mark.asyncio
async def test_mark_done_returns_none_for_missing_task(session) -> None:
    repo = TaskRepository(session)

    updated = await repo.mark_done(999)

    assert updated is None


@pytest.mark.asyncio
async def test_delete_returns_true_when_task_exists(session) -> None:
    repo = TaskRepository(session)
    saved = await repo.add(Task(title="Delete me"))

    deleted = await repo.delete(saved.id)
    loaded = await repo.get_by_id(saved.id)

    assert deleted is True
    assert loaded is None


@pytest.mark.asyncio
async def test_delete_returns_false_when_task_missing(session) -> None:
    repo = TaskRepository(session)

    deleted = await repo.delete(999)

    assert deleted is False


@pytest.mark.asyncio
async def test_get_page_return_task_page_when_exists(session) -> None:
    repo = TaskRepository(session)
    await repo.add(Task(title="Task 1"))
    await repo.add(Task(title="Task 2"))

    tasks = await repo.get_page(1, 10)

    assert len(tasks) == 2
    assert [task.title for task in tasks] == ["Task 1", "Task 2"]


@pytest.mark.asyncio
async def test_get_page_return_none_when_not_found(session) -> None:
    repo = TaskRepository(session)

    tasks = await repo.get_page(1, 10)

    assert tasks == []


@pytest.mark.asyncio
async def test_get_page_return_task_page(session) -> None:
    repo = TaskRepository(session)
    await repo.add(Task(title="Task 1"))
    await repo.add(Task(title="Task 2"))

    tasks = await repo.get_page(1, 1)

    assert len(tasks) == 1
    assert [task.title for task in tasks] == ["Task 1"]


@pytest.mark.asyncio
async def test_get_by_is_done_return_finished_tasks(session) -> None:
    repo = TaskRepository(session)

    await repo.add(Task(title="Task 1", is_done=True))

    tasks = await repo.get_by_is_done(True)

    assert len(tasks) == 1
    assert [task.title for task in tasks] == ["Task 1"]


@pytest.mark.asyncio
async def test_get_by_is_done_return_undone_tasks(session) -> None:
    repo = TaskRepository(session)

    await repo.add(Task(title="Task 1", is_done=False))

    tasks = await repo.get_by_is_done(False)

    assert len(tasks) == 1
    assert [task.title for task in tasks] == ["Task 1"]

@pytest.mark.asyncio
async def test_get_by_is_done_return_empty_when_no_finished_tasks(session) -> None:
    repo = TaskRepository(session)

    await repo.add(Task(title="Task 1", is_done=False))

    tasks = await repo.get_by_is_done(True)

    assert tasks == []

@pytest.mark.asyncio
async def test_get_by_is_done_return_empty_when_no_undone_tasks(session) -> None:
    repo = TaskRepository(session)

    await repo.add(Task(title="Task 1", is_done=True))

    tasks = await repo.get_by_is_done(False)

    assert tasks == []
