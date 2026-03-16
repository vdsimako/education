from dataclasses import dataclass
from decimal import Decimal, InvalidOperation


@dataclass(frozen=True)
class Money:
    amount: Decimal
    currency: str

    def __post_init__(self):
        try:
            normalized_amount = Decimal(self.amount)
        except (InvalidOperation, ValueError, TypeError):
            raise ValueError("Invalid amount")

        if  normalized_amount < 0:
            raise ValueError("Amount must be positive")

        normalized_currency = self.currency.strip().upper()

        if len(normalized_currency) != 3 or not normalized_currency.isalpha():
            raise ValueError("Currency must be a 3-letter code")

        object.__setattr__(self, "amount", normalized_amount)
        object.__setattr__(self, "currency", normalized_currency)

    def __add__(self, other):
        if not isinstance(other, Money):
            return NotImplemented
        if self.currency != other.currency:
            raise ValueError("Cannot add money with different currencies")
        return Money(self.amount + other.amount, self.currency)

    def __sub__(self, other):
        if not isinstance(other, Money):
            return NotImplemented
        if self.currency != other.currency:
            raise ValueError("Cannot subtract money with different currencies")
        if self.amount < other.amount:
            raise ValueError("Cannot subtract more money than available")

        return Money(self.amount - other.amount, self.currency)

    def is_positive(self):
        return self.amount > 0
