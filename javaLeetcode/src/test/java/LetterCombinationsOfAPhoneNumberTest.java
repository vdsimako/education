import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LetterCombinationsOfAPhoneNumberTest {
    private final LetterCombinationsOfAPhoneNumber solution = new LetterCombinationsOfAPhoneNumber();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        "2",
                        List.of("a", "b", "c")
                ),
                Arguments.of(
                        "23",
                        List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
                ),
                Arguments.of(
                        "29",
                        List.of("aw", "ax", "ay", "az", "bw", "bx", "by", "bz", "cw", "cx", "cy", "cz")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("17. Letter Combinations of a Phone Number")
    public void test(String input, List<String> expected) {
        assertEquals(expected, solution.letterCombinations(input));
    }
}
