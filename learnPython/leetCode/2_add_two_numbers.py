# Definition for singly-linked list.
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        return f"{self.val} -> {self.next}"


def addTwoNumbers(
        l1: Optional[ListNode],
        l2: Optional[ListNode]
) -> Optional[ListNode]:
    #
    result = f(l1, l2)

    return result


def f(
        l1: Optional[ListNode],
        l2: Optional[ListNode],
        add: int = 0
) -> Optional[ListNode]:
    if l1 is None and l2 is None:
        if add > 0:
            return ListNode(add, None)
        return None
    a, b = l1.val if l1 is not None else 0, l2.val if l2 is not None else 0
    c = a + b + add
    if c >= 10:
        c = c - 10
        add = 1

    result = ListNode(c, None)
    result.next = f(l1.next, l2.next, add)

    return result


if __name__ == "__main__":
    print(addTwoNumbers(ListNode(2, ListNode(4)), ListNode(4, ListNode(6))))
