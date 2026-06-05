package backtrack;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task1Test {
    private static final Task1 task1 = new Task1();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        1, List.of("0", "1")
                ),
                Arguments.of(
                        2, List.of(
                                "00",
                                "01",
                                "10",
                                "11"
                        )
                ),
                Arguments.of(
                        3, List.of(
                                "000",
                                "001",
                                "010",
                                "011",
                                "100",
                                "101",
                                "110",
                                "111"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(int n, List<String> expected) {
        List<String> actual = task1.generateBinaryStrings(n);

        assertEquals(expected, actual);
    }
}
