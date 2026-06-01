package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongestSubstringWithoutRepeatingCharactersTest {

    private final LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

    @ParameterizedTest
    @CsvSource({
            "'aab', 2",
            "'', 0",
            "'a', 1",
            "'abcabcbb', 3",
            "'bbbbb', 1",
            "'pwwkew', 3",
            "'dvdf', 3",
            "'bbtablud', 6"
    })
    @DisplayName("Longest Substring Without Repeating Characters")
    void test(String input, int expected) {
        assertEquals(expected, solution.lengthOfLongestSubstring(input));
    }
}
