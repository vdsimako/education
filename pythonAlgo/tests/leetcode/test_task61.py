from src.leetcode.task61 import ListNode, Solution


def build_list(values):
    dummy = ListNode()
    curr = dummy
    for v in values:
        curr.next = ListNode(v)
        curr = curr.next
    return dummy.next


def to_list(head):
    result = []
    while head:
        result.append(head.val)
        head = head.next
    return result


def test_example1():
    head = build_list([1, 2, 3, 4, 5])
    assert to_list(Solution().rotateRight(head, 2)) == [4, 5, 1, 2, 3]


def test_example2():
    head = build_list([0, 1, 2])
    assert to_list(Solution().rotateRight(head, 4)) == [2, 0, 1]


def test_example3():
    head = build_list([0, 1])
    assert to_list(Solution().rotateRight(head, 1)) == [1, 0]


def test_example4():
    head = build_list([0, 1])
    assert to_list(Solution().rotateRight(head, 2)) == [0, 1]


def test_example5():
    head = build_list([])
    assert to_list(Solution().rotateRight(head, 1)) == []


def test_example6():
    head = build_list([1])
    assert to_list(Solution().rotateRight(head, 1)) == [1]
