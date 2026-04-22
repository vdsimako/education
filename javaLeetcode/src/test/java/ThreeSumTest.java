import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ThreeSumTest {
    private final ThreeSum solution = new ThreeSum();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{-1, 0, 1, 2, -1, -4},
                        "[[-1, -1, 2], [-1, 0, 1]]"
                ),
                Arguments.of(new int[]{0, 1, 1}, "[]"),
                Arguments.of(new int[]{0, 0, 0}, "[[0, 0, 0]]"),
                Arguments.of(new int[]{1, 1, -2}, "[[-2, 1, 1]]")
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("3Sum test")
    public void test(int[] nums, String expected) {
        assertEquals(expected, solution.threeSum(nums).toString());
    }
}
