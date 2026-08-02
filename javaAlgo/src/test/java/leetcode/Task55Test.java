package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class Task55Test {

    private final Task55 task = new Task55();

    @Test
    void example1() {
        assertTrue(task.canJump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test
    void example2() {
        assertFalse(task.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    void example3() {
        assertTrue(task.canJump(new int[]{1, 1, 1, 1, 0}));
    }
}
