package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task38Test {
    private static final Task38 solution = new Task38();

    @ParameterizedTest
    @CsvSource({
            "1, '1'",
            "2, '11'",
            "3, '21'",
            "4, '1211'",
            "5, '111221'",
            "6, '312211'"
    })
    void test(int n, String expected) {
        String actual = solution.countAndSay(n);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "'1', '11'",
            "'11', '21'",
            "'111', '31'",
            "'5111', '1531'",
            "'1211', '111221'"
    })
    void testDescribe(String s, String expected) {
        String actual = solution.describe(s);

        assertEquals(expected, actual);
    }
}
