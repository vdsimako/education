package greedy;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task1Test {

    private static final Task1 solution = new Task1();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 3, 2, 4, 1},
                        7,
                        4
                ),
                Arguments.of(
                        new int[]{10,6,8,7,7,8},
                        5,
                        0
                ),
                Arguments.of(
                        new int[]{1,6,3,1,2,5},
                        20,
                        6
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] costs, int coins, int expected) {
        int actual = solution.count(costs, coins);

        assertEquals(expected, actual);
    }
}