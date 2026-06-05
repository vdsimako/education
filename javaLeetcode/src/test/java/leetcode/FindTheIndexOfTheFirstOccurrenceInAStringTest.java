package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FindTheIndexOfTheFirstOccurrenceInAStringTest {
    private static final FindTheIndexOfTheFirstOccurrenceInAString solution = new FindTheIndexOfTheFirstOccurrenceInAString();

    @ParameterizedTest
    @CsvSource({
            "'sadbutsad', 'sad', 0",
            "'leetcode', 'leeto', -1"
    })
    public void test(String haystack, String needle, int expected) {
        int actual = solution.strStr(haystack, needle);

        assertEquals(expected, actual);
    }
}
