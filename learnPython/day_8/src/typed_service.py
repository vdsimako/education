from typing import TypedDict, Protocol


class UserData(TypedDict):
    id: int
    name: str
    active: bool


class UserRepositoryProtocol(Protocol):

    def get_user(self, user_id: int) -> UserData | None:
        ...


class UserRepository(UserRepositoryProtocol):
    users: dict[int, UserData]

    def __init__(self) -> None:
        self.users = dict()
        self.users[1] = UserData(id=1, name="John Doe", active=True)
        self.users[2] = UserData(id=2, name="Jane Doe", active=True)

    def get_user(self, user_id: int) -> UserData | None:
        return self.users.get(user_id)


class UserService:
    def __init__(self, repository: UserRepositoryProtocol) -> None:
        self.repository = repository

    def get_user_by_id(self, user_id: int) -> UserData | None:
        return self.repository.get_user(user_id)
