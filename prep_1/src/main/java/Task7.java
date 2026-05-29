import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
Handle null input list explicitly.
Ignore null users inside the list.
If user.id is null, throw IllegalArgumentException.
If duplicate id exists, throw IllegalArgumentException.
Preserve original user order in the result map.
Use clean Java code.
Explain time and space complexity.

*/
public class Task7 {
    public Map<Long, User> indexById(List<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("input data must not be null");
        }

        Map<Long, User> result = new LinkedHashMap<>();

        for (User user : users) {
            if (user == null) {
                continue;
            }
            Long id = user.id();
            if (id == null) {
                throw new IllegalArgumentException("user id must not be null");
            }

            if (result.containsKey(id)) {
                throw new IllegalArgumentException("user id duplicated");
            }

            result.put(id, user);
        }

        return result;
    }

    public record User(Long id, String email) {
    }
}

