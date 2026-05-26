import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RemoveNthFromEndTest {
    private final RemoveNthFromEnd solution = new RemoveNthFromEnd();

    private static RemoveNthFromEnd.ListNode createListNode(int[] nums) {
        RemoveNthFromEnd.ListNode dummy = new RemoveNthFromEnd.ListNode();
        RemoveNthFromEnd.ListNode curr = dummy;
        for (int val : nums) {
            curr.next = new RemoveNthFromEnd.ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        null,
                        createListNode(new int[]{1}),
                        1
                ),
                Arguments.of(
                        createListNode(new int[]{1}),
                        createListNode(new int[]{1, 2}),
                        1
                ),
                Arguments.of(
                        createListNode(new int[]{2}),
                        createListNode(new int[]{1, 2}),
                        2
                ),
                Arguments.of(
                        createListNode(new int[]{1, 2, 3, 5}),
                        createListNode(new int[]{1, 2, 3, 4, 5}),
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveNthFromEnd(RemoveNthFromEnd.ListNode expected, RemoveNthFromEnd.ListNode head, int n) {
        assertEquals(expected, solution.removeNthFromEnd(head, n));
    }
}
