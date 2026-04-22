import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LongestPalindromeTest {
    private final LongestPalindrome solution = new LongestPalindrome();

    @ParameterizedTest
    @CsvSource({
            "'babad', 'bab'",
            "'cbbd','bb'",
            "'a', 'a'",
            "'ac', 'a'",
            "'abac', 'aba'",
            "'cabba', 'abba'"
    })
    @DisplayName("Longest Palindrome")
    void test(String input, String expected) {
        assertEquals(expected, solution.longestPalindrome(input));
    }
}
