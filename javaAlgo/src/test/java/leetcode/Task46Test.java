package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

class Task46Test {

    private final Task46 task = new Task46();

    @Test
    void example1() {
        List<List<Integer>> result = task.permute(new int[]{1, 2, 3});
        assertEquals(6, result.size());
        assertTrue(result.contains(List.of(1, 2, 3)));
        assertTrue(result.contains(List.of(1, 3, 2)));
        assertTrue(result.contains(List.of(2, 1, 3)));
        assertTrue(result.contains(List.of(2, 3, 1)));
        assertTrue(result.contains(List.of(3, 1, 2)));
        assertTrue(result.contains(List.of(3, 2, 1)));
    }

    @Test
    void example2() {
        List<List<Integer>> result = task.permute(new int[]{0, 1});
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
    }

    @Test
    void singleElement() {
        List<List<Integer>> result = task.permute(new int[]{1});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(1)));
    }

    @Test
    void negativeNumbers() {
        List<List<Integer>> result = task.permute(new int[]{-1, 0, 1});
        assertEquals(6, result.size());
        assertTrue(result.contains(List.of(-1, 0, 1)));
        assertTrue(result.contains(List.of(1, 0, -1)));
    }
}
