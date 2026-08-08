package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class Task59Test {

    private final Task59 task = new Task59();

    @Test
    void example1() {
        assertArrayEquals(
                new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}},
                task.generateMatrix(3));
    }

    @Test
    void example2() {
        assertArrayEquals(
                new int[][]{{1}},
                task.generateMatrix(1));
    }
}
