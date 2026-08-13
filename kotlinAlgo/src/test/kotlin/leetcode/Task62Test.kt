package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task62Test {
    private val task = Task62()

    @Test
    fun example1() {
        assertEquals(28, task.uniquePaths(3, 7))
    }

    @Test
    fun example2() {
        assertEquals(3, task.uniquePaths(3, 2))
    }

    @Test
    fun singleCell() {
        assertEquals(1, task.uniquePaths(1, 1))
    }
}
