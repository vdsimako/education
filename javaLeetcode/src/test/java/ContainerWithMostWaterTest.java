import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ContainerWithMostWaterTest {
    private final ContainerWithMostWater solution = new ContainerWithMostWater();

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49),
                Arguments.of(new int[]{1, 1}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void maxArea(
            int[] input,
            int expected
    ) {
        assertEquals(expected, solution.maxArea(input));
    }
}
