class Base:
    role: str
    def __init__(self):
        super().__init__()
        self.role = "test"

    def __eq__(self, __value):
        return self.role == __value

    def __repr__(self):
        return self.role

    def __hash__(self):
        return None


b = Base()
print(b)

b1 = Base()
print(b1)

print(b == b1)

print(b is b1)

# print({b: "ok"})

