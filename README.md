# education

A personal study monorepo for backend interview prep and language practice: structured
day-by-day exercises, LeetCode solutions across multiple languages, and small Java service
tasks.

## Structure

| Directory | Language | Description |
|---|---|---|
| [`learnPython/`](learnPython) | Python | Day-by-day study plan (basics → OOP → async → FastAPI → persistence), each day in its own package with tests and CI |
| [`learnPython/leetCode/`](learnPython/leetCode) | Python | Early standalone LeetCode scripts |
| [`pythonAlgo/`](pythonAlgo) | Python | LeetCode solutions with pytest test suite |
| [`javaAlgo/`](javaAlgo) | Java | LeetCode, backtracking, and greedy algorithm solutions with JUnit tests |
| [`kotlinAlgo/`](kotlinAlgo) | Kotlin | LeetCode solutions with JUnit tests |
| [`goAlgo/`](goAlgo) | Go | LeetCode solutions with Go tests |
| [`learnGo/`](learnGo) | Go | Go language basics |
| [`task_1/`](task_1) | Java | Money transfer service exercise |
| [`task_2/`](task_2) | Java | Subscription billing service exercise |

## Getting started

Each subdirectory is a self-contained project with its own dependencies. See the directory
table above and any nested `README.md` for details.

### Python (`learnPython/`, `pythonAlgo/`)

```bash
cd pythonAlgo   # or learnPython/day_x
python -m venv .venv && source .venv/bin/activate
pip install -r requirements.txt   # where present
pytest
```

### Java (`javaAlgo/`, `task_1/`, `task_2/`)

```bash
cd task_1
mvn test
```

### Kotlin (`kotlinAlgo/`)

```bash
cd kotlinAlgo
mvn test
```

### Go (`goAlgo/`, `learnGo/`)

```bash
cd goAlgo
go test ./...
```

## CI

GitHub Actions workflows under [`.github/workflows/`](.github/workflows) run the `learnPython`
day-by-day test suites (see [`.github/workflows/README.md`](.github/workflows/README.md)).

## Goals

- Prepare for a Python/Java backend interview at Middle+/Senior level
- Practice algorithmic problem solving (LeetCode) across languages
- Keep language fundamentals sharp through small, testable exercises
