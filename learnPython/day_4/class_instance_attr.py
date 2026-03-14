class User:
    role = "guest"

class Bad:
    tags = []


u1 = User()
u2 = User()
print(u1.role)
print(u2.role)

u1.role = "admin"

print(User.role)
print(u1.role)
print(u2.role)

b1 = Bad()
b2 = Bad()
print(b1.tags)
print(b2.tags)

b1.tags.append("admin")
print(b1.tags)
print(b2.tags)