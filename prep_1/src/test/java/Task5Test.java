import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Task5Test {
    private static final Task5 solution = new Task5();

    @Test
    void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> solution.findCommonValues(null, null));
        assertThrows(IllegalArgumentException.class, () -> solution.findCommonValues(List.of(), null));
        assertThrows(IllegalArgumentException.class, () -> solution.findCommonValues(null, List.of()));
    }

    @Test
    void shouldReturnEmptyList() {
        List<String> actual = solution.findCommonValues(List.of("A", "B"), List.of("C", "D"));
        assertEquals(List.of(), actual);
    }

    @Test
    void shouldReturnCommon() {
        List<String> actual = solution.findCommonValues(List.of("A", "C", "B", "D"), List.of("A", "D", "C"));

        assertEquals(List.of("A", "C", "D"), actual);
    }

    @Test
    void shouldIgnoreNullValues() {
        List<String> actual = solution.findCommonValues(Arrays.asList("A", "C", "B", "D", null), Arrays.asList("A", "D", "C"));

        assertEquals(List.of("A", "C", "D"), actual);
    }
}
