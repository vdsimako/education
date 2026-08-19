from fastapi import Request, FastAPI
from fastapi.responses import JSONResponse

from app.core.exceptions import AppError, TaskNotFoundError


async def app_error_handler(_: Request,
                            exc: AppError):
    return JSONResponse(
        status_code=400,
        content={
            "error": type(exc).__name__,
            "message": str(exc),
        },
    )


async def task_not_found_handler(_: Request, exc: TaskNotFoundError) -> JSONResponse:
    return JSONResponse(
        status_code=404,
        content={
            "error": type(exc).__name__,
            "message": str(exc),
            "task_id": exc.task_id,
        },
    )


def register_exception_handlers(app: FastAPI) -> None:
    app.add_exception_handler(TaskNotFoundError, task_not_found_handler)
    app.add_exception_handler(AppError, app_error_handler)
