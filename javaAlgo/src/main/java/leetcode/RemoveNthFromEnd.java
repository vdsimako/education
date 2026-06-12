package leetcode;

import java.util.Objects;

public class RemoveNthFromEnd {
    public static class ListNode {
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ListNode listNode)) return false;
            return val == listNode.val && Objects.equals(next, listNode.next);
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }

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
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = new ListNode();
        ListNode curr = result;

        if (head == null) {
            return result;
        }

        while (head != null) {
            if (isNodeOnIndexFromEnd(head, n)) {
                head = head.next;
                continue;
            }
            curr.next = new ListNode(head.val);
            curr = curr.next;
            head = head.next;
        }

        return result.next;
    }

    private boolean isNodeOnIndexFromEnd(ListNode head, int n) {
        ListNode curr = head;
        int index = 0;
        while (curr != null) {
            if (index == n - 1 && curr.next == null) {
                return true;
            }
            curr = curr.next;
            index++;
        }
        return false;
    }
}
