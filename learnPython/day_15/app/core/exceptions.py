class AppError(Exception):
    """Base application exception."""

class TaskNotFoundError(AppError):
    """Task not found."""
    def __init__(self, task_id: str) -> None:
        super().__init__(f"Task {task_id} not found.")
        self.task_id = task_id

class InvalidTaskStateError(AppError):
    """Invalid task state."""
    def __init__(self, task_id: str) -> None:
        super().__init__(f"Task {task_id} is in invalid state.")
        self.task_id = task_id