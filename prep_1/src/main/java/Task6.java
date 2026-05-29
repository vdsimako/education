import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task6 {
    public List<String> topFrequent(List<String> values, int limit) {
        if (values == null) {
            throw new IllegalArgumentException("input values must not be null");
        }

        if (limit <= 0) {
            return List.of();
        }

        Map<String, Integer> frequencyByValue = new LinkedHashMap<>();

        for (String value : values) {
            if (value == null) {
                continue;
            }
            frequencyByValue.put(value, frequencyByValue.getOrDefault(value, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencyByValue.entrySet());

        entries.sort((left, right) -> Integer.compare(right.getValue(), left.getValue()));

        List<String> result = new ArrayList<>();
        int resultSize = Math.min(limit, entries.size());

        for (int i = 0; i < resultSize; i++) {
            result.add(entries.get(i).getKey());
        }

        return result;
    }
}
