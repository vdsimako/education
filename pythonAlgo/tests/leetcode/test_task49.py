from src.leetcode.task49 import Solution


def canonical(groups):
    return sorted(sorted(group) for group in groups)


def test_example1():
    strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    expected = [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
    assert canonical(Solution().groupAnagrams(strs)) == canonical(expected)


def test_example2():
    strs = [""]
    expected = [[""]]
    assert canonical(Solution().groupAnagrams(strs)) == canonical(expected)


def test_example3():
    strs = ["a"]
    expected = [["a"]]
    assert canonical(Solution().groupAnagrams(strs)) == canonical(expected)


def test_no_anagrams_present():
    strs = ["abc", "def", "ghi"]
    expected = [["abc"], ["def"], ["ghi"]]
    assert canonical(Solution().groupAnagrams(strs)) == canonical(expected)
