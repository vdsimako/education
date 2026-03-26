# 🧵 Day 10 — Concurrency in Python

## Goal

Today you learn the **foundations of concurrency in Python**:

- what concurrency means
- what the **GIL** is
- when to use **threading**
- when to use **multiprocessing**
- how to think about **CPU-bound vs IO-bound** tasks
- when **async** is a better choice

This day is still beginner-friendly, but the goal is to build the kind of understanding that also matters in interviews.

---

## 1. What is concurrency?

Concurrency means your program can manage **multiple tasks that make progress during the same period of time**.

That does **not** always mean they are running at the exact same instant.

### Simple mental model

- **Concurrency** = handling many tasks at once
- **Parallelism** = truly running many tasks at the same time

### Example

If your program downloads 20 URLs:

- with concurrency, it can avoid waiting for each one one-by-one
- with parallelism, multiple tasks may literally run at the same time on different CPU cores

---

## 2. The GIL

### What is the GIL?

**GIL** means **Global Interpreter Lock**.

In **CPython**, only **one thread at a time** can execute Python bytecode inside a single process.

### Why does it matter?

Because of the GIL:

- Python threads are often **good for IO-bound work**
- Python threads usually **do not speed up CPU-bound Python code**

### Important nuance

The GIL does **not** mean Python cannot use multiple CPU cores.

Python can still do real parallel work by using:

- **multiple processes**
- native libraries/extensions that release the GIL

---

## 3. `threading`

Use threads when the program spends a lot of time **waiting**.

### Good cases for threads

- HTTP requests
- database calls
- file reads and writes
- waiting on external services
- background coordination tasks

### Why threads help for IO

While one thread is waiting for IO, another thread can do useful work.

### Main problem with threads

Threads share memory.

That means you can get:

- race conditions
- inconsistent state
- bugs that happen only sometimes

So you need synchronization primitives like:

- `Lock`
- `RLock`
- `Semaphore`
- `Event`
- `Condition`
- `Queue`

---

## 4. `multiprocessing`

Use processes when the program spends most of its time **doing calculations**.

### Good cases for processes

- prime checking
- image processing
- data transformation
- heavy parsing
- CPU-heavy batch jobs

### Why multiprocessing helps

Each process has its **own Python interpreter** and **own memory space**, so it avoids the GIL limitation for normal Python execution.

### Trade-offs

Processes are heavier than threads:

- more memory usage
- slower startup
- data must be serialized between processes

---

## 5. CPU-bound vs IO-bound

This is the key decision point.

### CPU-bound

The program spends most of its time using the CPU.

Examples:

- mathematical computation
- compression
- image processing
- parsing huge data sets

Usually prefer:

- `multiprocessing`

### IO-bound

The program spends most of its time waiting.

Examples:

- API calls
- DB queries
- file access
- sockets
- message brokers

Usually prefer:

- `threading`
- `asyncio`

---

## 6. When async is better

`asyncio` is usually best when you have **many IO-bound tasks** and your libraries support async.

### Good cases for async

- many HTTP requests
- websocket clients/servers
- high-concurrency services
- chat systems
- many slow network calls

### Why async can be better than threads

- lower overhead than creating many threads
- easier to coordinate lots of waiting tasks in one event loop
- often cleaner for large network-heavy systems

### When async is not the best choice

- CPU-heavy work
- libraries that are blocking only
- very small scripts where threads are enough

---

## 7. Decision guide

Use this quick rule:

- **CPU-heavy work** → try `multiprocessing`
- **few blocking IO tasks** → `threading` is often enough
- **many concurrent IO tasks** → `asyncio` is often the best fit

---

## 8. Practice tasks

### Practice 1 — Thread-safe counter

Build a counter that can be incremented safely from many threads.

#### Requirements

- create a `ThreadSafeCounter` class
- store an internal integer value
- protect writes with `threading.Lock`
- provide an `increment()` method
- provide a `get_value()` method

#### What to learn

- why `counter += 1` is not safe across threads
- what a critical section is
- how a lock protects shared state

#### Step-by-step

1. Create a class with an initial value.
2. Add a private `Lock` field.
3. In `increment()`, acquire the lock before changing the value.
4. Return the new or current value if you want.
5. Add `get_value()`.
6. In tests, start many threads and increment many times.
7. Assert that the final value is exactly what you expect.

---

### Practice 2 — Multiprocessing worker pool

Build a process-pool based function that executes CPU-heavy work in parallel.

#### Requirements

- create a pure worker function
- accept a list of input values
- process them sequentially in one function
- process them with a `multiprocessing` pool in another function
- return collected results

#### Good sample workloads

- square numbers with artificial CPU work
- sum ranges
- prime checking
- repeated hashing-like loops

#### What to learn

- why process pools are useful for CPU-bound work
- why worker functions should be simple and picklable
- how to compare sequential and multiprocessing versions

#### Step-by-step

1. Create a worker function that takes one input.
2. Make it do CPU work.
3. Create a `run_sequential()` function.
4. Create a `run_with_process_pool()` function.
5. Pass a list of inputs.
6. Collect results in order.
7. Verify both implementations return the same result.

---

## 9. Suggested project structure

```text
day_10_concurrency/
├── README.md
├── src/
│   ├── __init__.py
│   ├── thread_safe_counter.py
│   ├── process_pool_demo.py
│   └── workload.py
└── tests/
    ├── __init__.py
    ├── test_thread_safe_counter.py
    └── test_process_pool_demo.py
```

---

## 10. How to run

From the project directory:

```bash
pytest -q
```

If you want to run one test file only:

```bash
pytest tests/test_thread_safe_counter.py -q
pytest tests/test_process_pool_demo.py -q
```

---

## 11. Interview questions to practice

### What is the GIL?

The GIL is the Global Interpreter Lock in CPython. It allows only one thread at a time to execute Python bytecode in a single process.

### Why are threads still useful in Python?

Because they help with IO-bound work. While one thread waits for network or disk IO, another thread can run.

### Why does multiprocessing help CPU-bound work?

Because separate processes have separate interpreters and memory spaces, so they are not limited by the same process-level GIL.

### When would you choose async instead of threads?

When you have many concurrent IO-bound tasks and your stack is async-friendly.

### What is a race condition?

A race condition happens when multiple threads access shared mutable state without proper synchronization, and the result depends on timing.

---

## 12. Common mistakes

- expecting threads to speed up CPU-heavy Python code
- modifying shared state without a lock
- passing non-picklable objects into multiprocessing workers
- forgetting `if __name__ == "__main__":` when needed for process-based code
- using async with blocking libraries and expecting good concurrency

---

## 13. Homework

### Easy

Implement `ThreadSafeCounter` and make the thread-based test pass.

### Medium

Implement sequential and process-pool execution for one CPU-heavy worker.

### Hard

Add a small benchmark script and compare:

- sequential
- threading
- multiprocessing
- async

Then explain why each one behaves differently for CPU-bound and IO-bound work.

---

## 14. Summary

Remember this:

- **GIL** affects Python threads for CPU-bound work
- **threading** is usually for **IO-bound** tasks
- **multiprocessing** is usually for **CPU-bound** tasks
- **asyncio** is often best for **many concurrent IO tasks**
- first identify the workload type, then choose the tool
