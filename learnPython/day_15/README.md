# Async Task Processing API

A learning-oriented final pet project for backend development in Python.

This project is designed to help you practice:

- FastAPI
- async database access
- background workers
- custom exceptions
- context managers / lifespan / dependency scopes
- typing everywhere
- testing
- Docker / Docker Compose
- clean project structure

The core idea is simple:

1. A client sends a task to the API.
2. The API stores the task in the database.
3. A background worker picks the task up.
4. The worker processes it.
5. The client checks task status and result later.

---

## 1. Project Goal

Build an API that can:

- create tasks
- list tasks
- fetch a task by ID
- retry failed tasks
- process tasks in the background
- store result or error information

This project teaches the same backend ideas you will see in real systems:

- request validation
- transaction boundaries
- service layer
- repository pattern
- worker/API separation
- async resource management
- automated tests

---

## 2. Suggested Stack

- **FastAPI** — API framework
- **SQLAlchemy Async** — ORM / database access
- **PostgreSQL** — main database
- **Alembic** — migrations
- **Taskiq + Redis** — background jobs
- **Pydantic** — request / response models
- **pytest + pytest-asyncio + HTTPX** — tests
- **Docker Compose** — local multi-service setup

---

## 3. Architecture

```text
Client
  |
  v
FastAPI API  ---> PostgreSQL
  |
  v
Redis broker
  |
  v
Taskiq Worker ---> PostgreSQL
```

### Flow

1. `POST /tasks` creates a task row with status `pending`.
2. API sends task ID to broker.
3. Worker receives task.
4. Worker updates status to `running`.
5. Worker stores `result` or `error`.
6. Client polls `GET /tasks/{task_id}`.

---

## 4. Learning Order

Build the project in this order:

1. Configuration
2. FastAPI app + health endpoint
3. Async DB connection
4. SQLAlchemy model
5. Alembic migration
6. Repository layer
7. Service layer
8. Create/list/get task endpoints
9. Custom exceptions + handlers
10. BackgroundTasks demo
11. Real Taskiq worker
12. Tests
13. Docker setup
14. README cleanup

Do not start with workers first.
Start with a clean request -> DB -> response flow.

---

## 5. Project Structure

```text
async-task-api-skeleton/
├── README.md
├── pyproject.toml
├── .env.example
├── Dockerfile
├── docker-compose.yml
├── alembic.ini
├── migrations/
│   ├── env.py
│   └── versions/
├── app/
│   ├── __init__.py
│   ├── main.py
│   ├── api/
│   │   ├── __init__.py
│   │   └── routes/
│   │       ├── __init__.py
│   │       ├── health.py
│   │       └── tasks.py
│   ├── common/
│   │   ├── __init__.py
│   │   └── types.py
│   ├── core/
│   │   ├── __init__.py
│   │   ├── config.py
│   │   ├── exceptions.py
│   │   ├── handlers.py
│   │   ├── lifespan.py
│   │   └── logging.py
│   ├── db/
│   │   ├── __init__.py
│   │   ├── base.py
│   │   ├── models.py
│   │   ├── session.py
│   │   └── repositories/
│   │       ├── __init__.py
│   │       └── task_repository.py
│   ├── schemas/
│   │   ├── __init__.py
│   │   └── task.py
│   ├── services/
│   │   ├── __init__.py
│   │   └── task_service.py
│   └── workers/
│       ├── __init__.py
│       ├── broker.py
│       └── task_handlers.py
└── tests/
    ├── __init__.py
    ├── conftest.py
    ├── test_health.py
    ├── test_create_task.py
    ├── test_get_task.py
    ├── test_list_tasks.py
    ├── test_retry_task.py
    ├── test_repository.py
    ├── test_service.py
    ├── test_exceptions.py
    └── test_worker_flow.py
```

---

## 6. Domain Model

### Task statuses

Use a closed set of statuses:

- `pending`
- `running`
- `done`
- `failed`

### Task fields

A task can contain:

- `id`
- `task_type`
- `status`
- `payload`
- `result`
- `error`
- `created_at`
- `updated_at`

You can store `payload`, `result`, and `error` as JSON.

---

## 7. API Endpoints

### `GET /health`
Simple health check.

### `POST /tasks`
Create a new task.

Example request:

```json
{
  "task_type": "demo_sleep",
  "payload": {
    "seconds": 2
  }
}
```

