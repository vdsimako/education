package leetcode

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Task57Test {
    private val task = Task57()

    @Test
    fun example1() {
        assertArrayEquals(
            arrayOf(intArrayOf(1, 5), intArrayOf(6, 9)),
            task.insert(arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)), intArrayOf(2, 5)),
        )
    }

    @Test
    fun example2() {
        assertArrayEquals(
            arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16)),
            task.insert(
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)),
                intArrayOf(4, 8),
            ),
        )
    }

    @Test
    fun example3() {
        assertArrayEquals(
            arrayOf(intArrayOf(5, 7)),
            task.insert(arrayOf(), intArrayOf(5, 7)),
        )
    }

    @Test
    fun example4() {
        assertArrayEquals(
            arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7)),
            task.insert(arrayOf(intArrayOf(1, 2), intArrayOf(6, 7)), intArrayOf(3, 5)),
        )
    }
}
