import inspect
from functools import wraps



def required_role(role: str):
    def decorator_required_role(func):

        @wraps(func)
        def wrapper(*args, **kwargs):
            ba = inspect.signature(func).bind(*args, **kwargs)

            user = ba.arguments["user"]

            if role not in user["roles"]:
                raise PermissionError("User does not have required role")

            return func(*args, **kwargs)

        return wrapper

    return decorator_required_role
