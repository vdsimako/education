package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IntegerToRomanTest {
    private final IntegerToRoman solution = new IntegerToRoman();

    @ParameterizedTest
    @CsvSource({
            "3749, 'MMMDCCXLIX'",
            "58, 'LVIII'",
            "1994, 'MCMXCIV'"
    })
    public void intToRoman(int num, String expected) {
        assertEquals(expected, solution.intToRoman(num));
    }
}
