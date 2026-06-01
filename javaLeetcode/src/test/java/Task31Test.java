import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task31Test {
    private static final Task31 solution = new Task31();

    public static Stream<Arguments> data() {

        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3}, new int[]{1, 3, 2}
                ),
                Arguments.of(
                        new int[]{3, 2, 1}, new int[]{1, 2, 3}
                ),
                Arguments.of(
                        new int[]{1, 1, 5}, new int[]{1, 5, 1}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] nums, int[] expected) {
        solution.nextPermutation(nums);

        assertArrayEquals(expected, nums);
    }
}
