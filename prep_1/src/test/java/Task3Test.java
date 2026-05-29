import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {

    private final Task3 solution = new Task3();

    @Test
    void shouldGroupWordsByFirstLetter() {
        Map<Character, List<String>> expected = new LinkedHashMap<>();
        expected.put('a', List.of("apple", "apricot"));
        expected.put('b', List.of("banana", "blueberry"));
        expected.put('c', List.of("cherry"));

        Map<Character, List<String>> actual = solution.groupByFirstLetter(
                List.of("apple", "banana", "apricot", "blueberry", "cherry")
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldIgnoreNullAndBlankWords() {
        Map<Character, List<String>> expected = new LinkedHashMap<>();
        expected.put('a', List.of("apple"));
        expected.put('b', List.of("banana"));

        Map<Character, List<String>> actual = solution.groupByFirstLetter(
                java.util.Arrays.asList(null, "", "   ", "apple", "banana")
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyMapForEmptyInput() {
        assertEquals(Map.of(), solution.groupByFirstLetter(List.of()));
    }

    @Test
    void shouldThrowExceptionForNullInput() {
        assertThrows(
                IllegalArgumentException.class,
                () -> solution.groupByFirstLetter(null)
        );
    }
}