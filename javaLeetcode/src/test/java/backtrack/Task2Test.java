package backtrack;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {
    private static final Task2 solution = new Task2();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                List.of(1),
                                List.of(2),
                                List.of(3),
                                List.of(4),
                                List.of(5),
                                List.of(6)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                List.of(1, 1),
                                List.of(1, 2),
                                List.of(1, 3),
                                List.of(1, 4),
                                List.of(1, 5),
                                List.of(1, 6),

                                List.of(2, 1),
                                List.of(2, 2),
                                List.of(2, 3),
                                List.of(2, 4),
                                List.of(2, 5),
                                List.of(2, 6),

                                List.of(3, 1),
                                List.of(3, 2),
                                List.of(3, 3),
                                List.of(3, 4),
                                List.of(3, 5),
                                List.of(3, 6),

                                List.of(4, 1),
                                List.of(4, 2),
                                List.of(4, 3),
                                List.of(4, 4),
                                List.of(4, 5),
                                List.of(4, 6),

                                List.of(5, 1),
                                List.of(5, 2),
                                List.of(5, 3),
                                List.of(5, 4),
                                List.of(5, 5),
                                List.of(5, 6),

                                List.of(6, 1),
                                List.of(6, 2),
                                List.of(6, 3),
                                List.of(6, 4),
                                List.of(6, 5),
                                List.of(6, 6)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int n, List<List<Integer>> expected) {
        List<List<Integer>> actual = solution.generateDiceRolls(n);

        assertEquals(expected, actual);
    }
}
