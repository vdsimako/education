class MyInt(int):
    def __new__(cls, value):
        print("MyInt.__new__")
        return int.__new__(cls, value)
        return super().__new__(cls, int(value))

    def __init__(self, value):
        print("MyInt.__init__")
        # called after __new__, but int is already created
        pass


i1 = MyInt(1)
i2 = MyInt(1)

print(i1)
print(i2)
print(i1 == i2)
print(i1 is i2)