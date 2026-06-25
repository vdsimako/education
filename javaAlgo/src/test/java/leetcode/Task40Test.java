package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Task40Test {

    private final Task40 task = new Task40();

    @Test
    void example1() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> actual = task.combinationSum2(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(1, 1, 6),
                List.of(1, 2, 5),
                List.of(1, 7),
                List.of(2, 6)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void example2() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;

        List<List<Integer>> actual = task.combinationSum2(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(1, 2, 2),
                List.of(5)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void noCombination() {
        int[] candidates = {3, 4, 5};
        int target = 2;

        List<List<Integer>> actual = task.combinationSum2(candidates, target);

        List<List<Integer>> expected = List.of();

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void singleElementEqualsTarget() {
        int[] candidates = {1, 2, 3};
        int target = 3;

        List<List<Integer>> actual = task.combinationSum2(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(1, 2),
                List.of(3)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void duplicatesShouldNotCreateDuplicateResults() {
        int[] candidates = {1, 1, 1, 1};
        int target = 2;

        List<List<Integer>> actual = task.combinationSum2(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(1, 1)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    private List<List<Integer>> normalize(List<List<Integer>> lists) {
        return lists.stream()
                .map(list -> list.stream().sorted().toList())
                .sorted(Comparator.comparing(Object::toString))
                .toList();
    }
}