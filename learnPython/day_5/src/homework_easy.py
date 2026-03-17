class ValidationError(Exception):
    pass

def convert(value: str) -> int:
    try:
        return int(value)
    except ValueError as e:
        raise ValidationError("Invalid value") from e
