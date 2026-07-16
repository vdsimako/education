package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task50Test {

    private static final double DELTA = 1e-5;

    private final Task50 task = new Task50();

    @Test
    void example1() {
        assertEquals(1024.00000, task.myPow(2.00000, 10), DELTA);
    }

    @Test
    void example2() {
        assertEquals(9.26100, task.myPow(2.10000, 3), DELTA);
    }

    @Test
    void example3() {
        assertEquals(0.25000, task.myPow(2.00000, -2), DELTA);
    }

    @Test
    void example4() {
        assertEquals(8.0, task.myPow(2.00000, 3));
    }

    @Test
    void zeroExponent() {
        assertEquals(1.0, task.myPow(2.00000, 0), DELTA);
    }
}
