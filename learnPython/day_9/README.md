# 🔄 Day 9 — Async Python

## Goal

Learn how asynchronous programming works in Python, when to use it, and how to build simple concurrent programs with `asyncio`.

By the end of this day, you should understand:

- what `async` and `await` actually do
- how the event loop schedules work
- how to run many tasks concurrently
- how async context managers and async generators work
- what common async mistakes look like
- how to design small real-world async utilities

---

## Why Async Python Matters

Async programming is useful when your program spends a lot of time **waiting**:

- HTTP requests
- database calls
- reading from sockets
- message brokers
- external APIs
- file or network streams

Async is usually **not** for CPU-heavy work like:

- image processing
- large mathematical computations
- heavy data transformations

For CPU-bound work, threads or processes are usually better.

---

## Topics

### 1. `async` / `await`

When you define a function with `async def`, it becomes a **coroutine function**.

It does **not** run immediately like a normal function.
Calling it returns a **coroutine object**, which must be awaited.

`await` tells Python to pause the current coroutine until another awaitable finishes, while giving the event loop a chance to run other tasks.

Important rule:

Calling an async function without `await` does not execute it fully.

---

### 2. Event Loop

The **event loop** is the engine that runs async tasks.

It:

- starts coroutines
- pauses them when they wait
- resumes them when they are ready
- switches between tasks efficiently

Modern Python usually starts async code with:

```python
import asyncio

asyncio.run(main())
```

---

### 3. `asyncio.gather`

`asyncio.gather()` runs multiple awaitables concurrently and waits for all of them.

Use it when:

- you have several independent async operations
- you want all results together
- you want concurrency instead of sequential waiting

---

### 4. Async Context Managers

Normal context managers use:

- `__enter__`
- `__exit__`

Async context managers use:

- `__aenter__`
- `__aexit__`

They are used with:

```python
async with resource:
    ...
```

---

### 5. Async Generators

An async generator is defined with `async def` and uses `yield`.

You iterate over it with:

```python
async for item in generator():
    ...
```

Use async generators for streaming values over time.

---

### 6. Common Async Pitfalls

- forgetting `await`
- blocking the event loop with `time.sleep()`
- assuming async means CPU parallelism
- creating tasks and never awaiting them
- shared mutable state bugs
- assuming tasks finish in the same order they started

---

## Practice

### 1. Async Rate Limiter

Build an async rate limiter that limits how many operations can happen in a time window.

Example:

- allow 3 calls per second
- extra calls must wait

Suggested steps:

1. Create `AsyncRateLimiter`.
2. Accept `max_calls` and `period_seconds`.
3. Add async `acquire()`.
4. Track recent timestamps.
5. Expire old timestamps.
6. Make extra callers wait.
7. Make concurrent behavior safe.

---

### 2. Concurrent HTTP Calls

Write code that makes multiple HTTP requests concurrently and collects results.

Suggested steps:

1. Create an async function for one request.
2. Return structured data.
3. Handle errors.
4. Use an async context manager for the client.
5. Run many requests with `asyncio.gather()`.
6. Compare concurrent and sequential execution.

---

## Project Structure

```text
day_9_async_python/
├── README.md
├── src/
│   ├── __init__.py
│   ├── rate_limiter.py
│   ├── http_client.py
│   ├── async_examples.py
│   └── pitfalls_demo.py
└── tests/
    ├── __init__.py
    ├── test_rate_limiter.py
    ├── test_http_client.py
    └── test_async_examples.py
```

---

## Test Checklist

### `test_rate_limiter.py`

You should test that:

- [ ] calls under the limit proceed immediately
- [ ] extra calls wait when the limit is exceeded
- [ ] old timestamps expire correctly
- [ ] concurrent tasks still respect the limit
- [ ] limiter works correctly across multiple time windows

### `test_http_client.py`

You should test that:

- [ ] one request result is parsed correctly
- [ ] multiple requests are executed concurrently
- [ ] errors are captured properly
- [ ] timeout behavior is handled
- [ ] the final result contains expected fields

### `test_async_examples.py`

You should test that:

- [ ] an async function returns the correct value
- [ ] `gather()` returns all expected results
- [ ] async generator yields items in order
- [ ] async context manager performs setup and cleanup
- [ ] blocking code behaves differently from async-friendly code

---

## Interview Questions

### Beginner

- What is the difference between `def` and `async def`?
- What does `await` do?
- What is a coroutine?
- What is the event loop?
- When should you use async code?

### Mid-level

- What is the difference between concurrency and parallelism?
- Why is `time.sleep()` bad inside async code?
- What does `asyncio.gather()` do?
- What is an async context manager?
- What is an async generator?

### Senior

- When is async the wrong tool?
- How do you limit concurrency in async systems?
- How do you prevent overload when calling external APIs?
- How do you design cancellation-safe async code?
- How do you handle retries, timeouts, and partial failures?
- How do async applications behave under backpressure?

---

## Homework

1. Implement `AsyncRateLimiter`.
2. Build concurrent HTTP fetching.
3. Add logging to show task start and finish order.
4. Compare sequential vs concurrent runtime.
5. Write tests for both tasks.
6. Add one async pitfall example and explain why it is wrong.
