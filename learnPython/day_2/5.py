from dataclasses import dataclass, FrozenInstanceError


##############################################################################################################################
# 5.1
@dataclass
class User:
    id: int
    email: str
    is_active: bool = True

user = User(id=1, email="user@example.com")
print(user)

user_2 = User(id=1, email="user@example.com", is_active=False)
print(user_2)

##############################################################################################################################
# 5.2

from dataclasses import dataclass, field

@dataclass
class User:
    # roles: list = [] # Mutable default value [] is not allowed. Use 'field(default_factory=list)' instead.
    roles: list = field(default_factory=list)

user = User()
user.roles.append("admin")
print(user)

##############################################################################################################################
# 5.3
from dataclasses import dataclass

@dataclass(frozen=True)
class Point:
    x: int
    y: int

p = Point(1, 2)
print(p)

try:
    p.x = 3
except FrozenInstanceError:
    print("FrozenInstanceError")

##############################################################################################################################
# 5.4
from dataclasses import dataclass

@dataclass(slots=True)
class User:
    id: int
    email: str

user = User(id=1, email="user@example.com")
print(user)
print(user.__slots__)

