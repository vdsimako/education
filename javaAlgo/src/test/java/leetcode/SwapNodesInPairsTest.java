package leetcode;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SwapNodesInPairsTest {
    private final SwapNodesInPairs solution = new SwapNodesInPairs();

    public static Stream<Arguments> data() {

        return Stream.of(
                Arguments.of(
                        null,
                        null
                ),
                Arguments.of(
                        new SwapNodesInPairs.ListNode(1),
                        new SwapNodesInPairs.ListNode(1)
                ),
                Arguments.of(
                        createNodes(new int[]{1, 2}),
                        createNodes(new int[]{2, 1})
                ),
                Arguments.of(
                        createNodes(new int[]{1, 2, 3}),
                        createNodes(new int[]{2, 1, 3})
                ),
                Arguments.of(
                        createNodes(new int[]{1, 2, 3, 4}),
                        createNodes(new int[]{2, 1, 4, 3})
                )
        );
    }

    private static SwapNodesInPairs.ListNode createNodes(int[] ints) {
        SwapNodesInPairs.ListNode head = new SwapNodesInPairs.ListNode();
        SwapNodesInPairs.ListNode cur = head;
        for (int i : ints) {
            cur.next = new SwapNodesInPairs.ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }

    @ParameterizedTest
    @MethodSource("data")
    public void swapPairs(SwapNodesInPairs.ListNode head, SwapNodesInPairs.ListNode expected) {
        assertEquals(expected, solution.swapPairs(head));
    }
}
