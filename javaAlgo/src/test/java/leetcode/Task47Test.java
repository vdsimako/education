package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

class Task47Test {

    private final Task47 task = new Task47();

    @Test
    void example1() {
        List<List<Integer>> result = task.permuteUnique(new int[]{1, 1, 2});
        assertEquals(3, result.size());
        assertTrue(result.contains(List.of(1, 1, 2)));
        assertTrue(result.contains(List.of(1, 2, 1)));
        assertTrue(result.contains(List.of(2, 1, 1)));
    }

    @Test
    void example2() {
        List<List<Integer>> result = task.permuteUnique(new int[]{1, 2, 3});
        assertEquals(6, result.size());
        assertTrue(result.contains(List.of(1, 2, 3)));
        assertTrue(result.contains(List.of(3, 2, 1)));
    }

    @Test
    void allDuplicates() {
        List<List<Integer>> result = task.permuteUnique(new int[]{1, 1, 1});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(1, 1, 1)));
    }

    @Test
    void singleElement() {
        List<List<Integer>> result = task.permuteUnique(new int[]{5});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(5)));
    }

    @Test
    void twoDuplicates() {
        List<List<Integer>> result = task.permuteUnique(new int[]{1, 1});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(1, 1)));
    }
}
