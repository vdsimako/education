# 🔁 Day 6 — Iterators & Generators

## Goals

By the end of this day, you should understand:

- iterable vs iterator
- `__iter__()` / `__next__()`
- generator functions
- `yield` vs `return`
- `yield from`
- generator expressions
- memory efficiency

---

## Suggested project structure

```text
day_6_iterators_generators/
├── README.md
├── src/
│   ├── __init__.py
│   ├── custom_range.py
│   ├── stream_reader.py
│   └── pipeline.py
└── tests/
    ├── __init__.py
    ├── test_custom_range.py
    ├── test_stream_reader.py
    └── test_pipeline.py
```

---

## Theory notes

### Iterable vs iterator

An **iterable** is an object you can loop over.
Examples:

- `list`
- `tuple`
- `str`
- `dict`
- `set`
- `range`
- files
- custom classes with `__iter__()`

An **iterator** is an object that produces values one by one.
It must implement:

- `__iter__()`
- `__next__()`

Example:

```python
numbers = [1, 2, 3]
it = iter(numbers)

print(next(it))  # 1
print(next(it))  # 2
print(next(it))  # 3
```

---

### How `for` works internally

```python
items = [10, 20, 30]
it = iter(items)

while True:
    try:
        value = next(it)
        print(value)
    except StopIteration:
        break
```

---

### `__iter__()` and `__next__()`

- `__iter__()` should return an iterator
- `__next__()` should return the next value
- when iteration is finished, `__next__()` must raise `StopIteration`

---

### Generator functions

A function becomes a generator if it uses `yield`.

```python
def count_to_three():
    yield 1
    yield 2
    yield 3
```

A generator:

- produces values lazily
- pauses at `yield`
- resumes from the same place later

---

### `yield` vs `return`

`return`:

- ends the function immediately
- gives back one final result

`yield`:

- pauses the function
- gives back one value at a time
- preserves internal state

---

### `yield from`

`yield from` delegates iteration to another iterable or generator.

```python
def sub():
    yield 1
    yield 2


def main():
    yield 0
    yield from sub()
    yield 3
```

---

### Generator expressions

List comprehension:

```python
squares = [x * x for x in range(5)]
```

Generator expression:

```python
squares = (x * x for x in range(5))
```

Difference:

- list comprehension creates everything immediately
- generator expression creates values lazily

---

### Memory efficiency

This is one of the main reasons generators matter.

Less memory-friendly:

```python
numbers = [x for x in range(10_000_000)]
```

More memory-friendly:

```python
numbers = (x for x in range(10_000_000))
```

Use generators when:

- reading large files
- streaming data
- processing logs
- building pipelines

---

## Practice tasks

### 1. Custom range iterator

Build your own simplified version of `range(start, stop, step)`.

#### Requirements

- create a class `CustomRange`
- it must be iterable
- support:
  - `start`
  - `stop`
  - `step`
- it should work in a `for` loop
- raise `ValueError` if `step == 0`

#### Example behavior

```python
for x in CustomRange(1, 5):
    print(x)
```

Expected output:

```python
1
2
3
4
```

#### Step-by-step guide

1. Create a `CustomRange` class.
2. Store constructor arguments.
3. Implement `__iter__()`.
4. Decide whether:
   - the class itself is the iterator, or
   - it returns a separate iterator class.
5. Implement correct stopping logic in `__next__()`.
6. Add support for negative `step`.
7. Test these cases:
   - positive step
   - negative step
   - empty result
   - zero step

---

### 2. Streaming file reader

Create a generator that reads a file lazily.

#### Requirements

- function name: `stream_lines(path)`
- use `with open(...)`
- yield one cleaned line at a time
- remove trailing newline with `.rstrip("\n")`

#### Example

```python
for line in stream_lines("example.txt"):
    print(line)
```

#### Step-by-step guide

1. Define a generator function.
2. Open file with `with`.
3. Loop through the file object directly.
4. Use `yield` for each line.
5. Test with:
   - empty file
   - single-line file
   - multiple lines
   - blank lines

#### Extension

Create another generator that yields only non-empty lines.

---

### 3. Generator pipeline

Build a small pipeline for processing text lines.

#### Goal

Process data in stages, for example:

- read lines
- strip whitespace
- filter empty lines
- convert to uppercase

#### Example structure

```python
lines = stream_lines("data.txt")
cleaned = strip_lines(lines)
non_empty = filter_empty(cleaned)
uppercased = to_upper(non_empty)

for line in uppercased:
    print(line)
```

#### Step-by-step guide

1. Make each stage a generator function.
2. Each stage should accept an iterable.
3. Each stage should yield transformed values.
4. Chain them together.
5. Verify that the pipeline stays lazy.
6. Test it on small and larger inputs.

#### Extra challenge

Add a stage that yields only lines containing a target word.

---

## Mini homework

### Task A

Write a generator `countdown(n)` that yields values from `n` down to `1`.

### Task B

Write a generator `flatten(items)` that flattens a list of lists.

Example input:

```python
[[1, 2], [3], [4, 5]]
```

Expected sequence:

```python
1, 2, 3, 4, 5
```

Try solving it:

- first with nested loops
- then with `yield from`

### Task C

Create a generator expression that returns squares of even numbers from `0` to `20`.

### Task D

Explain in your own words:

- why generators are lazy
- why that helps memory usage
- when a list is still better

---

## Interview questions

1. What is the difference between iterable and iterator?
2. What does the iterator protocol include?
3. How does a `for` loop work internally?
4. What makes a function a generator?
5. What is the difference between `yield` and `return`?
6. What does `yield from` do?
7. What is the difference between list comprehension and generator expression?
8. Why are generators memory-efficient?
9. Can a generator be reused after it is exhausted?
10. When would you choose a list instead of a generator?

---

## Self-check

- [ ] I can explain iterable vs iterator
- [ ] I understand `iter()` and `next()`
- [ ] I can implement `__iter__()` and `__next__()`
- [ ] I can write a generator with `yield`
- [ ] I understand `yield from`
- [ ] I know when generator expressions are useful
- [ ] I understand why generators help with memory efficiency

---

## How to work on this package

### 1. Create a virtual environment

```bash
python -m venv .venv
source .venv/bin/activate
```

### 2. Install pytest

```bash
pip install pytest
```

### 3. Run tests

```bash
pytest -q
```

At the start, tests are only skeletons. Fill the implementation in `src/` and then replace the skeleton test bodies with real assertions.
