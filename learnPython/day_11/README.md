# 🧪 Day 11 — Testing

## Goals

Today you will learn the foundations of testing in Python with `pytest`.

By the end of this day, you should be able to:

- write simple tests with `pytest`
- use fixtures for shared setup
- use parametrization for multiple test cases
- mock external dependencies
- test async code
- test a service layer cleanly

---

## Topics

- [ ] pytest basics
- [ ] Fixtures
- [ ] Parametrization
- [ ] Mocking
- [ ] Testing async code

### Practice

- [ ] Test service layer
- [ ] Mock external dependency

---

# 1. Theory

## 1.1 Why testing matters

Testing is one of the most important backend skills.

Tests help you:

- catch bugs early
- refactor safely
- document expected behavior
- design cleaner code
- reduce fear when changing working code

A good test answers one simple question:

> Given this input and situation, what behavior do I expect?

---

## 1.2 What is a test?

A test is code that checks whether other code behaves correctly.

Example idea:

- input: `2, 3`
- expected result: `5`

Your test runs the code and verifies that the actual result matches the expected one.

---

## 1.3 Unit tests vs integration tests

### Unit test

A unit test checks one small piece of logic in isolation.

Examples:

- one function
- one class method
- one service method

Unit tests should usually be:

- fast
- deterministic
- independent

### Integration test

An integration test checks how multiple parts work together.

Examples:

- service + repository
- API endpoint + database
- code + real external API sandbox

For Day 11, focus mainly on **unit tests**.

---

## 1.4 `pytest` basics

`pytest` is the most common testing framework in Python.

Main ideas:

- test files usually start with `test_`
- test functions usually start with `test_`
- use plain `assert`
- run tests with:

```bash
pytest
```

Run tests with more detailed output:

```bash
pytest -v
```

---

## 1.5 Arrange / Act / Assert

A very useful structure for writing tests:

### Arrange
Prepare objects, input data, and dependencies.

### Act
Call the code under test.

### Assert
Check the result.

Example thinking:

1. create input data
2. call function
3. assert expected output

This makes tests easier to read and maintain.

---

## 1.6 Fixtures

Fixtures are used for reusable setup.

Use fixtures when several tests need the same:

- object
- service
- input data
- fake dependency
- temporary file
- prepared environment

Without fixtures, you repeat setup code in many tests.

With fixtures, tests become:

- shorter
- cleaner
- easier to change

Good beginner rule:

> If you repeat the same setup in multiple tests, move it into a fixture.

---

## 1.7 Parametrization

Parametrization lets you run one test with many input/output cases.

Use it when:

- the logic is the same
- only the data changes

Typical examples:

- testing many valid emails
- testing many invalid values
- checking edge cases for a helper function

Benefits:

- less duplication
- better coverage
- easier extension later

---

## 1.8 Mocking

Mocking means replacing a real dependency with a fake object in tests.

Use mocking when your code depends on something external, such as:

- HTTP API
- database
- email sender
- payment provider
- current time
- random values
- filesystem calls

Why?

Because unit tests should verify **your logic**, not whether some external system is available.

### Mock at the boundary

Usually mock:

- repository methods
- HTTP client methods
- third-party SDK calls
- external message sender calls

Usually do not mock:

- pure functions
- simple value objects
- your own small business logic unless there is a clear reason

---

## 1.9 Testing async code

Async code must be tested differently because async functions must be awaited.

Typical async test cases:

- async service calls async dependency
- async function returns expected result
- async function raises expected exception
- async client failure is handled correctly

You should learn to test:

- success case
- failure case
- exception propagation
- awaited dependency behavior

---

## 1.10 What makes a test good?

Good tests are:

- small
- readable
- deterministic
- fast
- focused on behavior

Bad tests are often:

- too long
- tightly coupled to implementation details
- flaky
- dependent on real network/database when that is unnecessary
- hard to understand from the name

---

## 1.11 Common beginner mistakes

Common mistakes:

- testing too many things in one test
- repeating setup everywhere
- choosing unclear test names
- mocking too much
- asserting implementation details instead of behavior
- skipping edge cases
- not testing failure paths

---

# 2. Project structure

Suggested structure for this day:

```text
day_11_testing/
├── README.md
├── requirements.txt
├── src/
│   ├── __init__.py
│   ├── models.py
│   ├── repository.py
│   ├── external_client.py
│   ├── service.py
│   └── async_service.py
└── tests/
    ├── __init__.py
    ├── test_repository.py
    ├── test_service.py
    ├── test_external_client.py
    └── test_async_service.py
```

---

# 3. Setup

Create and activate a virtual environment, then install the dependencies.

```bash
python -m venv .venv
source .venv/bin/activate
pip install pytest pytest-asyncio
```

You can also put them into `requirements.txt`:

```txt
pytest
pytest-asyncio
```

---

# 4. Step-by-step practice tasks

## Task 1 — Write your first basic tests

### Goal
Learn how `pytest` discovers and runs tests.

### Steps

