package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task62Test {

    private final Task62 task = new Task62();

    @Test
    void example1() {
        assertEquals(28, task.uniquePaths(3, 7));
    }

    @Test
    void example2() {
        assertEquals(3, task.uniquePaths(3, 2));
    }

    @Test
    void singleCell() {
        assertEquals(1, task.uniquePaths(1, 1));
    }
}
