package leetcode;

/*
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 500].
 * - -100 <= Node.val <= 100
 * - 0 <= k <= 2^31 - 1
 */
public class Task61 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ListNode listNode)) return false;
            return val == listNode.val && java.util.Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(val, next);
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        int length = 0;
        ListNode curr = head;
        while (true) {
            length++;
            if (curr.next == null) {
                break;
            }
            curr = curr.next;
        }

        k %= length;
        if (k == 0) {
            return head;
        }
        curr.next = head;

        int i = 0;
        ListNode tail = head;

        while (i < length - k - 1) {
            tail = tail.next;
            i++;
        }

        head = tail.next;
        tail.next = null;
        return head;
    }
}
