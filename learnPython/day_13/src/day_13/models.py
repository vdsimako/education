from datetime import datetime, timezone

from sqlalchemy import String, Boolean, DateTime
from sqlalchemy.orm import mapped_column, Mapped

from src.day_13.db import Base


class Task(Base):
    __tablename__ = "tasks"

    id: Mapped[int] = mapped_column(primary_key=True)
    title: Mapped[str] = mapped_column(String(255), nullable=False)
    description: Mapped[str | None] = mapped_column(String(1000), nullable=True)
    is_done: Mapped[bool] = mapped_column(Boolean, default=False, nullable=False)
    priority: Mapped[int] = mapped_column(default=0, nullable=False)
    created_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.now(timezone.utc), nullable=False)

    def __repr__(self) -> str:
        return f"Task(id={self.id}, title={self.title}, description={self.description}, is_done={self.is_done}, priority={self.priority}, created_at={self.created_at})"
