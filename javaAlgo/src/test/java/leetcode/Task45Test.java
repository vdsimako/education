package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task45Test {

    private final Task45 task = new Task45();

    @Test
    void example1() {
        assertEquals(2, task.jump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test
    void example2() {
        assertEquals(2, task.jump(new int[]{2, 3, 0, 1, 4}));
    }

    @Test
    void singleElement() {
        assertEquals(0, task.jump(new int[]{0}));
    }

    @Test
    void twoElements() {
        assertEquals(1, task.jump(new int[]{1, 0}));
    }

    @Test
    void twoElements_2() {
        assertEquals(1, task.jump(new int[]{2, 1}));
    }

    @Test
    void largeJumpsAtStart() {
        assertEquals(1, task.jump(new int[]{4, 1, 1, 1, 1}));
    }
}
