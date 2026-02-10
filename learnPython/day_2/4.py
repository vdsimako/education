matrix = [[0] * 3] * 3
print(matrix)
matrix[0][0] = 1
print(matrix)  # все строки изменятся

matrix = [[0] * 3 for _ in range(3)]
print(matrix)
matrix[0][0] = 1
print(matrix)  # только одна строка изменится

d = {"a": 1, "b": 2}
try:
    for k in d:
        del d[k]  # RuntimeError
except RuntimeError:
    print("catch RuntimeError")

for k in list(d):
    del d[k]

print(d)