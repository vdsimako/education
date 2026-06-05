package leetcode;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GenerateParenthesesTest {
    private final GenerateParentheses solution = new GenerateParentheses();

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of("()")
                ),
                Arguments.of(
                        2,
                        List.of("(())", "()()")
                ),
                Arguments.of(
                        3,
                        List.of("((()))","(()())","(())()","()(())","()()()")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void generateParenthesisTest(int n, List<String> expected) {
        assertEquals(expected, solution.generateParenthesis(n));
    }
}
