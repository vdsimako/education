package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task53Test {

    private final Task53 task = new Task53();

    @Test
    void example1() {
        assertEquals(6, task.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void example2() {
        assertEquals(1, task.maxSubArray(new int[]{1}));
    }

    @Test
    void example3() {
        assertEquals(23, task.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }
}
