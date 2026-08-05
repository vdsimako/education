package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class Task56Test {

    private final Task56 task = new Task56();

    @Test
    void example1() {
        var actual = task.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        assertArrayEquals(
                new int[][]{{1, 6}, {8, 10}, {15, 18}},
                actual);
    }

    @Test
    void example2() {
        assertArrayEquals(
                new int[][]{{1, 5}},
                task.merge(new int[][]{{1, 4}, {4, 5}}));
    }

    @Test
    void example3() {
        assertArrayEquals(
                new int[][]{{0, 4}},
                task.merge(new int[][]{{1, 4}, {0, 4}}));
    }

    @Test
    void example4() {
        assertArrayEquals(
                new int[][]{{1, 10}},
                task.merge(new int[][]{{1, 10}, {2, 3}, {4, 5}}));
    }
}
