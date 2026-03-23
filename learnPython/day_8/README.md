# Day 8 — Typing & Static Analysis

## Topics

- [ ] Type hints basics
- [ ] `Optional`, `Union`
- [ ] `TypedDict`
- [ ] `Protocol`
- [ ] `Callable`
- [ ] `mypy`

## Learning goals

By the end of this day, you should be able to:

- add basic type hints to variables, function arguments, and return values
- describe the difference between `Optional[T]` and an optional function argument
- model structured dictionary data with `TypedDict`
- define behavior-based interfaces with `Protocol`
- type functions that accept other functions with `Callable`
- run `mypy` and understand common type-checking errors

## Suggested structure

```text
day_8/
├── README.md
├── src/
│   ├── typed_service.py
│   ├── protocol_vs_abc.py
│   └── callable_examples.py
└── tests/
    ├── test_typed_service.py
    ├── test_protocol_vs_abc.py
    └── test_callable_examples.py
```

## Practice

### 1. Typed service interface

Build a tiny user service.

Requirements:

1. Create a `TypedDict` named `UserData`
2. Create a `Protocol` named `UserRepositoryProtocol`
3. Implement an in-memory repository
4. Create a service function that returns a user name by id
5. Return `None` when a user is missing
6. Run `mypy` and fix all warnings

### 2. Protocol vs ABC comparison

Implement the same idea in two styles:

- version A: abstract base class (`ABC`)
- version B: protocol (`Protocol`)

Questions to answer:

- which one is stricter?
- which one is more flexible?
- which one is easier to test?
- when would you choose one over the other?

### 3. Callable practice

Create a function that accepts another function as an argument.

Requirements:

1. Accept an integer input
2. Accept a callable transformation
3. Apply the callable
4. Return the transformed result

## How to run static analysis

Install mypy:

```bash
pip install mypy
```

Run it for the whole package:

```bash
mypy src
```

## Interview questions

1. What problem do type hints solve in Python?
2. What is the difference between `Union[T1, T2]` and `Optional[T]`?
3. When is `TypedDict` better than `dict[str, object]`?
4. What is structural typing?
5. What is the difference between `Protocol` and `ABC`?
6. What are the limitations of `mypy`?

## Notes

- Type hints improve readability and tooling, but they do not enforce runtime validation by default.
- `Optional[T]` means `T | None`.
- `Protocol` checks behavior compatibility, not inheritance.
- `Callable[[A], B]` means “a function that takes `A` and returns `B`”.
