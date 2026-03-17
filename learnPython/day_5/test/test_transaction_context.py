import pytest

from src.transaction_manager import TransactionManager


def test_transaction_context_cm_prints_begin_message(capsys):
    with TransactionManager("test"):
        captured = capsys.readouterr()
        assert "BEGIN" in captured.out


def test_transaction_context_cm_prints_commit_message(capsys):
    with TransactionManager("test"):
        pass

    captured = capsys.readouterr()
    assert "COMMIT" in captured.out
    assert "CLEANUP RESOURCES" in captured.out

def test_transaction_context_cm_prints_rollback_message(capsys):
    with pytest.raises(RuntimeError):
        with TransactionManager("test"):
            raise RuntimeError("Test error")

    captured = capsys.readouterr()
    assert "ROLLBACK" in captured.out
    assert "CLEANUP RESOURCES" in captured.out
