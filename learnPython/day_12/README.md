# 🌐 Day 12 — Backend Stack (FastAPI)

## Goals

Today you will move from core Python into a real web backend with FastAPI.

By the end of this day, you should be able to:

- create a basic FastAPI application
- define routes and return JSON responses
- use dependency injection with `Depends`
- validate request and response data with Pydantic
- handle errors properly
- run simple background tasks
- explore the generated OpenAPI docs
- build a small CRUD API
- protect one endpoint with a simple auth dependency

---

## Topics

- [ ] FastAPI basics
- [ ] Dependency injection
- [ ] Validation (Pydantic)
- [ ] Error handlers
- [ ] Background tasks
- [ ] OpenAPI

### Practice

- [ ] CRUD API
- [ ] Auth-protected endpoint

---

# 1. Theory

## 1.1 What is FastAPI?

FastAPI is a modern Python framework for building APIs.

It is built around:

- Python type hints
- request/response validation
- automatic API documentation
- async support
- dependency injection

A basic mental model:

- a route is a URL + HTTP method
- a handler is a Python function
- inputs come from path params, query params, headers, or request body
- output is converted into a JSON response

---

## 1.2 Why FastAPI is useful

FastAPI is popular because it gives you many useful backend features with relatively little code.

It helps you:

- build APIs quickly
- validate data automatically
- document APIs automatically
- keep code readable
- support async endpoints when needed

For learning, it is a very good framework because it makes important backend concepts visible.

---

## 1.3 FastAPI basics

A minimal FastAPI app usually includes:

- an app object
- one or more route handlers
- request and response schemas
- optional dependencies

Typical first steps:

1. create `app = FastAPI()`
2. add one route such as `GET /health`
3. run the app with `uvicorn`
4. open `/docs`

Important beginner idea:

> Keep route handlers thin and move business logic into services.

---

## 1.4 Dependency injection

Dependency injection means that one part of the code receives another part from the outside instead of creating it directly.

In FastAPI, this is commonly done with `Depends()`.

Use dependencies for things like:

- current user
- auth checks
- service objects
- repository objects
- settings
- database session

Why this matters:

- code becomes easier to test
- responsibilities are separated more clearly
- route handlers stay small

---

## 1.5 Validation with Pydantic

FastAPI uses Pydantic models to validate and structure data.

You usually define:

- input schemas for requests
- output schemas for responses

Examples of what validation can check:

- required fields
- field types
- length constraints
- default values
- optional fields

Good beginner rule:

> Do not manually parse request dictionaries when a schema can do it for you.

---

## 1.6 Error handling

In an API, errors should be clear and predictable.

Common examples:

- `404 Not Found`
- `400 Bad Request`
- `401 Unauthorized`
- `422 Unprocessable Entity` for validation errors

In FastAPI, expected API errors are often returned with `HTTPException`.

You can also define custom exception handlers when you want a consistent error format.

Good beginner rule:

- use exceptions for error cases
- keep error responses explicit
- do not expose internal implementation details to clients

---

## 1.7 Background tasks

Sometimes you want to return a response immediately and do extra work afterward.

Examples:

- write a log entry
- send an email
- notify another service
- store audit information

FastAPI provides `BackgroundTasks` for this kind of simple post-response work.

Important note:

Background tasks are useful for small jobs.
They are not a replacement for a full queue system like Celery.

---

## 1.8 OpenAPI

FastAPI generates API documentation automatically.

You usually get:

- `/docs` — Swagger UI
- `/redoc` — ReDoc

This is very useful because:

- frontend developers can explore the API
- QA can inspect endpoints
- you can verify schemas visually
- your documentation stays close to your code

---

## 1.9 Good backend structure

Even in a small project, it is good to separate responsibilities.

Recommended structure:

- `main.py` for app creation and route registration
- `schemas.py` for request/response schemas
- `services.py` for business logic
- `dependencies.py` for shared dependencies
- `auth.py` for auth-related logic
- `exceptions.py` for custom exceptions and handlers

This helps you grow the project without turning route handlers into large messy files.

---

## 1.10 Common beginner mistakes

Common mistakes:

- putting all logic directly in route handlers
- skipping schemas and working with raw dictionaries
- mixing auth logic with business logic everywhere
- returning inconsistent error responses
- not checking status codes
- ignoring validation errors
- overcomplicating the app too early

---

# 2. Project structure

Suggested structure for this day:

```text
day_12_fastapi/
├── README.md
├── requirements.txt
├── src/
│   ├── __init__.py
│   ├── main.py
│   ├── models.py
│   ├── schemas.py
│   ├── services.py
│   ├── dependencies.py
│   ├── auth.py
│   └── exceptions.py
└── tests/
    ├── __init__.py
    ├── test_crud.py
    ├── test_auth.py
    └── test_validation.py
```

---

# 3. Setup

Create and activate a virtual environment, then install dependencies.

```bash
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

Run the development server:

```bash
uvicorn src.main:app --reload
```

Then open:

- `http://127.0.0.1:8000/docs`
- `http://127.0.0.1:8000/redoc`

---

