from src.models import User


class UserRepository:
    def __init__(self) -> None:
        self._users = {
            1: User(id=1, name="Alice", email="alice@example.com"),
            2: User(id=2, name="Bob", email="bob@example.com", is_active=False),
        }

    def get_by_id(self, user_id: int) -> User | None:
        return self._users.get(user_id)
