package backtrack;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task3Test {
    private static final Task3 solution = new Task3();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{1},
                        List.of(
                                List.of(),
                                List.of(1)
                        )
                ),
                Arguments.of(
                        new int[]{1, 2},
                        List.of(
                                List.of(),
                                List.of(1),
                                List.of(2),
                                List.of(1, 2)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> actual = solution.subsets(nums);

        assertEqualsIgnoringOrder(expected, actual);
    }

    private void assertEqualsIgnoringOrder(List<List<Integer>> expected, List<List<Integer>> actual) {
        Comparator<List<Integer>> cmp = Comparator.comparing(Object::toString);
        assertEquals(
            expected.stream().sorted(cmp).collect(Collectors.toList()),
            actual.stream().sorted(cmp).collect(Collectors.toList())
        );
    }
}
