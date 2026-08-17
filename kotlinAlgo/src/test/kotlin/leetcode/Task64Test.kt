package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task64Test {
    private val task = Task64()

    @Test
    fun example1() {
        assertEquals(7, task.minPathSum(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))))
    }

    @Test
    fun example2() {
        assertEquals(12, task.minPathSum(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))))
    }

    @Test
    fun singleCell() {
        assertEquals(5, task.minPathSum(arrayOf(intArrayOf(5))))
    }
}
