import pytest

from domain.email import Email


def test_email_normalizes_value():
    email = Email("  TEST@Example.com  ")
    assert email.value == "test@example.com"


def test_emails_with_same_value_are_equal():
    e1 = Email("TEST@example.com")
    e2 = Email("test@example.com")
    assert e1 == e2


def test_invalid_email_raises_value_error():
    with pytest.raises(ValueError):
        Email("not-an-email")