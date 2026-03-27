import pytest

from src.models import User
from src.repository import UserRepository


@pytest.fixture
def repository() -> UserRepository:
    return UserRepository()

@pytest.fixture
def user() -> User:
    return User(id=1, name="Alice", email="alice@example.com")


def test_get_by_id_returns_user_when_user_exists(repository, user) -> None:
    found_user = repository.get_by_id(1)
    assert found_user == user


def test_get_by_id_returns_none_when_user_does_not_exist(repository) -> None:
    user = repository.get_by_id(3)
    assert user is None
