import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RemoveDuplicatesFromSortedArrayTest {
    private static final RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 1, 2},
                        2,
                        new int[]{1, 2}
                ),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                        5,
                        new int[]{0, 1, 2, 3, 4}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void removeDuplicates(int[] nums, int k, int[] expectedNums) {
        int actual = solution.removeDuplicates(nums);

        assertEquals(k, actual);
        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }
}
