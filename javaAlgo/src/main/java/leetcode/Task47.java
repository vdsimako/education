package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 47. Permutations II
 * https://leetcode.com/problems/permutations-ii/
 *
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: [[1,1,2],[1,2,1],[2,1,1]]
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 * - 1 <= nums.length <= 8
 * - -10 <= nums[i] <= 10
 */
public class Task47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

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
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
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
