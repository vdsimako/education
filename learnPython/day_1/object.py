x = 10
print(type(x))
print(x.__add__(5))

def f(): pass
print(type(f))

class A:
    pass

a = A()
print(type(a))

s = "hello"
print(type(s))
print(s.__len__())