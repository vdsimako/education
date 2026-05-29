import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Task4Test {
    private static final Task4 solution = new Task4();

    @Test
    public void shouldThrowExceptionForNullInput() {
        assertThrows(IllegalArgumentException.class, () -> solution.mergeUnique(null, List.of()));
        assertThrows(IllegalArgumentException.class, () -> solution.mergeUnique(List.of(), null));
        assertThrows(IllegalArgumentException.class, () -> solution.mergeUnique(null, null));
    }

    @Test
    void shouldReturnEmptyListForEmptyInput() {
        assertEquals(List.of(), solution.mergeUnique(List.of(), List.of()));
    }

    @Test
    void shouldMergeUnique() {
        List<String> actual = solution.mergeUnique(List.of("A", "B", "C"), List.of("B", "D", "A", "E"));
        List<String> expected = List.of("A", "B", "C", "D", "E");
        assertEquals(expected, actual);
    }

    @Test
    void shouldIgnoreNullOrBlank() {
        List<String> actual = solution.mergeUnique(Arrays.asList("A", null, "B", "", "    "), List.of("B", "D", "A", "E"));
        List<String> expected = List.of("A", "B", "D", "E");
        assertEquals(expected, actual);
    }
}
