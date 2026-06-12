package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    private Map<Character, String> digitToLetters = new HashMap<>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(0, digits, new StringBuilder(), result);

        return result;
    }

    private void backtrack(int index,
                           String digits,
                           StringBuilder sb,
                           List<String> result) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String letters = digitToLetters.get(digits.charAt(index));

        for (char letter : letters.toCharArray()) {
            sb.append(letter);
            backtrack(index + 1, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
