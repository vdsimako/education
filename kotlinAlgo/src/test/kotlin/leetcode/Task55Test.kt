package leetcode

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Task55Test {
    private val task = Task55()

    @Test
    fun example1() {
        assertTrue(task.canJump(intArrayOf(2, 3, 1, 1, 4)))
    }

    @Test
    fun example2() {
        assertFalse(task.canJump(intArrayOf(3, 2, 1, 0, 4)))
    }

    @Test
    fun example3() {
        assertTrue(task.canJump(intArrayOf(1, 1, 1, 1, 0)))
    }
}
