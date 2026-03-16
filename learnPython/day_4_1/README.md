# 🧩 Day 4.1 — Value Objects vs Entities (DDD)

A focused Python lesson on one of the most important DDD modeling ideas: **identity vs value**.

---

## Learning goals

By the end of this lesson, you should be able to:

- explain the difference between **Entity** and **Value Object**
- understand **identity vs equality**
- model both concepts in Python
- use **immutability** where it gives real benefits
- design **Entities that contain Value Objects**
- implement simple domain models like `User`, `Email`, and `Money`

---

## Recommended project structure

```text
day4_1/
├── README.md
├── src/
│   ├── __init__.py
│   ├── entities.py
│   ├── value_objects.py
│   └── main.py
└── tests/
    ├── __init__.py
    ├── test_email.py
    ├── test_money.py
    └── test_user.py
```

### Suggested responsibility split

- `value_objects.py` — `Email`, `Money`
- `entities.py` — `User`
- `main.py` — manual experiments / demo runs
- `tests/` — unit tests for behavior and invariants

---

## Warm-up recap

Before starting, make sure these ideas feel familiar:

- class
- object
- `__init__`
- `__eq__`
- `dataclass`
- mutable vs immutable objects

### Quick self-check

Try to answer mentally:

1. If two objects have the same field values, are they always the same thing?
2. If a user changes email, is it still the same user?
3. Are two `10 USD` values equal?

---

## Theory

### 1. What is an Entity?

An **Entity** is defined primarily by its **identity**, not just by its attributes.

#### Core idea

Even if some fields change, it is still the same entity.

#### Examples

- `User`
- `Order`
- `Invoice`
- `BankAccount`

#### Typical properties

- has an `id`
- has lifecycle: created, updated, maybe deleted
- may change over time
- equality is usually based on identity

---

### 2. What is a Value Object?

A **Value Object** is defined by its **value**, not by identity.

#### Core idea

If all attributes are equal, the objects are equal.

#### Examples

- `Email`
- `Money`
- `Address`
- `DateRange`
- `Coordinates`

#### Typical properties

- no business identity
- usually immutable
- compared by value
- small and focused
- often validated on creation

---

### 3. Equality vs Identity

This is the main distinction for today.

#### Identity

Question:

> Is this the same business object over time?

Example:

- `User(id=10, email="a@x.com")`
- later email becomes `"b@x.com"`
- still the same user because identity did not change

#### Equality

Question:

> Do these objects represent the same value?

Example:

- `Email("test@example.com")`
- `Email("test@example.com")`

These should be equal.

---

### 4. Why immutability matters for Value Objects

Value Objects are usually immutable because they are:

- safer to share
- easier to reason about
- harder to corrupt accidentally
- easier to test
- better for stable equality / hashing
- a natural fit for domain modeling

In Python, common tools are:

- `@dataclass(frozen=True)`
- `Decimal` for money instead of `float`

---

### 5. Entities containing Value Objects

This is a very common DDD pattern.

Example:

- `User` is an **Entity**
- `Email` is a **Value Object**
- `Money` can also be a **Value Object**

That means:

- the entity manages lifecycle and identity
- value objects hold validated domain values

---

## Python mapping

### Entity in Python

Usually:

- normal class or mutable dataclass
- has an `id`
- may change over time
- custom equality if needed

### Value Object in Python

Usually:

- `@dataclass(frozen=True)`
- validation in `__post_init__`
- equality by value
- explicit and narrow API

---

## Practice 1 — User (Entity) with Email (Value Object)

### Task

Model:

- `Email` as a Value Object
- `User` as an Entity

### Requirements

#### `Email`

- immutable
- normalizes input
- validates format on creation
- equality by value

#### `User`

- has `user_id`
- has `name`
- has `email: Email`
- can change email
- equality is based on `user_id`

### What to verify

- two `Email` objects with the same text should be equal
- two `User` objects with the same `user_id` should be equal
- changing email should not create a new user identity
- invalid email should raise an error

---

## Practice 2 — Money (Value Object)

### Task

Implement `Money` as a Value Object.

### Requirements

- immutable
- uses `Decimal`
- validates amount
- validates currency as uppercase 3-letter code
- supports addition only for the same currency

### What to verify

