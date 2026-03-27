from src.external_client import NotificationClient
from src.models import User, UserNotFoundError
from src.repository import UserRepository


class UserService:
    def __init__(
        self,
        repository: UserRepository,
        notification_client: NotificationClient,
    ) -> None:
        self.repository = repository
        self.notification_client = notification_client

    def get_user(self, user_id: int) -> User:
        user = self.repository.get_by_id(user_id)
        if user is None:
            raise UserNotFoundError(f"User with id={user_id} was not found")
        return user

    def get_display_name(self, user_id: int) -> str:
        user = self.get_user(user_id)
        return f"{user.name} <{user.email}>"

    def send_welcome_message(self, user_id: int) -> bool:
        user = self.get_user(user_id)
        return self.notification_client.send_welcome_email(user.email)
