package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
 * Task 4 — Generate subsets with fixed size k
 * */

public class Task4 {
    public List<List<Integer>> subsetsOfSizeK(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), 0, nums, k);

        return result;
    }

    private void backtrack(List<List<Integer>> result,
                           ArrayList<Integer> path,
                           int start,
                           int[] nums,
                           int k) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= nums.length - 1; i++) {
            path.add(nums[i]);

            backtrack(result, path, i + 1, nums, k);

            path.removeLast();
        }
    }
}
