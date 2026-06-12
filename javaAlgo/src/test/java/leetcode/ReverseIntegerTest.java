package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReverseIntegerTest {
    private final ReverseInteger solution = new ReverseInteger();

    @ParameterizedTest
    @CsvSource({
            "123, 321",
            "-123, -321",
            "120, 21",
            "1534236469, 0"
    })
    @DisplayName("Reverse integer test")
    void test(int input, int expected) {
        assertEquals(expected, solution.reverse(input));
    }
}
