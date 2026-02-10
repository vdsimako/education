import string
from collections import Counter, deque
from dataclasses import dataclass, field
from typing import TypeVar, Generic


##############################################################################################################################
# Task 1 — Word Frequency Counter (Counter)

def word_stats(text: str) -> dict[str, int]:
    t = text.translate(str.maketrans('', '', string.punctuation)).lower().split()
    return dict(Counter(t))


print(word_stats("Hello, world hello!"))

##############################################################################################################################
# Task 2 — Last N Events Queue (deque)

T = TypeVar("T")


class LastNQueue(Generic[T]):
    def __init__(self, n: int):
        if n <= 0:
            raise ValueError("n must be > 0")

        self.n = n
        self.queue = deque(maxlen=n)

    def push(self, item: T):
        self.queue.append(item)

    def items(self) -> list[T]:
        return list(self.queue)


q = LastNQueue(3)

q.push(1)
q.push(2)
q.push(3)
q.push(4)

print(q.items())  # [2, 3, 4]
q.push(5)
print(q.items())  # [3, 4, 5]


##############################################################################################################################
# Task 3 — DTO / Value Object через dataclass

@dataclass(frozen=True, slots=True)
class UserDTO:
    id: int
    email: str
    is_active: bool = True
    roles: frozenset[str] = field(default_factory=set)

    def with_added_role(self, role: str) -> "UserDTO":
        if role in self.roles:
            return self

        return UserDTO(id=self.id, email=self.email, is_active=self.is_active, roles=self.roles | {role})


user = UserDTO(id=1, email="user@example.com", roles=frozenset({"user"}))
print(user)

user_2 = user.with_added_role("admin")
print(user_2)
