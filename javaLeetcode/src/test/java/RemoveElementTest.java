import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RemoveElementTest {
    private static final RemoveElement solution = new RemoveElement();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{},
                        0,
                        0,
                        new int[]{}
                ),
                Arguments.of(
                        new int[]{1, 1, 1},
                        2,
                        3,
                        new int[]{1, 1, 1}
                ),
                Arguments.of(
                        new int[]{3, 2, 2, 3},
                        3,
                        2,
                        new int[]{2, 2, -1, -1}
                ),
                Arguments.of(
                        new int[]{0, 1, 2, 2, 4, 0, 3, 2},
                        2,
                        5,
                        new int[]{0, 1, 4, 0, 3}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void test(int[] nums,
                     int val,
                     int k,
                     int[] expectedNums) {
        int actual = solution.removeElement(nums, val);

        assertEquals(k, actual);
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }
}
