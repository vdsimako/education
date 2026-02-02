from typing import Protocol


class Animal(Protocol):
    def feed(self) -> None:
        pass


class Duck:
    def feed(self) -> None:
        print("Duck eats")


class Dog:
    def bark(self) -> None:
        print("Dog barks")

    def feed(self) -> None:
        print("Dog eats")


def feed(animal: Animal) -> None:
    animal.feed()


duck = Duck()
feed(duck)

dog = Dog()
feed(dog)
