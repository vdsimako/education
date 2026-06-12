package leetcode;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task35Test {
    private static final Task35 solution = new Task35();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 3, 5, 6}, 5, 2
                ),
                Arguments.of(
                        new int[]{1, 3, 5, 6}, 2, 1
                ),
                Arguments.of(
                        new int[]{1, 3, 5, 6}, 7, 4
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] nums, int target, int expected) {
        int actual = solution.searchInsert(nums, target);

        assertEquals(expected, actual);
    }
}