- `Money("10.00", "usd")` becomes `USD`
- `Money("10.50", "USD") + Money("2.25", "USD")` works
- adding `USD` and `EUR` raises error
- equality is based on value

---

## Practice tasks

### Task A — Build `Email`

Implement `Email` so that:

- spaces are trimmed
- value is lowercased
- invalid format raises `ValueError`
- two equal emails compare as equal

### Task B — Build `User`

Implement `User` so that:

- it stores `user_id`, `name`, and `email`
- email can be changed via `change_email()`
- equality depends on `user_id`, not all fields

### Task C — Build `Money`

Implement `Money` so that:

- it is immutable
- amount is stored as `Decimal`
- currency is normalized to uppercase
- addition works only for matching currencies

### Task D — Extend the model

Add the following:

- `Email.domain` property
- `User.change_name(new_name)`
- `Money.__sub__(...)`
- `Money.is_positive()`

---

## Hidden answers

<details>
<summary><strong>Answer — Why should <code>Email</code> be a Value Object?</strong></summary>

`Email` does not need business identity. Nobody asks whether this is “Email #42” in the system. What matters is the value itself. If two `Email` objects contain the same normalized address, they represent the same thing.

</details>

<details>
<summary><strong>Answer — Why should <code>User</code> be an Entity?</strong></summary>

A `User` has a stable business identity over time. The email, name, or other attributes may change, but the user remains the same user in the system. That is classic Entity behavior.

</details>

<details>
<summary><strong>Answer — Why is <code>float</code> bad for money?</strong></summary>

`float` uses binary floating-point representation, which introduces precision errors. Money calculations need exact decimal behavior, so `Decimal` is the safer and correct choice.

</details>

<details>
<summary><strong>Answer — Why are Value Objects usually immutable?</strong></summary>

Immutability makes them safer, easier to reason about, and easier to compare. Instead of mutating a value object, you create a new one that represents the new value.

</details>

<details>
<summary><strong>Answer — What is the key difference between identity and equality?</strong></summary>

Identity answers: “Is this the same business object over time?” Equality answers: “Do these objects represent the same value?” Entities are about identity. Value Objects are about equality by attributes.

</details>

---

## Mini test — Day 4.1

Try to answer without looking at the hidden answers.

### Questions

1. What makes an Entity different from a Value Object?
2. Why is `Email` usually modeled as a Value Object?
3. Why should `User` equality usually not depend on all fields?
4. Why is immutability a good fit for Value Objects?
5. Why should `Decimal` be used for money instead of `float`?
6. What should happen if someone tries to add `Money("10", "USD")` and `Money("5", "EUR")`?
7. Can an Entity contain Value Objects?
8. If a user changes email, does that create a new Entity?

### Expected answers

<details>
<summary><strong>Show expected answers</strong></summary>

1. Entity is defined by identity; Value Object is defined by value.
2. Because its meaning is fully described by its value, not by business identity.
3. Because the same user may change fields over time but remain the same entity.
4. Because immutable values are safer, easier to reason about, and stable for comparison.
5. Because `float` introduces precision issues.
6. Raise an error because currencies differ.
7. Yes — that is a very common DDD pattern.
8. No — it is still the same user if identity stays the same.

</details>

---

## Interview-style questions

Practice answering these out loud:

1. What is the difference between Entity and Value Object in DDD?
2. Why are Value Objects usually immutable?
3. How would you model money in Python?
4. Why is identity important for entities?
5. Can an Entity contain Value Objects? Why is that useful?
6. What problems appear if you use `float` for money?
7. How would you implement equality for a `User` entity?

---

## Success criteria

You are done when you can confidently say:

- **Entity = identity**
- **Value Object = value**
- **Value Objects are usually immutable**
- **Entities often contain Value Objects**

---

## Tiny cheat-sheet

### Entity

**Who is it?**

### Value Object

**What value does it represent?**

---

## Recommended session flow

- **10 min** — recap OOP basics
- **20 min** — theory
- **25 min** — `Email` + `User`
- **25 min** — `Money`
- **10 min** — practice review
- **10 min** — mini test

---

## Homework

### Required

- implement `Email`
- implement `User`
- implement `Money`

### Extra

- add `Email.domain`
- add `User.change_name(...)`
- add `Money.__sub__(...)`
- add `Money.is_positive()`
- write unit tests for all core rules
