def make_counter(start=0, step=1):
    value = start

    def next_value():
        nonlocal value
        value += step
        return value

    return next_value


c = make_counter()
assert c() == 1
assert c() == 2

c2 = make_counter(start=10, step=5)
assert c2() == 15
assert c2() == 20
