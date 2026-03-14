from dataclasses import dataclass
from datetime import datetime, timezone



class TimestampMixin:
    created_at: datetime
    updated_at: datetime

    def __init__(self, *args, **kwargs):
        # cooperative init - call super() first
        super().__init__(*args, **kwargs)
        now = datetime.now(timezone.utc)
        self.created_at = now
        self.updated_at = now

    def touch(self) -> None:
        self.updated_at = datetime.now(timezone.utc)


class AnotherMixin:
    def __init__(self, *args, **kwargs):
        # cooperative init - call super() first
        super().__init__(*args, **kwargs)
        self.another_field = "from another mixin"


@dataclass
class Document(TimestampMixin, AnotherMixin):
    title: str
    body: str
    
    def __init__(self, title: str, body: str):
        # Initialize dataclass fields first
        self.title = title
        self.body = body
        # Then call super() to initialize all mixins cooperatively
        super().__init__()


d = Document("Spec", "text")
print(d.created_at, d.updated_at)
print(d.another_field)
d.touch()
print(d.updated_at)