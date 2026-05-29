import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task4 {
    public List<String> mergeUnique(List<String> first, List<String> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("input data must not be null");
        }
        Set<String> uniqueValues = new LinkedHashSet<>();
        addValues(first, uniqueValues);
        addValues(second, uniqueValues);
        return new ArrayList<>(uniqueValues);
    }

    private void addValues(List<String> values, Set<String> uniqueValues) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                uniqueValues.add(value);
            }
        }
    }
}
