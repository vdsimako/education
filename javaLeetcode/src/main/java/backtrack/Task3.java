package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * Task 3 — Generate all subsets of an array
 * Given an integer array, generate all possible subsets.
 *
 * */
public class Task3 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), 0, nums);

        return result;
    }

    private void backtrack(List<List<Integer>> result,
                           ArrayList<Integer> path,
                           int i,
                           int[] nums) {
        if (i == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        backtrack(result, path, i + 1, nums);

        path.add(nums[i]);
        backtrack(result, path, i + 1, nums);
        path.removeLast();
    }
}
