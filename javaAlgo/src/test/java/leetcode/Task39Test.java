package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Task39Test {

    private final Task39 solution = new Task39();

    @Test
    void testCombinationSumExample1() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> actual = solution.combinationSum(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(2, 2, 3),
                List.of(7)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void testCombinationSumExample2() {
        int[] candidates = {2, 3, 5};
        int target = 8;

        List<List<Integer>> actual = solution.combinationSum(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(2, 2, 2, 2),
                List.of(2, 3, 3),
                List.of(3, 5)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void testNoCombination() {
        int[] candidates = {2};
        int target = 1;

        List<List<Integer>> actual = solution.combinationSum(candidates, target);

        List<List<Integer>> expected = List.of();

        assertEquals(normalize(expected), normalize(actual));
    }

    @Test
    void testSingleCandidateRepeated() {
        int[] candidates = {3};
        int target = 9;

        List<List<Integer>> actual = solution.combinationSum(candidates, target);

        List<List<Integer>> expected = List.of(
                List.of(3, 3, 3)
        );

        assertEquals(normalize(expected), normalize(actual));
    }

    private List<List<Integer>> normalize(List<List<Integer>> lists) {
        List<List<Integer>> normalized = new ArrayList<>();

        for (List<Integer> list : lists) {
            List<Integer> copy = new ArrayList<>(list);
            copy.sort(Integer::compareTo);
            normalized.add(copy);
        }

        normalized.sort(Comparator.comparing(Object::toString));

        return normalized;
    }
}