package leetcode

/*
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 500].
 * - -100 <= Node.val <= 100
 * - 0 <= k <= 2^31 - 1
 */
class Task61 {
    class ListNode(
        var `val`: Int = 0,
        var next: ListNode? = null,
    ) {
        override fun equals(other: Any?): Boolean {
            if (other !is ListNode) return false
            return `val` == other.`val` && next == other.next
        }

        override fun hashCode(): Int = java.util.Objects.hash(`val`, next)
        override fun toString(): String {
            return "ListNode(`val`=$`val`, next=$next)"
        }


    }

    fun rotateRight(
        head: ListNode?,
        k: Int,
    ): ListNode? {
        if (head == null) {
            return head
        }
        var length = 0
        var curr = head
        while (true) {
            length++
            if (curr?.next == null) {
                break
            }
            curr = curr.next
        }

        curr?.next = head

        var tail = head
        repeat(length - k % length - 1) {
            tail = tail?.next
        }

        val result = tail?.next
        tail?.next = null

        return result
    }
}
