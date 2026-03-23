from src.log_args import log_args


def test_log_args(capsys):
    @log_args
    def test(a, b):
        return a + b
    test(1, 2)
    captured = capsys.readouterr()
    assert captured.out == "Args: (1, 2)\nKwargs: {}\n"

def test_log_args_preserves_metadata():
    @log_args
    def test(a, b):
        """TEST"""
        return a + b
    assert test.__name__ == "test"
    assert test.__doc__ == "TEST"