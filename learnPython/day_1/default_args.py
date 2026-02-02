def add(x, lst=[]):
    lst.append(x)
    return lst


def append(x, lst=None):
    if lst is None:
        lst = []
    lst.append(x)
    return lst


add(1)
add(2)

print(add(3))

print(append(1))
print(append(2))
