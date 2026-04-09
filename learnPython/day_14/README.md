# 🧰 Day 14 — Infrastructure Basics

Today you move from “the app works on my machine” to “the app is packaged, configurable, and observable.”

This day gives you a practical beginner-friendly foundation in:

- Dockerfile
- Docker Compose
- environment configuration
- logging

---

## 🎯 Learning goals

By the end of this day, you should be able to:

- package a Python app into a Docker image
- run the app inside a container
- run multiple services together with Docker Compose
- pass configuration through environment variables
- avoid hardcoding secrets
- add useful application logging
- explain the basics of infrastructure setup in an interview

---

## 1. Theory

### 1.1 What is a Dockerfile?

A `Dockerfile` is a recipe for building a Docker image.

It describes:

- which base image to use
- where your code goes inside the container
- which dependencies to install
- which command starts the application

Instead of telling another developer:

> install Python, then install dependencies, then run this command...

you define that setup once in a Docker image.

---

### 1.2 What is Docker Compose?

Docker Compose helps you run multiple services together.

Typical example:

- web app
- PostgreSQL database

Compose lets you define them in one file and start them together.

It also helps with:

- networking between services
- environment variables
- port mapping
- volumes
- startup configuration

Simple mental model:

- `Dockerfile` = how to build one service
- `compose.yaml` = how to run several services together

---

### 1.3 What is environment configuration?

Your code should not hardcode things like:

- application name
- port
- database URL
- debug mode
- API keys
- passwords

These should come from environment variables.

Bad:

```python
DATABASE_URL = "postgresql://admin:admin@localhost:5432/app"
```

Better:

```python
import os

DATABASE_URL = os.getenv("DATABASE_URL")
```

Why this matters:

- different values for local, test, and production
- safer handling of secrets
- easier deployment
- easier testing

---

### 1.4 What is logging?

Logging means recording what the application is doing.

Useful logs answer questions like:

- did the app start successfully?
- which endpoint was called?
- what failed?
- why did it fail?
- what happened just before the error?

Good logs are:

- readable
- consistent
- meaningful
- safe
- not full of noise

At this stage, start with:

- startup log
- shutdown log
- request log
- config-safe log
- error log

---

## 2. Core concepts

### Dockerfile basics

Important instructions:

- `FROM` — base image
- `WORKDIR` — working directory inside the image
- `COPY` — copy files into the image
- `RUN` — execute commands during build
- `EXPOSE` — document the port
- `CMD` — define the default start command

Example:

```dockerfile
FROM python:3.12-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 8000

CMD ["python", "main.py"]
```

---

### Compose basics

Important sections:

- `services`
- `build`
- `ports`
- `environment`
- `depends_on`
- `volumes`

Example:

```yaml
services:
  app:
    build: .
    ports:
      - "8000:8000"
    environment:
      APP_ENV: local
```

---

### Environment config basics

Typical config groups:

- runtime config: app name, port, environment
- infrastructure config: database URL, redis URL
- feature flags
- secrets

Important rule:

- use `.env` or environment variables locally
- never commit real secrets into git

---

### Logging basics

Common log levels:

- `DEBUG` — detailed troubleshooting info
- `INFO` — normal useful events
- `WARNING` — something unexpected but app still works
- `ERROR` — an operation failed
- `CRITICAL` — severe failure

Good beginner rule:

- use `INFO` for normal milestones
- use `WARNING` for recoverable problems
- use `ERROR` when something fails
- use `DEBUG` only when you need detailed diagnostics

---

## 3. Practice project

Build a small FastAPI service with:

- `GET /health`
- `GET /config`
- `GET /error-demo`

And add:

- Dockerfile
- Compose file
- env-based settings
- logging setup

Optional extension:

- add PostgreSQL to Compose
- connect app to the database
- add a DB health check later

---

## 4. Project structure

```text
day_14/
├── README.md
├── .env.example
├── .gitignore
├── Dockerfile
├── compose.yaml
├── requirements.txt
├── src/
│   ├── __init__.py
│   ├── config.py
│   ├── logger.py
│   └── main.py
└── tests/
    ├── __init__.py
    ├── test_health.py
    ├── test_config.py
    └── test_logging.py
```

---

## 5. Step-by-step practice tasks

### Task 1 — Create the minimal FastAPI app

Create these endpoints:

- `GET /health` → returns `"ok"`
- `GET /config` → returns only safe configuration values
- `GET /error-demo` → demonstrates logging of a failure

Your goal:

- create a minimal but realistic app
- keep business logic out of infrastructure code
- separate configuration and logging into their own modules

---

### Task 2 — Centralize configuration

Create a `Settings`
class that reads from environment variables.

Include at least:

- `APP_NAME`
- `APP_ENV`
- `LOG_LEVEL`
- `PORT`

Practice points:

- use defaults where reasonable
- convert `PORT` to an integer
- keep env-reading in one place instead of scattering `os.getenv()` everywhere

---

### Task 3 — Add logging

Create a logger setup function.

Requirements:

- log to stdout
- use log level from config
- use a common format
- log application startup
- log application shutdown
- log endpoint usage
- log exceptions

Practice points:

- replace `print()` with `logging`
- use `logger.info()` and `logger.exception()`
- make logs readable and consistent

---

### Task 4 — Add a Dockerfile

Write a `Dockerfile` that:

- uses a slim Python image
- sets a working directory
- installs dependencies
- copies application code
- starts the FastAPI app with Uvicorn

