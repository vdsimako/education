# 🎭 Day 7 — Decorators

## Goal

Learn how Python decorators work from the ground up and practice building reusable wrappers around functions and methods.

By the end of this day, you should be able to:

- explain what a decorator is
- write a basic function decorator
- write a decorator with arguments
- explain why `functools.wraps` matters
- use closures inside decorators
- decorate instance methods correctly
- implement real-world decorators such as timing, retry, and authorization

---

## Topics

- [ ] Function decorators
- [ ] Decorators with arguments
- [ ] `functools.wraps`
- [ ] Decorators + closures
- [ ] Method decorators

---

## Practice

- [ ] Timing decorator
- [ ] Retry with backoff
- [ ] Authorization decorator

---

## Suggested project structure

```text
day_7_decorators/
├── README.md
├── src/
│   └── day7_decorators/
│       ├── __init__.py
│       ├── timing.py
│       ├── retry.py
│       └── authorization.py
└── tests/
    ├── test_timing.py
    ├── test_retry.py
    └── test_authorization.py
```

---

## What is a decorator?

A decorator is a function that takes another function and returns a new function.

It lets you add behavior:

- before a function call
- after a function call
- around a function call

without changing the original function body.

Example idea:

```python
def log_calls(func):
    def wrapper(*args, **kwargs):
        print(f"Calling {func.__name__}")
        return func(*args, **kwargs)
    return wrapper
```

Usage:

```python
@log_calls
def add(a, b):
    return a + b
```

This is the same as:

```python
def add(a, b):
    return a + b

add = log_calls(add)
```

---

## Why `*args` and `**kwargs` are used

A decorator should usually work with many different function signatures.

Bad example:

```python
def my_decorator(func):
    def wrapper():
        return func()
    return wrapper
```

This only works for functions without arguments.

Better:

```python
def my_decorator(func):
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
    return wrapper
```

This version works for most normal functions and methods.

---

## Decorators and closures

Decorators are usually based on closures.

A closure means an inner function remembers variables from the outer function.

Example:

```python
def repeat(n):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for _ in range(n):
                result = func(*args, **kwargs)
            return result
        return wrapper
    return decorator
```

Here, `wrapper` remembers `n` even after `repeat(n)` has finished.

---

## Decorators with arguments

A decorator with arguments has one extra level of nesting.

Pattern:

```python
def decorator_name(option):
    def decorator(func):
        def wrapper(*args, **kwargs):
            print(option)
            return func(*args, **kwargs)
        return wrapper
    return decorator
```

---

## Why `functools.wraps` matters

Without `wraps`, the decorated function loses metadata.

Example problem:

```python
print(my_function.__name__)
print(my_function.__doc__)
```

Without `@wraps(func)`, you may get wrapper metadata instead of the original function metadata.

Correct pattern:

```python
from functools import wraps


def my_decorator(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
    return wrapper
```

Use `@wraps(func)` almost every time you write a decorator.

---

## Method decorators

Decorators also work on instance methods.

Important point:

For methods, `self` is just the first positional argument.

Example:

```python
from functools import wraps


def log_method(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        print(f"Calling {func.__name__}")
        return func(*args, **kwargs)
    return wrapper


class Calculator:
    @log_method
    def multiply(self, a, b):
        return a * b
```

---

## Practice tasks

### 1. Timing decorator

Implement `timing(func)` in `src/day7_decorators/timing.py`.

Requirements:

- measure execution time with `time.perf_counter()`
- call the original function
- return the original result
- preserve metadata with `functools.wraps`
- print something like: `slow_task took 0.123456s`

Suggested steps:

1. Import `time` and `wraps`
2. Define `timing(func)`
3. Create `wrapper(*args, **kwargs)`
4. Save start time before the call
5. Call the original function
6. Save end time after the call
7. Print the elapsed time
8. Return the result

---

### 2. Retry with backoff

Implement `retry(max_attempts=3, delay=0.1, backoff=2.0, exceptions=(Exception,))` in `src/day7_decorators/retry.py`.

Requirements:

- retry the function if it raises one of the allowed exception types
- stop retrying after `max_attempts`
- wait `delay` seconds before retrying
- multiply the delay by `backoff` after each failed attempt
- re-raise the last exception if all attempts fail
- preserve metadata with `functools.wraps`

