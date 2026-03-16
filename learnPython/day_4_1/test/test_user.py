from domain.email import Email
from domain.user import User


def test_user_equality_is_based_on_id():
    user1 = User(email=Email('test@example.com'), user_id=1, name="user1")
    user2 = User(email=Email('test1@example.com'), user_id=1, name="user2")

    assert user1 == user2


def test_users_with_different_ids_are_not_equal():
    u1 = User(user_id=1, name="user1", email=Email("test@example.com"))
    u2 = User(user_id=2, name="user1", email=Email("test@example.com"))

    assert u1 != u2


def test_user_can_change_email():
    u1 = User(user_id=1, name="user1", email=Email("test@example.com"))

    u1.change_email(Email("new@example.com"))

    assert u1.email == Email("new@example.com")
