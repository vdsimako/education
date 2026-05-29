import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task1Test {
    private static final Task1 solution = new Task1();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(List.of("A", "A", "B", "C", "B"), List.of("A", "B")),
                Arguments.of(List.of("A", "A", "A"), List.of("A")),
                Arguments.of(null, null)
        );
    }


    @ParameterizedTest
    @MethodSource("data")
    public void test(List<String> values, List<String> expected) {
        List<String> actual = solution.findDuplicates(values);
        assertEquals(expected, actual);
    }
}
