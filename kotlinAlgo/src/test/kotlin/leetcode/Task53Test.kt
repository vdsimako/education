package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task53Test {
    private val task = Task53()

    @Test
    fun example1() {
        assertEquals(6, task.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }

    @Test
    fun example2() {
        assertEquals(1, task.maxSubArray(intArrayOf(1)))
    }

    @Test
    fun example3() {
        assertEquals(23, task.maxSubArray(intArrayOf(5, 4, -1, 7, 8)))
    }
}
