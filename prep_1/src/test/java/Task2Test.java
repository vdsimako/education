import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {
    private static final Task2 task2 = new Task2();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        List.of("A", "B", "A", "C", "B", "D"),
                        "C"
                ),
                Arguments.of(
                        List.of("A", "A", "B", "B"),
                        null
                ),
                Arguments.of(
                        List.of("A"),
                        "A"
                ),
                Arguments.of(
                        List.of(),
                        null
                ),
                Arguments.of(
                        List.of("A", "B", "A", "C", "C"),
                        "B"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void test(List<String> values, String expected) {
        String actual = task2.findFirstUnique(values);
        assertEquals(expected, actual);
    }
}
