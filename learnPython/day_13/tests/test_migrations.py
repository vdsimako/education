from pathlib import Path

from alembic import command
from alembic.config import Config
from sqlalchemy import create_engine, inspect


def make_alembic_config(db_path: Path) -> Config:
    config = Config("alembic.ini")
    config.set_main_option("sqlalchemy.url", f"sqlite+aiosqlite:///{db_path}")
    return config


def test_initial_migration_creates_tasks_table(tmp_path: Path) -> None:
    db_path = tmp_path / "migration_test.db"
    config = make_alembic_config(db_path)

    command.upgrade(config, "head")

    engine = create_engine(f"sqlite:///{db_path}")
    inspector = inspect(engine)

    assert "tasks" in inspector.get_table_names()


def test_second_migration_adds_priority_column(tmp_path: Path) -> None:
    db_path = tmp_path / "migration_test_2.db"
    config = make_alembic_config(db_path)

    command.upgrade(config, "head")

    engine = create_engine(f"sqlite:///{db_path}")
    inspector = inspect(engine)
    columns = {column["name"] for column in inspector.get_columns("tasks")}

    assert "priority" in columns
