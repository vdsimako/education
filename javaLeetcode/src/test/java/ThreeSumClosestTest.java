import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ThreeSumClosestTest {
    private final ThreeSumClosest solution = new ThreeSumClosest();

    public static Stream<Arguments> data() {

        return Stream.of(
                Arguments.of(
                        new int[]{-1, 2, 1, -4},
                        1,
                        2
                ),
                Arguments.of(
                        new int[]{0, 0, 0},
                        1,
                        0
                ),
                Arguments.of(
                        new int[]{7,8,9},
                        -1,
                        24
                ),
                Arguments.of(
                        new int[]{0,1,2},
                        0,
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void threeSumClosest(int[] nums, int target, int expected) {
        assertEquals(expected, solution.threeSumClosest(nums, target));
    }
}
