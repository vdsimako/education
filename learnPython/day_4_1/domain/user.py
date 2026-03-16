from domain.email import Email


class User:
    def __init__(self, user_id: int, name: str, email: Email):
        self.user_id = user_id
        self.name = name
        self.email = email

    def change_email(self, new_email: Email) -> None:
        self.email = new_email

    def change_name(self, new_name: str) -> None:
        if not new_name.strip():
            raise ValueError("Name cannot be empty")
        self.name = new_name

    def __eq__(self, other):
        if not isinstance(other, User):
            return NotImplemented
        return self.user_id == other.user_id

    def __repr__(self):
        return f"User(user_id={self.user_id}, name={self.name!r}, email={self.email.value!r})"