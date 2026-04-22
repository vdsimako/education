import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidParenthesesTest {
    private final ValidParentheses solution = new ValidParentheses();

    @ParameterizedTest
    @CsvSource({
            "'()', 'true'",
            "'()[]{}', 'true'",
            "'([])', 'true'",
            "'([)]', 'false'",
            "'(]', 'false'",
    })
    public void test(String s, boolean expected) {
        boolean actual = solution.isValid(s);
        assertEquals(expected, actual);
    }
}
