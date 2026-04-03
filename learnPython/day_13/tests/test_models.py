import pytest
from sqlalchemy.exc import IntegrityError

from src.day_13.models import Task


@pytest.mark.asyncio
async def test_task_can_be_created_with_required_fields(session) -> None:
    task = Task(title="Learn SQLAlchemy")
    session.add(task)
    await session.commit()
    await session.refresh(task)

    assert task.id is not None
    assert task.title == "Learn SQLAlchemy"
    assert task.description is None


@pytest.mark.asyncio
async def test_task_is_done_defaults_to_false(session) -> None:
    task = Task(title="Default check")
    session.add(task)
    await session.commit()
    await session.refresh(task)

    assert task.is_done is False


@pytest.mark.asyncio
async def test_task_created_at_is_set(session) -> None:
    task = Task(title="Timestamp check")
    session.add(task)
    await session.commit()
    await session.refresh(task)

    assert task.created_at is not None


@pytest.mark.asyncio
async def test_task_title_is_required(session) -> None:
    task = Task(title=None)  # type: ignore[arg-type]
    session.add(task)

    with pytest.raises(IntegrityError):
        await session.commit()