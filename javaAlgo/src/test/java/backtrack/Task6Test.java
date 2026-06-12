package backtrack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {
    private static final Task6 solution = new Task6();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        List.of(
                                List.of(1)
                        )
                ),
                Arguments.of(
                        new int[]{1, 2},
                        List.of(
                                List.of(1, 2),
                                List.of(2, 1)
                        )
                ),
                Arguments.of(
                        new int[]{1, 2, 3},
                        List.of(
                                List.of(1, 2, 3),
                                List.of(1, 3, 2),
                                List.of(2, 1, 3),
                                List.of(2, 3, 1),
                                List.of(3, 1, 2),
                                List.of(3, 2, 1)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> actual = solution.permute(nums);

        assertEqualsIgnoringOrder(expected, actual);
    }

    private void assertEqualsIgnoringOrder(List<List<Integer>> expected,
                                           List<List<Integer>> actual) {
        Comparator<List<Integer>> cmp = Comparator.comparing(Object::toString);

        assertEquals(
                expected.stream().sorted(cmp).collect(Collectors.toList()),
                actual.stream().sorted(cmp).collect(Collectors.toList())
        );
    }
}