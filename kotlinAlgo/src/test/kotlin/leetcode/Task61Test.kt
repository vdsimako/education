package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task61Test {
    private val task = Task61()

    private fun createNodes(values: IntArray): Task61.ListNode? {
        val dummy = Task61.ListNode()
        var curr = dummy
        for (v in values) {
            curr.next = Task61.ListNode(v)
            curr = curr.next!!
        }
        return dummy.next
    }

    @Test
    fun example1() {
        assertEquals(
            createNodes(intArrayOf(4, 5, 1, 2, 3)),
            task.rotateRight(createNodes(intArrayOf(1, 2, 3, 4, 5)), 2),
        )
    }

    @Test
    fun example2() {
        assertEquals(
            createNodes(intArrayOf(2, 0, 1)),
            task.rotateRight(createNodes(intArrayOf(0, 1, 2)), 4),
        )
    }

    @Test
    fun example3() {
        assertEquals(
            createNodes(intArrayOf(1, 0)),
            task.rotateRight(createNodes(intArrayOf(0, 1)), 1),
        )
    }

    @Test
    fun example4() {
        assertEquals(
            createNodes(intArrayOf(0, 1)),
            task.rotateRight(createNodes(intArrayOf(0, 1)), 2),
        )
    }

    @Test
    fun example5() {
        assertEquals(
            createNodes(intArrayOf()),
            task.rotateRight(createNodes(intArrayOf()), 1),
        )
    }

    @Test
    fun example6() {
        assertEquals(
            createNodes(intArrayOf(1)),
            task.rotateRight(createNodes(intArrayOf(1)), 1),
        )
    }
}
