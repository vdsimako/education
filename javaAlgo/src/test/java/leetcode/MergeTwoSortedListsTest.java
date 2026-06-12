package leetcode;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MergeTwoSortedListsTest {
    private static final MergeTwoSortedLists solution = new MergeTwoSortedLists();

    private static MergeTwoSortedLists.ListNode createListNode(int[] nums) {
        MergeTwoSortedLists.ListNode dummy = new MergeTwoSortedLists.ListNode();
        MergeTwoSortedLists.ListNode curr = dummy;
        for (int val : nums) {
            curr.next = new MergeTwoSortedLists.ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        null,
                        null,
                        null
                ),
                Arguments.of(
                        null,
                        createListNode(new int[]{0}),
                        createListNode(new int[]{0})
                ),
                Arguments.of(
                        createListNode(new int[]{1, 2, 3, 4}),
                        createListNode(new int[]{0, 1, 1, 2, 3,}),
                        createListNode(new int[]{0, 1, 1, 1, 2, 2, 3, 3, 4})
                )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void test(MergeTwoSortedLists.ListNode node1,
                     MergeTwoSortedLists.ListNode node2,
                     MergeTwoSortedLists.ListNode expected) {
        MergeTwoSortedLists.ListNode actual = solution.mergeTwoLists(node1, node2);

        assertEquals(expected, actual);
    }
}
