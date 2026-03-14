from dataclasses import dataclass


@dataclass(frozen=True, slots=True)
class Money:
    amount: int
    currency: str

    def __post_init__(self):
        if self.amount < 0:
            raise ValueError("amount must be non-negative")
        if len(self.currency) != 3 or not self.currency.isalpha():
            raise ValueError("currency must be a 3-letter code")
        object.__setattr__(self, "currency", self.currency.upper())

    def __add__(self, other: "Money") -> "Money":
        if not isinstance(other, Money):
            return NotImplemented
        if self.currency != other.currency:
            raise ValueError("currency mismatch")
        return Money(self.amount + other.amount, self.currency)

    def __str__(self):
        return f"{self.amount} {self.currency}"

m1 = Money(150, "usd")
m2 = Money(50, "USD")
print(m1 + m2)           # 200 USD
print({m1: "ok"})        # hashable
print(Money(10, "usd") == Money(10, "USD"))

# m1.amount = 100