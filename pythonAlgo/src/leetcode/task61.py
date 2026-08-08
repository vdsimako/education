"""
61. Rotate List
https://leetcode.com/problems/rotate-list/

Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]

Constraints:
- The number of nodes in the list is in the range [0, 500].
- -100 <= Node.val <= 100
- 0 <= k <= 2^31 - 1
"""
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if not head:
            return head

        length = 0
        curr = head
        while True:
            length += 1
            if not curr.next:
                break
            curr = curr.next

        steps = int(k % length)
        if steps == 0:
            return head

        curr.next = head

        tail = head
        for _ in range(length - steps - 1):
            tail = tail.next

        head = tail.next
        tail.next = None

        return head
