from __future__ import annotations

import pytest_asyncio
from httpx import ASGITransport, AsyncClient
from sqlalchemy.ext.asyncio import create_async_engine, async_sessionmaker

from app.db.base import Base
from app.db.session import get_db_session
from app.main import app

TEST_DATABASE_URL = "sqlite+aiosqlite:///:memory:"


@pytest_asyncio.fixture
async def client() -> AsyncClient:
    engine = create_async_engine(TEST_DATABASE_URL)
    session_local = async_sessionmaker(engine, expire_on_commit=False)

    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.create_all)

    async def override_get_db_session():
        async with session_local() as session:
            yield session

    app.dependency_overrides[get_db_session] = override_get_db_session

    async with AsyncClient(transport=ASGITransport(app=app), base_url="http://test") as async_client:
        yield async_client

    app.dependency_overrides.clear()

    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.drop_all)

    await engine.dispose()

# Later you should add:
# - test database engine
# - async session fixture
# - dependency override for get_db_session
# - test data factories
