package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task58Test {
    private val task = Task58()

    @Test
    fun example1() {
        assertEquals(5, task.lengthOfLastWord("Hello World"))
    }

    @Test
    fun example2() {
        assertEquals(4, task.lengthOfLastWord("   fly me   to   the moon  "))
    }

    @Test
    fun example3() {
        assertEquals(6, task.lengthOfLastWord("luffy is still joyboy"))
    }
}
