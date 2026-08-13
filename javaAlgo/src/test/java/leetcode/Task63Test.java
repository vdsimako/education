package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task63Test {

    private final Task63 task = new Task63();

    @Test
    void example1() {
        assertEquals(2, task.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    @Test
    void example2() {
        assertEquals(1, task.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
    }

    @Test
    void startBlocked() {
        assertEquals(0, task.uniquePathsWithObstacles(new int[][]{{1, 0}}));
    }

    @Test
    void example3() {
        assertEquals(3, task.uniquePathsWithObstacles(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    void example4() {
        assertEquals(2, task.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}, {0,0}}));
    }
}
