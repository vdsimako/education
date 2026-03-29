from fastapi import BackgroundTasks, Depends, FastAPI, status
from fastapi.responses import JSONResponse

from src.auth import require_api_key
from src.dependencies import get_task_service
from src.exceptions import TaskNotFoundError
from src.schemas import TaskCreate, TaskRead, TaskUpdate
from src.services import TaskService


def write_audit_log(message: str) -> None:
    with open("audit.log", "a", encoding="utf-8") as file:
        file.write(message + "\n")


app = FastAPI(title="Day 12 FastAPI Demo")


@app.exception_handler(TaskNotFoundError)
async def task_not_found_handler(_, exc: TaskNotFoundError) -> JSONResponse:
    return JSONResponse(
        status_code=status.HTTP_404_NOT_FOUND,
        content={"detail": f"Task with id={exc.task_id} was not found"},
    )


@app.get("/health")
def healthcheck() -> dict[str, str]:
    return {"status": "ok"}


@app.post("/tasks", response_model=TaskRead, status_code=status.HTTP_201_CREATED)
def create_task(
        payload: TaskCreate,
        background_tasks: BackgroundTasks,
        service: TaskService = Depends(get_task_service),
) -> TaskRead:
    task = service.create_task(payload)
    background_tasks.add_task(write_audit_log, f"Created task {task.id}")
    return TaskRead(**task.__dict__)


@app.get("/tasks", response_model=list[TaskRead])
def list_tasks(service: TaskService = Depends(get_task_service)) -> list[TaskRead]:
    return [TaskRead(**task.__dict__) for task in service.list_tasks()]


@app.get("/tasks/{task_id}", response_model=TaskRead)
def get_task(task_id: int, service: TaskService = Depends(get_task_service)) -> TaskRead:
    task = service.get_task(task_id)
    return TaskRead(**task.__dict__)


@app.put("/tasks/{task_id}", response_model=TaskRead)
def update_task(
        task_id: int,
        payload: TaskUpdate,
        service: TaskService = Depends(get_task_service),
) -> TaskRead:
    task = service.update_task(task_id, payload)
    return TaskRead(**task.__dict__)


@app.delete("/tasks/{task_id}", status_code=status.HTTP_204_NO_CONTENT)
def delete_task(task_id: int, service: TaskService = Depends(get_task_service)) -> None:
    service.delete_task(task_id)


@app.get("/tasks/private")
def private_tasks(
        _: str = Depends(require_api_key),
        service: TaskService = Depends(get_task_service),
) -> dict[str, int]:
    return {"count": len(service.list_tasks())}
