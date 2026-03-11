funcs = []
for i in range(5):
    funcs.append(lambda: i)

# ожидаем 0,1,2,3,4
print([f() for f in funcs])

funcs = []

for i in range(5):
    funcs.append(lambda i=i: i)

print([f() for f in funcs])


funcs = []

def make(i):
    return lambda i=i: i


for i in range(5):
    funcs.append(make(i))

print([f() for f in funcs])