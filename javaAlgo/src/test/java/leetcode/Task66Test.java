package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class Task66Test {

    private final Task66 task = new Task66();

    @Test
    void example1() {
        assertArrayEquals(new int[]{1, 2, 4}, task.plusOne(new int[]{1, 2, 3}));
    }

    @Test
    void example2() {
        assertArrayEquals(new int[]{4, 3, 2, 2}, task.plusOne(new int[]{4, 3, 2, 1}));
    }

    @Test
    void example3() {
        assertArrayEquals(new int[]{1, 0}, task.plusOne(new int[]{9}));
    }

    @Test
    void example4() {
        assertArrayEquals(new int[]{1, 2, 9}, task.plusOne(new int[]{1, 2, 8}));
    }

    @Test
    void allNines() {
        assertArrayEquals(new int[]{1, 0, 0, 0}, task.plusOne(new int[]{9, 9, 9}));
    }
}