Practice points:

- understand build steps
- understand startup commands
- understand why `0.0.0.0` is needed in containers

---

### Task 5 — Run the app in Docker

Build and run the container.

Check:

- the app starts successfully
- `/health` is reachable
- logs appear in the terminal

Commands:

```bash
docker build -t day14-app .
docker run -p 8000:8000 day14-app
```

---

### Task 6 — Add Docker Compose

Create `compose.yaml` with one service first:

- `app`
- build from current directory
- publish port `8000:8000`
- pass environment variables

Then extend it with a PostgreSQL service.

Practice points:

- app and db can run together
- service names become hostnames inside the Docker network
- `localhost` inside a container is not the host machine

---

### Task 7 — Add `.env.example`

Create `.env.example` with safe example values.

Example:

```env
APP_NAME=infra-basics-app
APP_ENV=local
LOG_LEVEL=INFO
PORT=8000
```

Practice points:

- document required configuration
- separate real secrets from example values

---

### Task 8 — Observe logging behavior

Call:

- `/health`
- `/config`
- `/error-demo`

Watch the logs and answer:

- which logs are normal?
- which logs are errors?
- what information is useful?
- what information should never be logged?

---

### Task 9 — Add PostgreSQL to Compose

After the app-only version works, extend the setup with PostgreSQL.

Add:

- `db` service
- PostgreSQL image
- volume for persistent data
- database environment variables

Think about:

- how the app would connect to the database
- which values should come from env vars
- why `depends_on` is not the same as “database is ready”

---

## 6. Starter code skeleton

### `src/config.py`

Responsibilities:

- read environment variables
- store defaults
- keep config in one place

Suggested fields:

- `app_name`
- `app_env`
- `log_level`
- `port`

---

### `src/logger.py`

Responsibilities:

- configure root logging
- define common formatting
- make application logs consistent

Suggested behavior:

- log to stdout
- use log level from settings
- keep setup isolated in one function

---

### `src/main.py`

Responsibilities:

- create FastAPI app
- call logging setup
- register startup/shutdown events
- expose endpoints

Endpoints to implement:

- `/health`
- `/config`
- `/error-demo`

---

## 7. Testing guide

You are not testing Docker itself here.
You are testing the Python application behavior around infrastructure concerns.

### `tests/test_health.py`

Test cases:

- `test_health_returns_200`
- `test_health_returns_expected_payload`

What to verify:

- response status code is 200
- response JSON contains `{"status": "ok"}`

---

### `tests/test_config.py`

Test cases:

- `test_config_endpoint_returns_safe_values`
- `test_settings_use_defaults_when_env_vars_missing`
- `test_port_is_parsed_as_integer`

What to verify:

- no secrets are returned
- expected safe config values are exposed
- defaults work correctly

---

### `tests/test_logging.py`

Test cases:

- `test_logging_is_configured`
- `test_health_endpoint_emits_info_log`
- `test_error_demo_emits_exception_log`

What to verify:

- logging is initialized
- endpoint calls generate logs
- exceptions are logged properly

---

## 8. Common beginner mistakes

### Docker mistakes

- forgetting `WORKDIR`
- binding the server to `127.0.0.1` instead of `0.0.0.0`
- copying the whole project before dependency installation
- exposing a port but not publishing it when running the container

### Compose mistakes

- trying to reach the database with `localhost` from the app container
- assuming `depends_on` means “the database is ready”
- forgetting to pass env variables

### Config mistakes

- hardcoding URLs and secrets
- reading env vars in many different files
- committing real `.env` files

### Logging mistakes

- using only `print()`
- logging too little
- logging too much
- logging secrets, tokens, or passwords

---

## 9. Interview notes

### Junior-level answer

“I use Dockerfile to package the application and Docker Compose to run multiple services together locally. I keep configuration in environment variables and use logging instead of print so I can debug issues more easily.”

### Middle-level answer

“I separate configuration from code, centralize environment reading, and use Docker Compose for local orchestration. I add startup, request, and error logs with proper log levels so failures are easier to trace.”

### Senior-level thinking

When you grow further, start considering:

- image size optimization
- build caching
- deterministic dependency installation
- startup health checks
- secret management
- structured JSON logging
- centralized log aggregation
- readiness vs liveness
- stateless containers
- config validation at startup

---

## 10. Homework

### Easy

- add `/health`
- containerize the app
- move config into environment variables
- add startup logs

### Medium

- add `/config`
- add `/error-demo`
- add Compose with app + postgres
- add `.env.example`
- document local startup steps

### Hard

- add structured JSON logging
- add Compose healthchecks
- validate required env vars at startup
- add request ID middleware
- add separate settings for local/dev/prod

---

## 11. Commands to know

Build image:

```bash
docker build -t day14-app .
```

Run container:

```bash
docker run -p 8000:8000 day14-app
```

Run with Compose:

```bash
docker compose up --build
```

Stop Compose:

```bash
docker compose down
```

---

## 12. Self-check

Try answering these on your own:

1. What is the difference between a Docker image and a container?
2. Why should an app inside Docker bind to `0.0.0.0`?
3. Why should configuration come from environment variables?
4. Why is logging better than plain `print()`?
5. What problem does Docker Compose solve?
6. Why should secrets not be committed to git?
7. Why is `depends_on` not enough for database readiness?

---

## 13. Next step

After Day 14, the natural next step is:

- database-backed app in containers
- migrations in containers
- health checks
- basic deployment thinking
