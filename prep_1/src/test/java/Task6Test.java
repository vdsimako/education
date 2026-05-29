import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Task6Test {
    private static final Task6 solution = new Task6();

    @Test
    void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> solution.topFrequent(null, 1));
    }

    @Test
    void shouldReturnEmptyListByLimit() {
        List<String> actual = solution.topFrequent(List.of(), 0);
        assertEquals(List.of(), actual);

        actual = solution.topFrequent(List.of(), -1);
        assertEquals(List.of(), actual);
    }

    @Test
    void shouldReturnTopFrequent() {
        List<String> values = List.of("A", "A", "A", "B", "B", "C", "D");

        List<String> actual = solution.topFrequent(values, 2);

        List<String> expected = List.of("A", "B");

        assertEquals(expected, actual);
    }
}
