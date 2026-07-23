package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task54Test {
    private val task = Task54()

    @Test
    fun example1() {
        assertEquals(
            listOf(1, 2, 3, 6, 9, 8, 7, 4, 5),
            task.spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))),
        )
    }

    @Test
    fun example2() {
        assertEquals(
            listOf(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
            task.spiralOrder(
                arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12)),
            ),
        )
    }
}
