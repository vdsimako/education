package leetcode;/*
    link - https://leetcode.com/problems/valid-parentheses/
    20. Valid Parentheses
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
    determine if the input string is valid.

    An input string is valid if:
    1. Open brackets must be closed by the same type of brackets.
    2. Open brackets must be closed in the correct order.
    3. Every close bracket has a corresponding open bracket of the same type.
    "()[]{}"
    "([])"
    "([)]"
*/

import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    private final static Map<Character, Character> map = Map.ofEntries(
            Map.entry('(', ')'),
            Map.entry('{', '}'),
            Map.entry('[', ']')
    );

    public boolean isValid(String s) {
        int length = s.length();
        if (length % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));

        for (int i = 1; i < length; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            Character peek = stack.peek();
            if (map.containsKey(peek) && c == map.get(peek)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
