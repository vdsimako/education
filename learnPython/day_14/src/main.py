import logging
from contextlib import asynccontextmanager

from fastapi import FastAPI

from src.config import settings
from src.logger import setup_logging

setup_logging()
logger = logging.getLogger(__name__)


@asynccontextmanager
async def lifespan():
    logger.info("Application starting in %s environment", settings.app_env)
    yield
    logger.info("Application shutting down")


app = FastAPI(title=settings.app_name)


@app.get("/health")
async def health() -> dict[str, str]:
    logger.info("Health endpoint called")
    return {"status": "ok"}


@app.get("/config")
async def get_config() -> dict[str, str | int]:
    logger.info("Config endpoint called")
    return {
        "app_name": settings.app_name,
        "app_env": settings.app_env,
        "log_level": settings.log_level,
        "port": settings.port,
    }


@app.get("/error-demo")
async def error_demo() -> dict[str, str]:
    logger.info("Error demo endpoint called")
    try:
        raise RuntimeError("Demo exception for logging practice")
    except RuntimeError:
        logger.exception("Handled demo exception")
        return {"status": "error_logged"}
