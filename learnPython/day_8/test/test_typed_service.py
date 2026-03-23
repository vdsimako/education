from src.typed_service import UserService, UserData, UserRepository


class FakeRepository:
    def get_user(self, user_id: int) -> UserData | None:
        if user_id != 1:
            return None

        return UserData(id=user_id, name="John Doe", active=True)


def test_repository_returns_existing_user():
    repository = UserRepository()
    user = repository.get_user(1)
    assert user == UserData(id=1, name="John Doe", active=True)
    assert repository.get_user(2) == UserData(id=2, name="Jane Doe", active=True)


def test_repository_returns_none_for_missing_user():
    repository = UserRepository()
    user = repository.get_user(3)
    assert user is None


def test_service_returns_existing_user():
    repository = UserRepository()
    service = UserService(repository)
    user = service.get_user_by_id(1)
    assert user == UserData(id=1, name="John Doe", active=True)
    assert service.get_user_by_id(2) == UserData(id=2, name="Jane Doe", active=True)


def test_service_returns_none_for_missing_user():
    repository = UserRepository()
    service = UserService(repository)
    user = service.get_user_by_id(3)
    assert user is None


def test_service_accepts_protocol_compatible_repository():
    class Repository:
        def get_user(self, user_id: int) -> UserData | None:
            return None

    repository = Repository()
    service = UserService(repository)
    user = service.get_user_by_id(1)
    assert user is None


def test_service_works_with_fake_repository():
    fake_service = UserService(FakeRepository())
    user = fake_service.get_user_by_id(1)
    assert user == UserData(id=1, name="John Doe", active=True)


def test_service_handles_fake_repository_returning_none():
    fake_service = UserService(FakeRepository())
    user = fake_service.get_user_by_id(3)
    assert user is None
