package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class Task49Test {

    private final Task49 task = new Task49();

    private static List<List<String>> canonical(List<List<String>> groups) {
        List<List<String>> sorted = new ArrayList<>();
        for (List<String> group : groups) {
            List<String> copy = new ArrayList<>(group);
            Collections.sort(copy);
            sorted.add(copy);
        }
        sorted.sort((a, b) -> String.join(",", a).compareTo(String.join(",", b)));
        return sorted;
    }

    @Test
    void example1() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected = List.of(
                List.of("bat"),
                List.of("nat", "tan"),
                List.of("ate", "eat", "tea")
        );
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)));
    }

    @Test
    void example2() {
        String[] strs = {""};
        List<List<String>> expected = List.of(List.of(""));
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)));
    }

    @Test
    void example3() {
        String[] strs = {"a"};
        List<List<String>> expected = List.of(List.of("a"));
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)));
    }

    @Test
    void noAnagramsPresent() {
        String[] strs = {"abc", "def", "ghi"};
        List<List<String>> expected = List.of(
                List.of("abc"),
                List.of("def"),
                List.of("ghi")
        );
        assertEquals(canonical(expected), canonical(task.groupAnagrams(strs)));
    }
}
