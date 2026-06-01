package backtrack;

import java.util.ArrayList;
import java.util.List;

public class Task6 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(result, new ArrayList<>(), nums, used);

        return result;
    }

    private void backtrack(List<List<Integer>> result,
                           ArrayList<Integer> path,
                           int[] nums,
                           boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i <= nums.length - 1; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtrack(result, path, nums, used);
            path.removeLast();
            used[i] = false;
        }

    }
}