package backtrack;

import java.util.ArrayList;
import java.util.List;

public class Task5 {
    public List<List<Integer>> combine(int n,
                                       int k) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), 1, n, k);

        return result;
    }

    private void backtrack(List<List<Integer>> result,
                           ArrayList<Integer> path,
                           int start,
                           int n,
                           int k) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrack(result, path, i + 1, n, k);
            path.removeLast();
        }

    }
}
