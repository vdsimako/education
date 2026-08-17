package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task64Test {

    private final Task64 task = new Task64();

    @Test
    void example1() {
        assertEquals(7, task.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    @Test
    void example2() {
        assertEquals(12, task.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    void singleCell() {
        assertEquals(5, task.minPathSum(new int[][]{{5}}));
    }
}
