import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task33Test {
    private static final Task33 solution = new Task33();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4
                ),
                Arguments.of(
                        new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1
                ),
                Arguments.of(
                        new int[]{1}, 0, -1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] nums, int target, int expected) {
        int actual = solution.search(nums, target);
        assertEquals(expected, actual);
    }
}
