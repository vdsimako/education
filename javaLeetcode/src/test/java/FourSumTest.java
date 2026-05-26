import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FourSumTest {
    private final FourSum solution = new FourSum();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 0, -1, 0, -2, 2},
                        0,
                        "[[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]"
                ),
                Arguments.of(
                        new int[]{2, 2, 2, 2, 2},
                        8,
                        "[[2, 2, 2, 2]]"
                ),
                Arguments.of(
                        new int[]{-2, -1, -1, 1, 1, 2, 2},
                        0,
                        "[[-2, -1, 1, 2], [-1, -1, 1, 1]]"
                ),
                Arguments.of(
                        new int[]{1000000000, 1000000000, 1000000000, 1000000000},
                        -294967296,
                        "[]"

                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void fourSum(int[] nums, int target, String expected) {
        assertEquals(expected, solution.fourSum(nums, target).toString());
    }
}
