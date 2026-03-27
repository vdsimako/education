from dataclasses import dataclass


@dataclass(frozen=True)
class User:
    id: int
    name: str
    email: str
    is_active: bool = True


class UserNotFoundError(Exception):
    pass


class ExternalServiceError(Exception):
    pass
