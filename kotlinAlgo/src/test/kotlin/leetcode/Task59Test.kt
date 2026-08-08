package leetcode

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Task59Test {
    private val task = Task59()

    @Test
    fun example1() {
        assertArrayEquals(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(8, 9, 4), intArrayOf(7, 6, 5)),
            task.generateMatrix(3),
        )
    }

    @Test
    fun example2() {
        assertArrayEquals(
            arrayOf(intArrayOf(1)),
            task.generateMatrix(1),
        )
    }
}
