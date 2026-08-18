package leetcode

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Task66Test {
    private val task = Task66()

    @Test
    fun example1() {
        assertArrayEquals(intArrayOf(1, 2, 4), task.plusOne(intArrayOf(1, 2, 3)))
    }

    @Test
    fun example2() {
        assertArrayEquals(intArrayOf(4, 3, 2, 2), task.plusOne(intArrayOf(4, 3, 2, 1)))
    }

    @Test
    fun example3() {
        assertArrayEquals(intArrayOf(1, 0), task.plusOne(intArrayOf(9)))
    }

    @Test
    fun example4() {
        assertArrayEquals(intArrayOf(1, 2, 9), task.plusOne(intArrayOf(1, 2, 8)))
    }

    @Test
    fun allNines() {
        assertArrayEquals(intArrayOf(1, 0, 0, 0), task.plusOne(intArrayOf(9, 9, 9)))
    }
}
