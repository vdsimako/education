package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task43Test {

    private final Task43 task = new Task43();

    @Test
    void example1() {
        assertEquals("6", task.multiply("2", "3"));
    }

    @Test
    void example2() {
        assertEquals("56088", task.multiply("123", "456"));
    }

    @Test
    void example3() {
        assertEquals("10000", task.multiply("1000", "10"));
    }

    @Test
    void example4() {
        assertEquals("561372", task.multiply("123", "4564"));
    }

    @Test
    void zeroTimesZero() {
        assertEquals("0", task.multiply("0", "0"));
    }

    @Test
    void zeroTimesNumber() {
        assertEquals("0", task.multiply("0", "123"));
    }

    @Test
    void singleDigits() {
        assertEquals("81", task.multiply("9", "9"));
    }

    @Test
    void largeNumbers() {
        assertEquals("998001", task.multiply("999", "999"));
    }
}
