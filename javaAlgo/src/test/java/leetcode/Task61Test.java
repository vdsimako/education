package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task61Test {

    private final Task61 task = new Task61();

    private static Task61.ListNode createNodes(int[] values) {
        Task61.ListNode dummy = new Task61.ListNode();
        Task61.ListNode curr = dummy;
        for (int v : values) {
            curr.next = new Task61.ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    @Test
    void example1() {
        assertEquals(
                createNodes(new int[]{4, 5, 1, 2, 3}),
                task.rotateRight(createNodes(new int[]{1, 2, 3, 4, 5}), 2));
    }

    @Test
    void example2() {
        assertEquals(
                createNodes(new int[]{2, 0, 1}),
                task.rotateRight(createNodes(new int[]{0, 1, 2}), 4));
    }

    @Test
    void example3() {
        assertEquals(
                createNodes(new int[]{1, 0}),
                task.rotateRight(createNodes(new int[]{0, 1}), 1));
    }

    @Test
    void example4() {
        assertEquals(
                createNodes(new int[]{0, 1}),
                task.rotateRight(createNodes(new int[]{0, 1}), 2));
    }

    @Test
    void example5() {
        assertEquals(
                createNodes(new int[]{}),
                task.rotateRight(createNodes(new int[]{}), 1)
        );
    }

    @Test
    void example6() {
        assertEquals(
                createNodes(new int[]{1}),
                task.rotateRight(createNodes(new int[]{1}), 1)
        );
    }
}