Suggested steps:

1. Create the outer function with decorator arguments
2. Return an inner `decorator(func)`
3. Add `wrapper(*args, **kwargs)`
4. Keep a `current_delay` variable
5. Loop over attempts
6. Use `try/except`
7. Sleep between retries with `time.sleep(current_delay)`
8. Increase delay after each failure
9. Re-raise the final exception

---

### 3. Authorization decorator

Implement `require_role(role)` in `src/day7_decorators/authorization.py`.

Requirements:

- work with both plain functions and instance methods
- expect a `user` argument that is a dictionary with a `roles` list
- check whether the required role exists in `user["roles"]`
- raise `PermissionError` when access is denied
- call the original function when access is allowed
- preserve metadata with `functools.wraps`

Suggested design for practice:

- for plain functions, use signature like `func(user, ...)`
- for methods, use signature like `method(self, user, ...)`

Suggested steps:

1. Create `require_role(role)`
2. Return `decorator(func)`
3. Add `wrapper(*args, **kwargs)`
4. Detect whether the first argument is `self` or `user`
5. Extract the `user` object
6. Check roles safely
7. Raise `PermissionError` on failure
8. Call the original function on success

---

## Test checklist

### `test_timing.py`

You should test that:

- [ ] the decorator returns the original function result
- [ ] function metadata is preserved
- [ ] execution info is printed
- [ ] the decorator works with positional and keyword arguments

### `test_retry.py`

You should test that:

- [ ] a flaky function succeeds after retrying
- [ ] the decorator stops after the success case
- [ ] the final exception is raised after all attempts fail
- [ ] backoff calls `time.sleep()` with increasing delays
- [ ] function metadata is preserved

### `test_authorization.py`

You should test that:

- [ ] access is allowed for a user with the required role
- [ ] `PermissionError` is raised when role is missing
- [ ] metadata is preserved
- [ ] the decorator works for plain functions
- [ ] the decorator works for instance methods

---

## Common mistakes

### 1. Forgetting to return the wrapper

```python
def decorator(func):
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
```

This is wrong because `decorator` must return `wrapper`.

### 2. Forgetting to return the original result

```python
def decorator(func):
    def wrapper(*args, **kwargs):
        func(*args, **kwargs)
    return wrapper
```

This returns `None` instead of the original result.

### 3. Not using `@wraps`

This breaks function metadata.

### 4. Writing a retry decorator that silently hides errors

Do not swallow exceptions without re-raising them.

### 5. Hard-coding the wrapper signature too narrowly

Decorators are much more reusable with `*args` and `**kwargs`.

---

## Interview questions

### What is a decorator in Python?

A decorator is a callable that takes a function or class and returns a modified callable, usually to add reusable behavior around execution.

### Why are decorators related to closures?

Because the wrapper function often remembers variables from the outer scope, such as the original function or configuration arguments.

### Why do we use `functools.wraps`?

To preserve the original function's metadata like `__name__`, `__doc__`, and module information.

### How does a decorator with arguments work?

It adds one extra outer function that receives decorator configuration and returns the real decorator.

### How do decorators work with methods?

The instance `self` is passed as the first positional argument, so method decorators usually work with `*args, **kwargs`.

### In what order are multiple decorators applied?

Bottom to top.

```python
@A
@B
def f():
    pass
```

means:

```python
f = A(B(f))
```

---

## Run locally

Create a virtual environment and run tests:

```bash
python -m venv .venv
source .venv/bin/activate
pip install pytest
pytest -v
```

If you want imports like `from day7_decorators.timing import timing` to work, run pytest from the project root.

---

## Homework

### Easy

Write `@uppercase_result` that converts a string return value to uppercase.

### Medium

Write `@repeat(n)` that runs the function `n` times.

### Medium+

Write `@log_args` that prints all arguments passed to the function.

### Hard

Extend `retry` so it accepts a tuple of exception classes.

### Hard+

Make `require_role` work with both plain functions and class methods cleanly.

---

## Done checklist

- [ ] I understand what a decorator is
- [ ] I can write a basic wrapper
- [ ] I can explain why closures are used
- [ ] I know how decorator arguments work
- [ ] I know why `functools.wraps` is important
- [ ] I can decorate methods
- [ ] I can implement timing, retry, and authorization decorators
