import pytest

from src.authorization import required_role


class TestClass:
    @required_role("admin")
    def method(self, user, test):
        """Test method"""
        return user["name"], test


def test_authorization_plain_function():
    test_user = {"roles": ["admin"], "name": "John Doe"}

    @required_role("admin")
    def test_function(user, test):
        return user["name"], test

    assert test_function(test_user, 1) == ("John Doe", 1)


def test_authorization_class_method():
    test_user = {"roles": ["admin"], "name": "John Doe"}
    test_class = TestClass()
    assert test_class.method(test_user, 1) == ("John Doe", 1)


def test_authorization_raises_permission_error():
    test_user = {"roles": ["user"], "name": "John Doe"}
    test_class = TestClass()
    with pytest.raises(PermissionError) as e:
        test_class.method(test_user, 1)
    assert str(e.value) == "User does not have required role"

def test_authorization_preserves_metadata_for_function():
    @required_role("admin")
    def test_function(user, test):
        """Test function"""
        return user["name"], test

    assert test_function.__name__ == "test_function"
    assert test_function.__doc__ == "Test function"


def test_authorization_preserves_metadata_for_class_method():
    test_class = TestClass()
    assert test_class.method.__name__ == "method"
    assert test_class.method.__doc__ == "Test method"