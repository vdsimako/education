import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Task7Test {
    private static final Task7 solution = new Task7();

    @Test
    void shouldThrowExceptionOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> solution.indexById(null));
    }

    @Test
    void shouldThrowExceptionOnNullUserId() {
        assertThrows(IllegalArgumentException.class, () -> solution.indexById(List.of(new Task7.User(null, "test"))));
    }

    @Test
    void shouldThrowExceptionOnDuplicateUserId() {
        assertThrows(IllegalArgumentException.class, () -> solution.indexById(
                        List.of(
                                new Task7.User(1L, "test"),
                                new Task7.User(1L, "test1")
                        )
                )
        );
    }

    @Test
    void shouldReturnMapOfUsers() {
        Task7.User user1 = new Task7.User(1L, "test");
        Task7.User user2 = new Task7.User(2L, "test1");
        final var users = List.of(
                user1,
                user2
        );
        final var actual = solution.indexById(users);

        final var expected = Map.ofEntries(
                Map.entry(user1.id(), user1),
                Map.entry(user2.id(), user2)
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldIgnoreNullUserInList() {
        Task7.User user1 = new Task7.User(1L, "test");
        Task7.User user2 = new Task7.User(2L, "test1");
        final var users = Arrays.asList(
                user1,
                null,
                user2,
                null
        );
        final var actual = solution.indexById(users);

        final var expected = Map.ofEntries(
                Map.entry(user1.id(), user1),
                Map.entry(user2.id(), user2)
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldPreserveOriginalUserOrder() {
        Task7.User user1 = new Task7.User(10L, "a@test.com");
        Task7.User user2 = new Task7.User(5L, "b@test.com");
        Task7.User user3 = new Task7.User(20L, "c@test.com");

        Map<Long, Task7.User> actual = solution.indexById(
                List.of(user1, user2, user3)
        );

        assertEquals(
                List.of(10L, 5L, 20L),
                new ArrayList<>(actual.keySet())
        );
    }
}
