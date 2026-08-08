package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task58Test {

    private final Task58 task = new Task58();

    @Test
    void example1() {
        assertEquals(5, task.lengthOfLastWord("Hello World"));
    }

    @Test
    void example2() {
        assertEquals(4, task.lengthOfLastWord("   fly me   to   the moon   "));
    }

    @Test
    void example3() {
        assertEquals(6, task.lengthOfLastWord("luffy is still joyboy"));
    }

    @Test
    void example4() {
        assertEquals(3, task.lengthOfLastWord("Today is a nice day"));
    }
}
