import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int startIndex = 0;

        for (int i = 1; i <= s.length(); ++i) {
            String sub = s.substring(startIndex, i);
            int count = 0;
            Map<String, Integer> charMap = new HashMap<>();
            for (String c : sub.split("")) {
                if (charMap.containsKey(c)) {
                    startIndex += charMap.get(c)+ 1;
                    break;
                }
                charMap.put(c, count);
                count += 1;
            }

            if (count > result) {
                result = count;
            }
        }

        return result;
    }
}
