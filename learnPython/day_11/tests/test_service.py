import pytest

from src.external_client import NotificationClient
from src.models import User, UserNotFoundError
from src.repository import UserRepository
from src.service import UserService


@pytest.fixture
def repository() -> UserRepository:
    return UserRepository()


@pytest.fixture
def notification_client() -> NotificationClient:
    return NotificationClient()


@pytest.fixture
def service(repository, notification_client) -> UserService:
    return UserService(repository, notification_client)


@pytest.fixture
def user() -> User:
    return User(id=1, name="Alice", email="alice@example.com")


@pytest.fixture
def mock_notification_client(monkeypatch) -> NotificationClient:
    return NotificationClient()


def test_get_user_returns_user_when_found(service, user) -> None:
    assert service.get_user(user_id=user.id) == user


def test_get_user_raises_user_not_found_error_when_missing(service) -> None:
    with pytest.raises(UserNotFoundError):
        service.get_user(3)


def test_get_display_name_returns_formatted_value(service, user) -> None:
    assert service.get_display_name(user_id=user.id) == f"{user.name} <{user.email}>"


def test_send_welcome_message_calls_notification_client(service, user) -> None:
    assert service.send_welcome_message(user_id=user.id)


def test_send_welcome_message_raises_error_when_user_missing(service) -> None:
    with pytest.raises(UserNotFoundError):
        service.send_welcome_message(3)
