package leetcode

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Task56Test {
    private val task = Task56()

    @Test
    fun example1() {
        assertArrayEquals(
            arrayOf(intArrayOf(1, 6), intArrayOf(8, 10), intArrayOf(15, 18)),
            task.merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))),
        )
    }

    @Test
    fun example2() {
        assertArrayEquals(
            arrayOf(intArrayOf(1, 5)),
            task.merge(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))),
        )
    }

    @Test
    fun example3() {
        assertArrayEquals(
            arrayOf(intArrayOf(0, 4)),
            task.merge(arrayOf(intArrayOf(1, 4), intArrayOf(0, 4))),
        )
    }

    @Test
    fun example4() {
        assertArrayEquals(
            arrayOf<IntArray?>(intArrayOf(1, 10)),
            task.merge(arrayOf(intArrayOf(1, 10), intArrayOf(2, 3), intArrayOf(4, 5)))
        )
    }
}