# 4. Step-by-step practice tasks

## Task 1 — Create your first FastAPI app

### Goal

Create a minimal application and return JSON.

### Steps

1. Create `src/main.py`.
2. Create a `FastAPI()` app.
3. Add a `GET /health` endpoint.
4. Return a small JSON object.
5. Start the app with `uvicorn`.
6. Open `/docs`.

### What to verify

- the server starts
- the route returns JSON
- the endpoint appears in Swagger UI

---

## Task 2 — Add schemas

### Goal

Describe input and output data using Pydantic models.

### Steps

1. Create `src/schemas.py`.
2. Add one create schema, one update schema, and one read schema.
3. Add basic field constraints.
4. Use these schemas in route handlers.

### What to verify

- invalid data is rejected
- valid data is accepted
- the schema appears correctly in `/docs`

---

## Task 3 — Build a CRUD API

### Goal

Create a small CRUD API for one resource.

Suggested resource: `Task`

### Steps

1. Add a simple in-memory store in a service.
2. Create:
   - `POST /tasks`
   - `GET /tasks`
   - `GET /tasks/{task_id}`
   - `PUT /tasks/{task_id}`
   - `DELETE /tasks/{task_id}`
3. Return correct status codes.
4. Raise an error when an item does not exist.

### What to verify

- create works
- list works
- get by id works
- update works
- delete works
- missing item returns an error

---

## Task 4 — Add dependency injection

### Goal

Keep route handlers small and inject shared objects.

### Steps

1. Create a service class in `src/services.py`.
2. Create a dependency provider in `src/dependencies.py`.
3. Inject the service into route handlers with `Depends`.
4. Move business logic out of handlers.

### What to verify

- routes still work
- service is created through a dependency
- handlers become smaller and cleaner

---

## Task 5 — Add proper errors

### Goal

Return clear API errors.

### Steps

1. Create custom exceptions in `src/exceptions.py`.
2. Raise them from the service when appropriate.
3. Register exception handlers in the app.
4. Keep error responses consistent.

### What to verify

- missing resource returns a clear 404-style response
- custom exceptions are converted into JSON responses
- error shape is consistent

---

## Task 6 — Add a background task

### Goal

Run simple work after returning a response.

### Steps

1. Add a background task to the create endpoint.
2. Use it to simulate a post-create action.
3. Keep the response immediate.

### What to verify

- the endpoint returns normally
- background work is scheduled
- the main response is not blocked by the extra task

---

## Task 7 — Add an auth-protected endpoint

### Goal

Protect one endpoint with a simple auth dependency.

### Steps

1. Create an auth helper in `src/auth.py`.
2. Read an API key from a request header.
3. Reject unauthorized requests.
4. Add one route such as `GET /tasks/private`.

### What to verify

- request without the key is rejected
- request with the correct key is accepted
- auth logic is separate from business logic

---

## Task 8 — Inspect OpenAPI

### Goal

Use generated docs to inspect and validate your API design.

### Steps

1. Open `/docs`.
2. Open `/redoc`.
3. Inspect request schemas.
4. Inspect response schemas.
5. Check status codes and auth-related behavior.

### What to verify

- all routes are listed
- schemas look correct
- protected routes are visible
- validation rules are reflected in docs

---

# 5. Suggested implementation idea

Use a simple `Task` domain.

Example fields:

- `id`
- `title`
- `description`
- `is_done`

Suggested behavior:

- create task
- list tasks
- get task by id
- update task
- delete task
- protect a private endpoint
- schedule a background log after creation

This is simple enough for learning and realistic enough for interviews.

---

# 6. Test naming guide

Choose names that describe behavior.

Good examples:

- `test_create_task_returns_created_item`
- `test_list_tasks_returns_all_items`
- `test_get_task_returns_404_when_missing`
- `test_update_task_changes_existing_item`
- `test_delete_task_removes_item`
- `test_private_endpoint_rejects_request_without_api_key`
- `test_create_task_rejects_invalid_payload`

Avoid names like:

- `test_api`
- `test_1`
- `test_fastapi`

---

# 7. Interview questions

Be ready to answer questions like:

1. What problem does FastAPI solve well?
2. How does dependency injection work in FastAPI?
3. Why are Pydantic schemas useful?
4. What is the difference between request schema and response schema?
5. When should you raise `HTTPException`?
6. What is a background task?
7. What is OpenAPI and why is it useful?
8. How would you structure a larger FastAPI application?
9. Why should route handlers stay thin?
10. How would you protect an endpoint?

---

# 8. Homework

## Level 1
Create a minimal FastAPI app with one health endpoint.

## Level 2
Add CRUD endpoints for a `Task` resource.

## Level 3
Add Pydantic schemas and validation.

## Level 4
Move logic into a service and inject it with dependencies.

## Level 5
Add one auth-protected endpoint and one background task.

---

# 9. End-of-day result

At the end of Day 12, you should be able to:

- create a FastAPI app
- define CRUD routes
- validate input and output data
- use dependencies cleanly
- return proper API errors
- add simple auth protection
- understand generated API docs

This is a very important step toward building real backend applications with Python.
