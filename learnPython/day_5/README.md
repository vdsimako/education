# Day 5 — Exceptions & Context Managers

## Overview

Today is about two very important Python topics:

- **exceptions** — how Python represents and handles errors
- **context managers** — how Python guarantees cleanup of resources

These topics matter a lot in real backend code because production systems are not only about the happy path.
They are also about:

- handling failure clearly
- preserving debugging information
- cleaning up resources safely
- designing predictable APIs

---

## Learning goals

By the end of this day, you should be able to:

- understand Python's exception hierarchy
- use `try / except / else / finally` correctly
- catch specific exceptions instead of hiding bugs
- create your own custom exception classes
- use exception chaining with `raise ... from ...`
- understand how the `with` statement works
- implement class-based context managers
- implement generator-based context managers with `@contextmanager`
- understand when exceptions are suppressed
- use `ExitStack` for dynamic resource management

---

## Topics checklist

- [ ] Exception hierarchy
- [ ] `try / except / else / finally`
- [ ] Catching specific exceptions
- [ ] Custom exceptions
- [ ] Exception chaining (`raise from`)
- [ ] `with` statement
- [ ] `__enter__` / `__exit__`
- [ ] Exception suppression rules
- [ ] `contextlib.contextmanager`
- [ ] `ExitStack`

---

## Suggested project structure

```text
day_5/
├── README.md
├── src/
│   ├── __init__.py
│   ├── config_loader.py
│   ├── logging_context.py
│   ├── logging_context_cm.py
│   └── exitstack_examples.py
└── tests/
    ├── __init__.py
    ├── test_config_loader.py
    ├── test_logging_context.py
    ├── test_logging_context_cm.py
    └── test_exitstack_examples.py
```

---

## 1. Exception hierarchy

Exceptions in Python are classes.

At the top of the hierarchy is `BaseException`, but in normal application code you usually work with `Exception`.

### Important rule

Catch:

```python
except Exception:
```

Avoid catching:

```python
except BaseException:
```

or bare:

```python
except:
```

because `BaseException` also includes special exceptions like:

- `SystemExit`
- `KeyboardInterrupt`
- `GeneratorExit`

These are not normal business/runtime errors.

### Example

```python
print(issubclass(ValueError, Exception))         # True
print(issubclass(Exception, BaseException))      # True
print(issubclass(KeyboardInterrupt, Exception))  # False
```

### Common built-in exceptions

- `ValueError` — valid type, invalid value
- `TypeError` — operation used on wrong type
- `KeyError` — missing dictionary key
- `IndexError` — invalid list index
- `FileNotFoundError` — missing file
- `ZeroDivisionError` — division by zero
- `RuntimeError` — generic runtime issue
- `NotImplementedError` — method intentionally not implemented

---

## 2. `try / except / else / finally`

This is the core error-handling structure in Python.

### Basic form

```python
try:
    ...
except SomeError:
    ...
else:
    ...
finally:
    ...
```

### Meaning

- `try` — code that may fail
- `except` — runs when a matching exception happens
- `else` — runs only if no exception happened
- `finally` — runs always

### Example

```python
def divide(a, b):
    try:
        result = a / b
    except ZeroDivisionError:
        print("Cannot divide by zero")
    else:
        print("Success:", result)
    finally:
        print("Done")
```

### Important detail

`finally` runs even if the block contains:

- `return`
- `break`
- `continue`
- another exception

Example:

```python
def demo():
    try:
        return 10
    finally:
        print("finally runs before function exits")

print(demo())
```

---

## 3. Catching specific exceptions

Always catch the most specific exception you can.

### Good

```python
try:
    number = int(user_input)
except ValueError:
    print("Please enter a valid integer")
```

### Too broad

```python
try:
    number = int(user_input)
except Exception:
    print("Something went wrong")
```

The second version may hide unrelated bugs.

### Multiple specific exceptions

```python
try:
    value = int(data["age"])
except KeyError:
    print("Missing 'age'")
except ValueError:
    print("'age' must be an integer")
```

### Grouping exceptions

```python
try:
    ...
except (TypeError, ValueError) as e:
    print("Invalid input:", e)
```

### Order matters

More specific exceptions must go first.

```python
try:
    ...
except Exception:
    ...
except ValueError:
    ...  # unreachable
```

That is incorrect.

---

## 4. Custom exceptions

In real applications, built-in exceptions are often too generic.
Custom exceptions let you express domain meaning.

### Example

