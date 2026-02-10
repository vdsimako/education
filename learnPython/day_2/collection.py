# list
a = [1, 2]
a.append(3)
print(a)

fruits = ['orange', 'apple', 'pear', 'banana', 'kiwi', 'apple', 'banana']
print(f"count apple: {fruits.count('apple')}")

print(f"count tangerine: {fruits.count('tangerine')}")

print(f"index banana: {fruits.index('banana')}")

print(f"index banana from 4: {fruits.index('banana', 4)}")  # Find next banana starting at position 4

fruits.reverse()
print(fruits)

fruits.append('grape')
print(fruits)

fruits.sort()
print(fruits)

print(fruits.pop())
print(fruits)

fruits.insert(0, 'a')
print(fruits)

# tuple
b = (1, 2, 3)
print(b)

x, y, z = b
print(x, y, z)

# set
s = {1, 2, 2, 3}
print(s)

# dict
d = {"id": 1, "name": "Alex"}
print(d["id"])
try:
    print(d["age"])
except KeyError:
    print("Age not found")
