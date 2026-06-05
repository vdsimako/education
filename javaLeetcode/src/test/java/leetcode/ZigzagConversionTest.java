package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ZigzagConversionTest {
    private final ZigzagConversion solution = new ZigzagConversion();

    @ParameterizedTest
    @CsvSource({
            "'PAYPALISHIRING', 3, 'PAHNAPLSIIGYIR'",
            "'PAYPALISHIRING', 4, 'PINALSIGYAHRPI'",
            "'A', 1, 'A'",
            "'AB', 1, 'AB'",
    })
    @DisplayName("Zigzag Conversion")
    void test(String input,
              int numRows,
              String expected) {
        assertEquals(expected, solution.convert(input, numRows));
    }
}
