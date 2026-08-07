from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from app.api.routes import health, tasks
from app.core.config import get_settings
from app.core.handlers import register_exception_handlers

settings = get_settings()
app = FastAPI(title=settings.app_name,
              port=settings.app_port,
              host=settings.app_host,
              log_level=settings.log_level)
app.add_middleware(
    CORSMiddleware,
)

app.include_router(health.router)
app.include_router(tasks.router)

register_exception_handlers(app)
