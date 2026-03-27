from unittest.mock import Mock

import pytest

from src.external_client import NotificationClient
from src.models import User
from src.repository import UserRepository
from src.service import UserService


@pytest.fixture
def mock_notification_client():
    client = Mock(spec=NotificationClient)
    return client


@pytest.fixture
def repository() -> UserRepository:
    return UserRepository()


@pytest.fixture
def service(repository, mock_notification_client) -> UserService:
    return UserService(repository, mock_notification_client)


@pytest.fixture
def user() -> User:
    return User(id=1, name="Alice", email="alice@example.com")


def test_notification_client_can_be_mocked_in_service_tests(mock_notification_client, service) -> None:
    mock_notification_client.send_welcome_email.return_value = True

    assert service.send_welcome_message(1)


def test_service_handles_notification_failure(mock_notification_client, service) -> None:
    mock_notification_client.send_welcome_email.return_value = False

    assert not service.send_welcome_message(1)


def test_send_welcome_message_calls_notification_client_with_user_data(service, mock_notification_client, user) -> None:
    mock_notification_client.send_welcome_email.return_value = True
    assert service.send_welcome_message(user.id)
    mock_notification_client.send_welcome_email.assert_called_once_with(user.email)
