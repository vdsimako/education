import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringToIntegerTest {
    private final StringToInteger solution = new StringToInteger();

    @ParameterizedTest
    @CsvSource({
            "'42', 42",
            "'-42', -42",
            "'+42', 42",
            "'   -01', -1",
            "'1337c0d3', 1337",
            "'0-1', 0",
            "'words and 987', 0",
            "'987 w', 987",
            "'-91283472332', -2147483648",
            "'.1', 0",
            "'   +0 123', 0"
    })
    @DisplayName("String to Integer(atoi) test")
    void test(String input, int expected) {
        assertEquals(expected, solution.myAtoi(input));
    }
}
