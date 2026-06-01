import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task34Test {
    private static final Task34 solution = new Task34();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4}
                ),
                Arguments.of(
                        new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1}
                ),
                Arguments.of(
                        new int[]{}, 0, new int[]{-1, -1}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] nums, int target, int[] expected) {
        int[] actual = solution.searchRange(nums, target);

        assertArrayEquals(expected, actual);
    }
}
