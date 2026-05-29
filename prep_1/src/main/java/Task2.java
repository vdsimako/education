import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task2 {
    public String findFirstUnique(List<String> values) {
        if (values == null) {
            throw new IllegalArgumentException("values must not be null");
        }

        Map<String, Integer> frequencyByValue = new LinkedHashMap<>();
        for (String s : values) {
            frequencyByValue.put(
                    s,
                    frequencyByValue.getOrDefault(s, 0) + 1
            );
        }

        for (Map.Entry<String, Integer> entry : frequencyByValue.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null;
    }
}
