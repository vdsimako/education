package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
 * Given integer n, generate all possible results of rolling n dice.
 * Each dice can have values:
 * 1, 2, 3, 4, 5, 6
 * Example
 * Input:
 * n = 2
 * Expected result:
 * [1, 1]
 * [1, 2]
 * [1, 3]
 * [1, 4]
 * [1, 5]
 * [1, 6]
 * [2, 1]
 * [2, 2]
 * ...
 * [6, 6]
 * Total combinations for n = 2:
 * 6 * 6 = 36
 * */

public class Task2 {
    public List<List<Integer>> generateDiceRolls(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n);
        return result;
    }

    private void backtrack(List<List<Integer>> result,
                           ArrayList<Integer> path,
                           int n) {
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i <= 6; i++) {
            path.add(i);
            backtrack(result, path, n);
            path.remove(path.size() - 1);
        }
    }
}