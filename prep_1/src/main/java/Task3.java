import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    public Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        if (words == null) {
            throw new IllegalArgumentException("words must not be null");
        }

        Map<Character, List<String>> result = new LinkedHashMap<>();

        for (String s : words) {
            if (s != null && !s.isBlank()) {
                char c = s.charAt(0);

                result
                        .computeIfAbsent(c, character -> new ArrayList<>())
                        .add(s);
            }
        }

        return result;
    }
}