Example response:

```json
{
  "id": "9b0d3b7e-1b8a-4d12-b78d-95d73c8f6f18",
  "task_type": "demo_sleep",
  "status": "pending",
  "payload": {
    "seconds": 2
  },
  "result": null,
  "error": null,
  "created_at": "2026-04-03T12:00:00Z",
  "updated_at": "2026-04-03T12:00:00Z"
}
```

### `GET /tasks/{task_id}`
Fetch one task by ID.

### `GET /tasks`
List tasks, optionally filtered by status.

### `POST /tasks/{task_id}/retry`
Retry a failed task.

---

## 8. Custom Exceptions

Define your own exceptions instead of raising generic `ValueError` everywhere.

Suggested exceptions:

- `TaskNotFoundError`
- `InvalidTaskStateError`
- `TaskAlreadyFinishedError`
- `TaskProcessingError`

Then map them to API responses with FastAPI exception handlers.

---

## 9. Context Managers in This Project

Use context-manager style patterns in three important places:

### App lifespan
Use `asynccontextmanager` for startup/shutdown logic.

### Database session dependency
Use `yield` dependency to create and close one session per request.

### Worker task scope
Each worker job should open its own DB session and close it safely.

Important rule:
Do not share one `AsyncSession` across concurrent requests or jobs.

---

## 10. Service Layer and Repository Layer

### Repository layer
The repository should only talk to the database.

Examples:

- create task row
- fetch by ID
- list tasks
- update status
- save result

### Service layer
The service should contain application rules.

Examples:

- validate allowed state transitions
- decide when retry is allowed
- enqueue background work
- coordinate repository operations

Good rule:

- repository = persistence logic
- service = business logic
- router = HTTP logic

---

## 11. Testing Strategy

You should test the project on multiple levels.

### API tests
Test endpoints through HTTP.

Examples:

- health endpoint returns `200`
- creating task returns expected schema
- task not found returns correct error response
- retry endpoint rejects invalid state

### Repository tests
Test DB operations directly.

Examples:

- create row
- read row
- list rows
- update status

### Service tests
Test business rules without depending too much on HTTP.

Examples:

- cannot retry completed task
- retry failed task resets status to pending
- state transition rules are enforced

### Worker tests
Test task execution flow.

Examples:

- worker marks task as running
- worker stores result on success
- worker stores error on failure

---

## 12. Environment Variables

Use `.env` locally.

Example values are in `.env.example`.

Main variables:

- `APP_NAME`
- `APP_ENV`
- `APP_HOST`
- `APP_PORT`
- `DATABASE_URL`
- `REDIS_URL`
- `LOG_LEVEL`

---

## 13. How to Run Locally

### 1. Create virtual environment

```bash
python -m venv .venv
source .venv/bin/activate
```

### 2. Install dependencies

```bash
pip install -e .[dev]
```

### 3. Copy env file

```bash
cp .env.example .env
```

### 4. Start containers

```bash
docker compose up -d db redis
```

### 5. Run migrations

```bash
alembic upgrade head
```

### 6. Start API

```bash
uvicorn app.main:app --reload
```

### 7. Start worker

```bash
taskiq worker app.workers.broker:broker app.workers.task_handlers
```

---

## 14. How to Run Tests

```bash
pytest -q
```

For async tests, use `pytest.mark.asyncio`.

---

## 15. First Implementation Tasks

Start with these tasks:

1. Make `GET /health` work.
2. Make settings load from environment.
3. Configure async SQLAlchemy session.
4. Create `TaskModel`.
5. Write first migration.
6. Implement repository create/get/list methods.
7. Implement service create/get/list/retry methods.
8. Connect router to service.
9. Add exception handlers.
10. Add worker skeleton.
11. Add tests step by step.

---

## 16. Senior-Level Improvements Later

After MVP, you can add:

- idempotency key for task creation
- structured logging with `task_id`
- retries with backoff
- timeout handling
- metrics
- auth
- pagination metadata
- dead-letter queue
- optimistic locking/version field
- task deduplication

---

## 17. Notes About This Skeleton

This starter project intentionally contains:

- some ready-to-run code
- some placeholders with `NotImplementedError`
- test skeletons you should complete gradually

That is good for learning.
You should implement one layer at a time and keep tests green.
