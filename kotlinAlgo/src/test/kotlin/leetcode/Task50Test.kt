package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task50Test {
    private val task = Task50()
    private val delta = 1e-5

    @Test
    fun example1() {
        assertEquals(1024.00000, task.myPow(2.00000, 10), delta)
    }

    @Test
    fun example2() {
        assertEquals(9.26100, task.myPow(2.10000, 3), delta)
    }

    @Test
    fun example3() {
        assertEquals(0.25000, task.myPow(2.00000, -2), delta)
    }

    @Test
    fun zeroExponent() {
        assertEquals(1.0, task.myPow(2.00000, 0), delta)
    }
}