```python
class AppError(Exception):
    """Base exception for the application."""


class ConfigError(AppError):
    """Base config-related error."""


class ConfigFileError(ConfigError):
    """Config file cannot be read."""


class ConfigParseError(ConfigError):
    """Config format is invalid."""


class MissingConfigKeyError(ConfigError):
    """Required config key is missing."""


class InvalidConfigValueError(ConfigError):
    """Config key has invalid value."""
```

### Why this is useful

You can catch errors at different levels:

```python
except MissingConfigKeyError:
    ...
except ConfigError:
    ...
except AppError:
    ...
```

This makes your code more flexible and easier to maintain.

---

## 5. Exception chaining (`raise from`)

Sometimes you want to convert a low-level exception into a more meaningful domain exception.

### Without chaining

```python
def parse_port(raw_value):
    try:
        return int(raw_value)
    except ValueError:
        raise ConfigError("Invalid port")
```

This loses the original cause.

### With chaining

```python
def parse_port(raw_value):
    try:
        return int(raw_value)
    except ValueError as e:
        raise ConfigError("Invalid port") from e
```

Now the traceback shows:

- original low-level exception
- new domain-level exception

### When to use `raise from`

Use it when:

- you wrap low-level errors into domain-specific ones
- you want cleaner public APIs
- you still want full debugging context

### Rare case: suppressing the original cause

```python
raise ConfigError("Invalid config") from None
```

This hides the original cause in traceback output. Use it rarely.

---

## 6. `with` statement

The `with` statement is used for safe setup and cleanup.

### Classic example

```python
with open("data.txt", "r", encoding="utf-8") as f:
    content = f.read()
```

Equivalent manual version:

```python
f = open("data.txt", "r", encoding="utf-8")
try:
    content = f.read()
finally:
    f.close()
```

### Why `with` is better

- cleaner syntax
- automatic cleanup
- safe behavior when exceptions happen

### Common use cases

- files
- locks
- database sessions
- transactions
- temporary environment changes
- logging scopes

---

## 7. `__enter__` / `__exit__`

A context manager is an object that implements:

- `__enter__(self)`
- `__exit__(self, exc_type, exc, tb)`

### Example

```python
class SimpleContext:
    def __enter__(self):
        print("Entering")
        return self

    def __exit__(self, exc_type, exc, tb):
        print("Exiting")
```

### Usage

```python
with SimpleContext():
    print("Inside block")
```

### What happens conceptually

```python
manager = SimpleContext()
value = manager.__enter__()
try:
    ...
finally:
    manager.__exit__(...)
```

### `__exit__` arguments

If no exception happened:

- `exc_type is None`
- `exc is None`
- `tb is None`

If an exception happened:

- `exc_type` is the exception class
- `exc` is the exception instance
- `tb` is the traceback object

---

## 8. Exception suppression rules

This is a very common interview topic.

### Rule

If `__exit__()` returns:

- `True` → exception is suppressed
- `False` or `None` → exception continues propagating

### Example

```python
class SuppressValueError:
    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc, tb):
        return exc_type is ValueError
```

### Usage

```python
with SuppressValueError():
    int("abc")  # ValueError is suppressed
```

But this will still propagate:

```python
with SuppressValueError():
    1 / 0
```

### Best practice

Only suppress exceptions intentionally. Accidental suppression makes debugging much harder.

---

## 9. `contextlib.contextmanager`

Python provides a simpler way to write context managers.

### Example

```python
from contextlib import contextmanager


@contextmanager
def log_block(name):
    print(f"[START] {name}")
    try:
        yield
    finally:
        print(f"[END] {name}")
```

### Usage

```python
with log_block("import-users"):
    print("Working...")
```

### How it works

- code before `yield` = enter logic
- `yield` = value passed to the `as ...` variable
- code after `yield` = exit logic

### Returning a value

```python
from contextlib import contextmanager


@contextmanager
def named_block(name):
    print(f"Open: {name}")
    try:
        yield name
    finally:
        print(f"Close: {name}")
```

Usage:

```python
with named_block("task") as block_name:
    print(block_name)
```

---

## 10. `ExitStack`

`ExitStack` is useful when the number of context managers is dynamic.

### Problem

This works when the number of files is fixed:

```python
with open("a.txt") as a, open("b.txt") as b:
    ...
```

But what if the list of files is dynamic?

### Solution

