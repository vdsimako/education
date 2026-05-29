import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task5 {
    public List<String> findCommonValues(List<String> first, List<String> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("input data must not be null");
        }
        Set<String> firstValues = new LinkedHashSet<>(first);
        Set<String> secondValues = new HashSet<>(second);
        List<String> result = new ArrayList<>();

        for (String value : firstValues) {
            if (value == null) {
                continue;
            }
            if (secondValues.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }
}
