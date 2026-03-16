from dataclasses import FrozenInstanceError
from decimal import Decimal

import pytest

from domain.money import Money


def test_money_normalizes_currency():
    money = Money(amount="10.50", currency="usd")

    assert money.currency == "USD"


def test_money_converts_amount_to_decimal():
    money = Money(amount="10.50", currency="usd")

    assert money.amount == Decimal("10.50")


def test_money_addition_with_same_currency():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="12.25", currency="usd")

    result = m1 + m2

    assert result == Money(amount="22.75", currency="usd")


def test_money_addition_with_different_currency_raises_error():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="12.25", currency="EUR")

    with pytest.raises(ValueError) as ex:
        _ = m1 + m2


def test_invalid_amount_raises_value_error():
    with pytest.raises(ValueError):
        Money(amount="asdfasd", currency="usd")


def test_invalid_currency_raises_value_error():
    with pytest.raises(ValueError):
        Money(amount="10.50", currency="us")


def test_money_immutability():
    money = Money(amount="10.50", currency="usd")

    with pytest.raises(FrozenInstanceError):
        money.currency = "EUR"


def test_money_is_positive():
    money = Money(amount="00.50", currency="usd")

    assert money.is_positive()


def test_money_is_negative():
    with pytest.raises(ValueError):
        money = Money(amount="-10.50", currency="usd")


def test_money_substraction_with_same_currency():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="2.25", currency="usd")

    result = m1 - m2

    assert result == Money(amount="8.25", currency="usd")


def test_money_substraction_with_different_currency_raise_error():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="2.25", currency="EUR")

    with pytest.raises(ValueError):
        _ = m1 - m2


def test_money_substraction_more_raise_error():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="12.25", currency="usd")

    with pytest.raises(ValueError):
        _ = m1 - m2


def test_money_substraction_with_non_money_returns_not_implemented():
    m1 = Money(amount="10.50", currency="usd")

    with pytest.raises(TypeError):
        _ = m1 - "not_money"


def test_money_addition_with_non_money_returns_not_implemented():
    m1 = Money(amount="10.50", currency="usd")

    with pytest.raises(TypeError):
        _ = m1 + "not_money"


def test_money_zero_amount_is_not_positive():
    money = Money(amount="0", currency="usd")

    assert not money.is_positive()


def test_money_currency_whitespace_normalization():
    money = Money(amount="10.50", currency="  usd  ")

    assert money.currency == "USD"


def test_money_currency_case_normalization():
    money = Money(amount="10.50", currency="usd")

    assert money.currency == "USD"


def test_money_decimal_amount_types():
    # Test with integer
    m1 = Money(amount=10, currency="USD")
    assert m1.amount == Decimal("10")

    # Test with float (should be converted)
    m2 = Money(amount=10.5, currency="USD")
    assert m2.amount == Decimal("10.5")

    # Test with Decimal
    m3 = Money(amount=Decimal("10.50"), currency="USD")
    assert m3.amount == Decimal("10.50")


def test_money_invalid_currency_non_alpha():
    with pytest.raises(ValueError, match="Currency must be a 3-letter code"):
        Money(amount="10.50", currency="123")


def test_money_invalid_currency_wrong_length():
    with pytest.raises(ValueError, match="Currency must be a 3-letter code"):
        Money(amount="10.50", currency="US")

    with pytest.raises(ValueError, match="Currency must be a 3-letter code"):
        Money(amount="10.50", currency="USDA")


def test_money_substraction_resulting_in_zero():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="10.50", currency="usd")

    result = m1 - m2

    assert result == Money(amount="0", currency="usd")
    assert not result.is_positive()


def test_money_equality():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="10.50", currency="USD")
    m3 = Money(amount="10.50", currency="eur")

    assert m1 == m2  # Same amount, currency normalized
    assert m1 != m3  # Different currency


def test_money_hashable():
    m1 = Money(amount="10.50", currency="usd")
    m2 = Money(amount="10.50", currency="USD")

    # Should be hashable and have same hash for equal objects
    assert hash(m1) == hash(m2)

    # Can be used in sets
    money_set = {m1, m2}
    assert len(money_set) == 1
