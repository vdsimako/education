from src.leetcode.task58 import Solution


def test_example1():
    assert Solution().lengthOfLastWord("Hello World") == 5


def test_example2():
    assert Solution().lengthOfLastWord("   fly me   to   the moon  ") == 4


def test_example3():
    assert Solution().lengthOfLastWord("luffy is still joyboy") == 6
