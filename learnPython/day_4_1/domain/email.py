from dataclasses import dataclass
import re


@dataclass(frozen=True)
class Email:
    value: str
    domain: str = ""

    def __post_init__(self):
        normalized = self.value.strip().lower()
        if not re.match(r"^[^@]+@[^@]+\.[^@]+$", normalized):
            raise ValueError("Invalid email format")
        object.__setattr__(self, "value", normalized)
        if not self.domain:
            extracted_domain = normalized.split("@")[1]
            object.__setattr__(self, "domain", extracted_domain)