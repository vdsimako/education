package backtrack;

/*
1. Generate all binary strings of length n
Example:
n = 3
000
001
010
011
100
101
110
111
*/

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), n);
        return result;
    }

    private void backtrack(List<String> result,
                           StringBuilder path,
                           int n) {
        if (path.length() == n) {
            result.add(path.toString());
            return;
        }

        path.append("0");
        backtrack(result, path, n);
        path.deleteCharAt(path.length() - 1);

        path.append("1");
        backtrack(result, path, n);
        path.deleteCharAt(path.length() - 1);
    }
}