```python
from contextlib import ExitStack

files = ["a.txt", "b.txt", "c.txt"]

with ExitStack() as stack:
    opened_files = [stack.enter_context(open(name, "r", encoding="utf-8")) for name in files]
    for f in opened_files:
        print(f.read())
```

### Why `ExitStack` is useful

- dynamic number of resources
- conditional resource acquisition
- combining context managers with manual cleanup callbacks

### Callback example

```python
from contextlib import ExitStack


def cleanup():
    print("cleanup called")


with ExitStack() as stack:
    stack.callback(cleanup)
    print("working")
```

---

## Practice

> Important: below you will find task definitions only.
> No solutions are provided here on purpose.

### Task 1 — Config loader with custom exceptions

#### Goal

Build a configuration loader that:

- reads JSON from a file
- validates required keys
- raises custom exceptions instead of raw built-in ones

#### Requirements

Required config keys:

- `host`
- `port`
- `debug`

Validation rules:

- `host` must be a non-empty string
- `port` must be an integer in range `1..65535`
- `debug` must be a boolean

Create these exceptions:

- `ConfigError`
- `ConfigFileError`
- `ConfigParseError`
- `MissingConfigKeyError`
- `InvalidConfigValueError`

#### Suggested file

```text
src/config_loader.py
```

#### Step-by-step instructions

1. Create a base exception class `ConfigError`.
2. Create the four specific config-related exception classes.
3. Implement a function:

   ```python
   def load_config(path: str) -> dict:
       ...
   ```

4. Open the file using `with open(...)`.
5. If the file does not exist, convert the low-level exception into `ConfigFileError`.
6. Parse JSON using `json.load(...)`.
7. If JSON is invalid, convert the parsing error into `ConfigParseError`.
8. Check that all required keys exist.
9. If a key is missing, raise `MissingConfigKeyError`.
10. Validate each value.
11. If a value is invalid, raise `InvalidConfigValueError`.
12. Return the validated dictionary.
13. Use `raise ... from ...` where wrapping low-level exceptions makes sense.

#### Unit test points

Your tests should check:

- loading a valid config returns a dictionary
- missing file raises `ConfigFileError`
- invalid JSON raises `ConfigParseError`
- missing `host` raises `MissingConfigKeyError`
- missing `port` raises `MissingConfigKeyError`
- missing `debug` raises `MissingConfigKeyError`
- empty host raises `InvalidConfigValueError`
- non-integer port raises `InvalidConfigValueError`
- port outside valid range raises `InvalidConfigValueError`
- non-boolean debug raises `InvalidConfigValueError`
- wrapped exceptions preserve the original cause where expected

#### Hints for tests

Use:

- `tmp_path`
- `pytest.raises(...)`
- small helper functions to write test config files

### Task 2 — Logging context manager (class-based)

#### Goal

Create a class-based context manager for logging execution of a block.

#### Requirements

Behavior:

- print a start message on entering the context
- print a success message if the block finishes without exception
- print an error message if the block fails
- print a final end message in all cases
- do not suppress exceptions

Example idea:

```python
with LoggingContext("job-1"):
    ...
```

#### Suggested file

```text
src/logging_context.py
```

#### Step-by-step instructions

1. Create a class `LoggingContext`.
2. Add `__init__(self, name: str)` to store the operation name.
3. Implement `__enter__`.
4. In `__enter__`, print a start message.
5. Return `self` from `__enter__`.
6. Implement `__exit__(self, exc_type, exc, tb)`.
7. If `exc_type is None`, print a success message.
8. Otherwise, print an error message with exception info.
9. Print an end message regardless of success or failure.
10. Return `False` from `__exit__` so exceptions are not suppressed.

#### Unit test points

Your tests should check:

- `__enter__` returns the context object
- start message is printed
- success message is printed when no exception occurs
- error message is printed when an exception occurs
- end message is always printed
- exception is not suppressed
- printed messages include the context name

#### Hints for tests

Use:

- `capsys` to capture printed output
- `pytest.raises(...)` to confirm propagation of exceptions

### Task 3 — Same logging context manager via `@contextmanager`

#### Goal

Implement the same behavior as Task 2, but using `contextlib.contextmanager`.

#### Requirements

Behavior should match the class-based version:

- print start on enter
- print success if the block succeeds
- print error if the block fails
- print end in all cases
- do not suppress exceptions

#### Suggested file

```text
src/logging_context_cm.py
```

#### Step-by-step instructions

1. Import `contextmanager` from `contextlib`.
2. Create a function-based context manager, for example:

   ```python
   @contextmanager
   def logging_context(name: str):
       ...
   ```

