import os
from dataclasses import dataclass


@dataclass(frozen=True)
class Settings:
    app_name: str
    app_env: str
    log_level: str
    port: int


def load_settings() -> Settings:
    return Settings(
        app_name=os.getenv("APP_NAME", "infra-basics-app"),
        app_env=os.getenv("APP_ENV", "local"),
        log_level=os.getenv("LOG_LEVEL", "INFO"),
        port=int(os.getenv("PORT", "8000")),
    )


settings = load_settings()
