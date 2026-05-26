import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DivideTwoIntegersTest {
    private final DivideTwoIntegers solution = new DivideTwoIntegers();

    @ParameterizedTest
    @CsvSource({
            "10, 3, 3",
            "7, 3, 2",
            "1, 3, 0",
            "7, -3, -2",
            "0, 1, 0",
            "1, 1, 1",
            "1, -1, -1",
            "-1, 1, -1",
            "-2147483648, -1, 2147483647",
            "2147483647, 1, 2147483647"
    })
    public void test(int dividend, int divisor, int expected) {
        assertEquals(expected, solution.divide(dividend, divisor));
    }
}
