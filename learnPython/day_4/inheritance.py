class Animal:
    name: str

    def __init__(self, name):
        self.name = name

    def speak(self):
        return "..."

class Dog(Animal):

    def __init__(self, name):
        super().__init__(name)

    def speak(self):
        return "woof"


a = Animal("a")
print(a.speak())

dog = Dog("d")
print(dog.speak())
print(dog.name)
dog = Animal("asd")
print(dog.speak())

print(dog.name)