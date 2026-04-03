# 🗄 Day 13 — Persistence

## Goal

Learn how Python applications persist data using a real database.

Today you will practice:

- SQLAlchemy 2.0
- async database access
- migrations with Alembic
- repository pattern

By the end of the day, you should understand how to:

- define database models
- create async database sessions
- save and read data
- separate persistence logic from business logic
- manage schema changes safely

---

## Why this matters

In-memory storage is useful for learning, but real applications need persistent storage.

Persistence helps you:

- keep data after app restart
- query and filter data efficiently
- enforce constraints
- share data across multiple app instances
- evolve schema over time

This is where your training starts to look much closer to a real backend application.

---

## Theory

### 1. SQLAlchemy 2.0

SQLAlchemy is one of the most common Python libraries for working with relational databases.

It can be used in two styles:

- **Core** — closer to raw SQL
- **ORM** — maps Python classes to database tables

For this day, focus on the ORM.

Main concepts:

- **Engine** — knows how to connect to the database
- **Session** — unit that talks to the database
- **Model** — Python class mapped to a table
- **Metadata** — collection of mapped tables
- **Transaction** — group of changes committed or rolled back together

### 2. SQLAlchemy 2.0 style

Modern SQLAlchemy uses explicit typing and clear APIs:

- `DeclarativeBase`
- `Mapped[...]`
- `mapped_column(...)`
- `select(...)`
- `session.execute(...)`
- `scalar_one()` / `scalar_one_or_none()` / `scalars().all()`

### 3. Async DB access

For async SQLAlchemy you usually use:

- `create_async_engine`
- `AsyncSession`
- `async_sessionmaker`
- `await session.execute(...)`
- `await session.commit()`
- `await session.refresh(...)`

Async DB access does not make the database magically faster. It helps your app avoid blocking while waiting for database I/O.

### 4. Migrations with Alembic

A migration is a versioned schema change.

Examples:

- create a table
- add a column
- rename a column
- add an index
- add a constraint

Alembic helps you:

- keep schema changes in code
- apply them in order
- track database version
- share schema evolution across environments

### 5. Repository pattern

The repository pattern separates persistence logic from business logic.

Instead of spreading ORM queries everywhere, you create a dedicated object responsible for loading and saving entities.

Repository responsibilities:

- read entities
- persist entities
- delete entities
- hide persistence details from higher layers

Service responsibilities:

- define application behavior
- coordinate use cases
- apply business rules

---

## Practice project

Build a small **Task persistence module**.

Entity fields:

- `id`
- `title`
- `description`
- `is_done`
- `created_at`

You will implement:

- SQLAlchemy model
- async engine and session setup
- repository class
- service layer
- Alembic migration

---

## Step-by-step practice tasks

### Part 1 — Set up the database layer

1. Create a `db.py` module.
2. Add an async engine.
3. Add an async session factory.
4. Add a declarative base class.

What to learn:

- where DB configuration should live
- how sessions are created
- how models are registered

### Part 2 — Create the model

Create a `Task` model with fields:

- `id`
- `title`
- `description`
- `is_done`
- `created_at`

Things to think about:

- which fields are nullable
- which defaults belong in Python
- which defaults belong in the database
- how `Mapped[...]` typing works

### Part 3 — Practice direct session usage

Before writing a repository, do manual persistence work:

1. create a task
2. commit it
3. refresh it
4. read it back by id
5. list all tasks
6. update `is_done`
7. delete a task

What to learn:

- `add`
- `commit`
- `refresh`
- `select`
- scalar results
- deletion flow

### Part 4 — Implement the repository

Create `TaskRepository` with methods:

- `add(task)`
- `get_by_id(task_id)`
- `list_all()`
- `mark_done(task_id)`
- `delete(task_id)`

Goal:

move persistence details out of the higher-level code.

### Part 5 — Add the service layer

Create `TaskService` that depends on `TaskRepository`.

Suggested methods:

- `create_task(title, description)`
- `get_task(task_id)`
- `list_tasks()`
- `complete_task(task_id)`
- `remove_task(task_id)`

Goal:

separate business behavior from persistence details.

### Part 6 — Add Alembic migrations

1. initialize Alembic
2. connect Alembic to your metadata
3. generate the first migration
4. apply the migration
5. verify the table exists
6. add a second schema change later
7. generate the next migration
8. apply it

Goal:

understand how schema evolves safely.

---

## Project structure

```text
day_13_persistence/
├─ README.md
├─ pyproject.toml
├─ alembic.ini
├─ alembic/
│  ├─ env.py
│  └─ versions/
├─ src/
│  ├─ __init__.py
│  ├─ db.py
│  ├─ main.py
│  ├─ models.py
│  ├─ repositories.py
│  ├─ schemas.py
│  └─ services.py
└─ tests/
   ├─ __init__.py
   ├─ conftest.py
   ├─ test_migrations.py
   ├─ test_models.py
   ├─ test_repository.py
   └─ test_service.py
```

---

## What to pay attention to

### Commit vs flush

- `flush()` sends pending SQL to the DB inside the current transaction
- `commit()` finalizes the transaction

### Refresh

Use `refresh()` when you want ORM objects updated with values generated by the database.

### Async session lifecycle

In web apps, one request usually uses one session or one unit of work.

### Repository boundaries

Repository should focus on persistence. It should not contain higher-level business rules.

### Service boundaries

Service should define behavior and use cases, not raw SQLAlchemy details.

---

## Common mistakes

1. Mixing sync and async SQLAlchemy APIs
2. Creating sessions everywhere instead of centralizing session creation
3. Putting business logic inside ORM models
4. Overengineering repository abstractions too early
5. Changing models without creating migrations
6. Not testing the missing/not-found cases

---

## Interview questions

### What is SQLAlchemy?

A Python library for working with relational databases using SQL expressions and ORM mapping.

### What is an ORM?

A way to map Python objects to rows in database tables.

### Why use async DB access?

To avoid blocking the event loop while waiting for database I/O in async applications.

### What is a migration?

A versioned schema change that can be applied to a database in a controlled way.

### Why use Alembic?

To track, generate, and apply SQLAlchemy schema changes across environments.

### What is the repository pattern?

A pattern that hides persistence details behind a clean interface for saving and reading entities.

### Repository vs service?

Repository handles data access. Service handles business logic and use-case orchestration.

### What is one danger of repository pattern?

Too much abstraction can make simple code harder to understand.

---

## Homework

### Easy

Implement `TaskRepository.add()` and `TaskRepository.get_by_id()`.

### Medium

Implement full CRUD for `TaskRepository` and `TaskService`.

### Hard

Add:

- `priority`
- filtering by `is_done`
- pagination
- second migration
- full tests

### Senior-level thinking

Design how you would handle:

- transaction boundaries
- optimistic locking
- unit of work
- session-per-request
- repository interfaces for testing
- migration strategy from SQLite to PostgreSQL

---

## Expected result

After this day, you should be able to say:

- I know how SQLAlchemy 2.0 models work
- I can create async sessions
- I understand how to persist and query data
- I can organize persistence through repositories
- I know why migrations are necessary
- I can build a small production-like persistence layer
