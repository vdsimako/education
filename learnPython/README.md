# Python Study Plan

## Structure

- day_x - test task for learning programming on Python
- [leetCode](leetCode) - tasks from LeetCode for practicing

## Goals:

- Prepare for Python backend interview as Middle+/Senior dev
- Understand Python as production language
- Create pet-project for CV/Github

---

## 🧱 Day 1 — Python Basics & Philosophy

- [ ] Zen of Python (PEP 20)
- [ ] Everything is an object
- [ ] Duck typing
- [ ] Truthy / Falsy values
- [ ] `None`, `is` vs `==`
- [ ] Mutable vs immutable types
- [ ] Default arguments pitfall
- [ ] Import execution model
- [ ] `if __name__ == "__main__"`

Practice:

- [ ] Explain mutable default args bug
- [ ] Explain `if data:` vs `if data is None`

---

## 📦 Day 2 — Collections & Dataclasses

- [ ] list / tuple / set / dict
- [ ] Time complexity basics
- [ ] `collections`: defaultdict, Counter, deque
- [ ] Common collection pitfalls
- [ ] `@dataclass`
- [ ] `field(default_factory=...)`
- [ ] `frozen=True`
- [ ] `slots=True`

Practice:

- [ ] Word frequency counter (`Counter`)
- [ ] Bounded queue (`deque`)
- [ ] Immutable DTO with dataclass

---

## 🔧 Day 3 — Functions, Args & Closures

- [ ] Positional vs keyword arguments
- [ ] `*args` / `**kwargs`
- [ ] Keyword-only arguments
- [ ] Default arguments evaluation time
- [ ] LEGB rule
- [ ] `global` vs `nonlocal`
- [ ] Closures
- [ ] Late binding problem
- [ ] Lambda limitations

Practice:

- [ ] Closure-based counter
- [ ] Fix late binding bug
- [ ] Design keyword-only API

---

## 🧠 Day 4 — OOP in Python

- [ ] Class vs instance attributes
- [ ] `__init__` vs `__new__`
- [ ] Inheritance basics
- [ ] `super()` mechanics
- [ ] Multiple inheritance
- [ ] MRO (C3 linearization)
- [ ] Mixins
- [ ] Magic methods (`__str__`, `__repr__`, `__eq__`, `__hash__`)
- [ ] `__slots__`

Practice:

- [ ] TimestampMixin
- [ ] MRO execution tracing
- [ ] Immutable Money value object

---

## 🧩 Day 4.1 — Value Objects vs Entities (DDD)

- [ ] Entity definition and lifecycle
- [ ] Value Object definition
- [ ] Equality vs identity
- [ ] Immutability benefits
- [ ] Entities containing Value Objects

Practice:

- [ ] User (Entity) with Email (Value Object)
- [ ] Money with Decimal and currency checks

---

## 🚨 Day 5 — Exceptions & Context Managers

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

Practice:

- [ ] Config loader with custom exceptions
- [ ] Logging context manager (class-based)
- [ ] Same context manager via `@contextmanager`

---

## 🔁 Day 6 — Iterators & Generators

- [ ] Iterable vs iterator
- [ ] `__iter__` / `__next__`
- [ ] Generator functions
- [ ] `yield` vs `return`
- [ ] `yield from`
- [ ] Generator expressions
- [ ] Memory efficiency

Practice:

- [ ] Custom range iterator
- [ ] Streaming file reader
- [ ] Generator pipeline

---

## 🎭 Day 7 — Decorators

- [ ] Function decorators
- [ ] Decorators with arguments
- [ ] `functools.wraps`
- [ ] Decorators + closures
- [ ] Method decorators

Practice:

- [ ] Timing decorator
- [ ] Retry with backoff
- [ ] Authorization decorator

---

## ⚙️ Day 8 — Typing & Static Analysis

- [ ] Type hints basics
- [ ] `Optional`, `Union`
- [ ] `TypedDict`
- [ ] `Protocol`
- [ ] `Callable`
- [ ] `mypy`

Practice:

- [ ] Typed service interface
- [ ] Protocol vs ABC comparison

---

## 🔄 Day 9 — Async Python

- [ ] `async` / `await`
- [ ] Event loop
- [ ] `asyncio.gather`
- [ ] Async context managers
- [ ] Async generators
- [ ] Common async pitfalls

Practice:

- [ ] Async rate limiter
- [ ] Concurrent HTTP calls

---

## 🧵 Day 10 — Concurrency

- [ ] GIL
- [ ] threading
- [ ] multiprocessing
- [ ] CPU-bound vs IO-bound
- [ ] When async is better

Practice:

- [ ] Thread-safe counter
- [ ] Multiprocessing worker pool

---

## 🧪 Day 11 — Testing

- [ ] pytest basics
- [ ] Fixtures
- [ ] Parametrization
- [ ] Mocking
- [ ] Testing async code

Practice:

- [ ] Test service layer
- [ ] Mock external dependency

---

## 🌐 Day 12 — Backend Stack (FastAPI)

- [ ] FastAPI basics
- [ ] Dependency injection
- [ ] Validation (pydantic)
- [ ] Error handlers
- [ ] Background tasks
- [ ] OpenAPI

Practice:

- [ ] CRUD API
- [ ] Auth-protected endpoint

---

## 🗄 Day 13 — Persistence

- [ ] SQLAlchemy 2.0
- [ ] Async DB access
- [ ] Migrations (alembic)
- [ ] Repository pattern

---

## 🧰 Day 14 — Infrastructure Basics

- [ ] Dockerfile
- [ ] Docker Compose
- [ ] Env configuration
- [ ] Logging

---

## 🚀 Final — Pet Project

Project idea:
**Async Task Processing API**

Checklist:

- [ ] FastAPI
- [ ] Async DB
- [ ] Background workers
- [ ] Custom exceptions
- [ ] Context managers
- [ ] Typing everywhere
- [ ] Tests
- [ ] Dockerized
- [ ] Clean README

---