3. Print a start message before `yield`.
4. Use `yield` to hand control to the `with` block.
5. Use `try / except / else / finally` around `yield`.
6. In `else`, print a success message.
7. In `except`, print an error message.
8. Re-raise the exception after logging it.
9. In `finally`, print the end message.

#### Unit test points

Your tests should check:

- start message is printed
- success message is printed on normal completion
- error message is printed on failure
- end message is printed in both success and failure paths
- raised exception is not suppressed
- output format is consistent with task expectations

#### Hints for tests

Use:

- `capsys`
- `pytest.raises(...)`

### Task 4 — Dynamic file reader with `ExitStack`

#### Goal

Practice dynamic resource management using `ExitStack`.

#### Requirements

Write a function that:

- accepts a list of file paths
- opens all files safely
- reads their content
- returns a list of strings

Suggested function:

```python
def read_all_files(paths: list[str]) -> list[str]:
    ...
```

#### Suggested file

```text
src/exitstack_examples.py
```

#### Step-by-step instructions

1. Import `ExitStack` from `contextlib`.
2. Create the `read_all_files(...)` function.
3. Create an `ExitStack` inside a `with` block.
4. For each path, use `stack.enter_context(...)` to open the file.
5. Store opened file objects in a list.
6. Read all file contents.
7. Return the list of contents.
8. Keep the implementation simple and explicit.

#### Unit test points

Your tests should check:

- reading one file returns a one-item list
- reading multiple files returns content in correct order
- empty input list returns an empty list
- missing file raises an exception
- function works with dynamically created temp files

#### Hints for tests

Use:

- `tmp_path`
- several small temp files with known content

---

## Recommended test commands

Run all tests:

```bash
pytest
```

Run a specific test file:

```bash
pytest tests/test_config_loader.py
```

Run with verbose output:

```bash
pytest -v
```

---

## Interview questions

### Why is `except Exception` better than bare `except:`?

Because bare `except:` catches almost everything, including `KeyboardInterrupt` and `SystemExit`.
That can make your program hard to interrupt and harder to debug.

### What is the purpose of `else` in `try/except`?

It separates the success path from error handling.
`else` runs only if the `try` block completes without exception.

### What does `finally` guarantee?

It runs regardless of success or failure, even when there is `return`, `break`, `continue`, or an exception.

### Why create custom exceptions?

To express business/domain meaning clearly and make error handling more precise.

### Why use `raise ... from ...`?

To preserve the original cause when converting low-level exceptions into higher-level domain exceptions.

### How does `with` work internally?

Python calls `__enter__()` before the block and `__exit__()` after the block.
If an exception happens, it is passed into `__exit__`.

### When is an exception suppressed in a context manager?

When `__exit__()` returns `True`.

### When should you use `@contextmanager` vs a class-based context manager?

- use `@contextmanager` for simple, compact context managers
- use a class when you need more structure, state, methods, or reuse

### What problem does `ExitStack` solve?

It helps manage a dynamic number of resources when you do not know the number of context managers ahead of time.

---

## Mini checklist

- [ ] I understand the difference between `BaseException` and `Exception`
- [ ] I can explain `try / except / else / finally`
- [ ] I can catch specific exceptions instead of broad ones
- [ ] I can create a custom exception hierarchy
- [ ] I know when to use `raise from`
- [ ] I can write a class-based context manager
- [ ] I can write a generator-based context manager
- [ ] I understand exception suppression
- [ ] I know why `ExitStack` exists

---

## Homework

### Easy

1. Write a function that converts input to `int` and handles `ValueError`.
2. Create your own `ValidationError`.
3. Rewrite manual `open()` / `close()` code using `with`.

### Medium

1. Extend the config loader with optional keys.
2. Add timestamps to the logging context manager.
3. Create a context manager that temporarily changes the current working directory.

### Hard

1. Build a transaction-like context manager with `BEGIN`, `COMMIT`, `ROLLBACK`.
2. Use `ExitStack` to open all `.txt` files in a directory.
3. Implement a context manager that suppresses only selected exception types.

---

## Key takeaway

Exceptions are not only about reacting to errors.

They are about:

- designing failure paths clearly
- preserving debugging context
- separating low-level failures from domain logic
- keeping cleanup reliable and predictable

Context managers are Python’s clean way to say:

> set up resource → run block → always clean up safely

That pattern appears everywhere in real production code.
