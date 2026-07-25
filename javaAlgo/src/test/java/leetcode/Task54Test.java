package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

class Task54Test {

    private final Task54 task = new Task54();

    @Test
    void example1() {
        assertEquals(
            List.of(1, 2, 3, 6, 9, 8, 7, 4, 5),
            task.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})
        );
    }

    @Test
    void example2() {
        assertEquals(
            List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
            task.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})
        );
    }
}