1. Create a very small function in one of your modules.
2. Make it return a predictable value.
3. Create a test file inside `tests/`.
4. Add one test function starting with `test_`.
5. Use `assert` to check the expected result.
6. Run `pytest -v`.

### What to verify

- the test file is discovered
- the test function is executed
- the expected value matches the actual value

---

## Task 2 — Test a repository method

### Goal
Test a simple class that returns stored data.

### Steps

1. Create a `User` model in `src/models.py`.
2. Create a `UserRepository` in `src/repository.py`.
3. Store a small in-memory dictionary of users.
4. Add a method that returns a user by id.
5. Write tests for:
   - existing user
   - missing user

### What to verify

- repository returns a user when the id exists
- repository returns `None` when the id does not exist

---

## Task 3 — Test the service layer

### Goal
Test business logic, not just helper functions.

### Scenario
The service depends on the repository.

Example flow:

- repository returns a user by id
- service either returns the user
- or raises an error if the user does not exist

### Steps

1. Create a `UserService` in `src/service.py`.
2. Inject the repository into the service.
3. Add a method that fetches a user.
4. Raise a custom exception when the user is missing.
5. Write tests for both success and failure cases.

### What to verify

- service returns the expected user
- service raises the correct exception for missing user
- service behavior is clear and deterministic

---

## Task 4 — Add fixtures

### Goal
Reduce repeated setup code.

### Steps

1. Look at the setup repeated across your tests.
2. Move repeated object creation into fixtures.
3. Create fixtures for:
   - repository
   - service
   - sample user
4. Replace repeated setup code with fixture arguments.
5. Re-run the tests.

### What to verify

- tests become shorter
- setup stays reusable
- test behavior does not change

---

## Task 5 — Add parametrization

### Goal
Run one test with many similar cases.

### Steps

1. Pick logic that can be tested with different inputs.
2. Create one parametrized test.
3. Add several input/output combinations.
4. Include:
   - normal cases
   - edge cases
   - unusual values if needed

### What to verify

- the same test runs multiple times
- each case is visible in the test output
- repetitive tests are removed

---

## Task 6 — Mock an external dependency

### Goal
Test service logic without calling a real external system.

### Scenario
The service uses an external client.

Possible examples:

- email sender
- payment client
- weather API client
- notification service

### Steps

1. Add a client class in `src/external_client.py`.
2. Add a service method that calls that client.
3. In tests, replace the real client with a fake or mock.
4. Simulate:
   - successful response
   - failed response
   - raised exception
5. Assert how the service behaves.

### What to verify

- the real dependency is not called
- the service handles success correctly
- the service handles failure correctly
- the dependency is called with the expected arguments

---

## Task 7 — Test async code

### Goal
Learn to test async methods.

### Scenario
Create an async service that awaits an async dependency.

### Steps

1. Create `src/async_service.py`.
2. Add one async dependency class.
3. Add one async service method that awaits that dependency.
4. Create async tests in `tests/test_async_service.py`.
5. Cover:
   - successful result
   - empty result
   - dependency exception

### What to verify

- async function returns expected data
- dependency is awaited correctly
- exceptions are handled or propagated as expected

---

# 5. Suggested implementation idea

You can build a simple user domain for this day.

## Example domain

- `User`
- `UserRepository`
- `UserService`
- `NotificationClient`
- `AsyncUserService`

Possible behaviors:

- repository fetches a user by id
- service returns user data
- service formats display name
- service raises `UserNotFoundError`
- service sends a welcome message through notification client
- async service fetches profile data asynchronously

This example is simple enough for learning and realistic enough for interviews.

---

# 6. Test naming guide

Choose names that clearly describe behavior.

Good examples:

- `test_get_by_id_returns_user_when_user_exists`
- `test_get_by_id_returns_none_when_user_does_not_exist`
- `test_get_user_returns_user_when_found`
- `test_get_user_raises_user_not_found_error_when_missing`
- `test_send_welcome_message_calls_notification_client`
- `test_get_profile_raises_error_when_profile_is_empty`

Avoid names like:

- `test_service`
- `test_1`
- `test_happy_path`

---

# 7. Interview questions

Be ready to answer questions like:

1. What is the difference between unit tests and integration tests?
2. Why are fixtures useful?
3. When should you use parametrization?
4. When should you mock something?
5. What should usually not be mocked?
6. What makes a test flaky?
7. How do you test async code in Python?
8. Why are small tests usually better than large tests?
9. What is Arrange / Act / Assert?
10. Why is testing behavior usually better than testing implementation details?

---

# 8. Homework

## Level 1
Write tests for two or three very small functions.

## Level 2
Refactor repeated setup into fixtures.

## Level 3
Use parametrization for several cases.

## Level 4
Create a service with one mocked dependency.

## Level 5
Create one async service and test it.

---

# 9. End-of-day result

At the end of Day 11, you should be able to:

- write tests with `pytest`
- reuse setup with fixtures
- cover multiple cases with parametrization
- isolate dependencies with mocks
- test async functions confidently
- structure tests in a readable way

This is a very important step toward real backend development and senior-level interview preparation.
