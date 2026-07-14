package leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Task49Test {
    private val task = Task49()

    private fun canonical(groups: List<List<String>>): List<List<String>> = groups.map { it.sorted() }.sortedBy { it.joinToString(",") }

    @Test
    fun example1() {
        val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
        val expected = listOf(listOf("bat"), listOf("nat", "tan"), listOf("ate", "eat", "tea"))
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)))
    }

    @Test
    fun example2() {
        val strs = arrayOf("")
        val expected = listOf(listOf(""))
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)))
    }

    @Test
    fun example3() {
        val strs = arrayOf("a")
        val expected = listOf(listOf("a"))
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)))
    }

    @Test
    fun noAnagramsPresent() {
        val strs = arrayOf("abc", "def", "ghi")
        val expected = listOf(listOf("abc"), listOf("def"), listOf("ghi"))
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)))
    }
}
