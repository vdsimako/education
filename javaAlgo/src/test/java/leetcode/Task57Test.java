package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class Task57Test {

    private final Task57 task = new Task57();

    @Test
    void example1() {
        assertArrayEquals(
                new int[][]{{1, 5}, {6, 9}},
                task.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
    }

    @Test
    void example2() {
        assertArrayEquals(
                new int[][]{{1, 2}, {3, 10}, {12, 16}},
                task.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}));
    }

    @Test
    void example3() {
        assertArrayEquals(
                new int[][]{{5, 7}},
                task.insert(new int[][]{}, new int[]{5, 7}));
    }

    @Test
    void example4() {
        assertArrayEquals(
                new int[][]{{1, 2}, {3, 5}, {6, 7}},
                task.insert(new int[][]{{1, 2}, {6, 7}}, new int[]{3, 5}));
    }
}
