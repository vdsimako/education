import java.util.Objects;

public class SwapNodesInPairs {
    /*
     * Given a linked list, swap every two adjacent nodes and return its head.
     * You must solve the problem without modifying the values in the list's
     * nodes (i.e., only nodes themselves may be changed.)
     * */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = new ListNode(0, head);
        ListNode prev = result;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;
            ListNode nextPair = second.next != null ? second.next : null;

            prev.next = second;
            first.next = nextPair;
            second.next = first;

            prev = first;

        }
        return result.next;
    }

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
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ListNode listNode)) return false;
            return val == listNode.val && Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }
    }
}
