package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task63Test {
    private val task = Task63()

    @Test
    fun example1() {
        assertEquals(2, task.uniquePathsWithObstacles(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))))
    }

    @Test
    fun example2() {
        assertEquals(1, task.uniquePathsWithObstacles(arrayOf(intArrayOf(0, 1), intArrayOf(0, 0))))
    }

    @Test
    fun startBlocked() {
        assertEquals(0, task.uniquePathsWithObstacles(arrayOf(intArrayOf(1, 0))))
    }

    @Test
    fun example3() {
        assertEquals(3, task.uniquePathsWithObstacles(arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))))
    }

    @Test
    fun example4() {
        assertEquals(2, task.uniquePathsWithObstacles(arrayOf(intArrayOf(0, 1), intArrayOf(0, 0), intArrayOf(0, 0))))
    }
}
