import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task1 {
    public List<String> findDuplicates(List<String> values) {
        if (values == null) {
            return List.of();
        }

        Set<String> seenValues = new HashSet<>();
        Set<String> result = new LinkedHashSet<>();

        for (String s : values) {
            if (!seenValues.add(s)) {
                result.add(s);
            }
        }

        return new ArrayList<>(result);
    }
}
