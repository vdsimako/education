import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerToRoman {
    private static final Map<Integer, String> ROMANS = new HashMap<Integer, String>() {{
        put(1, "I");
        put(4, "IV");
        put(5, "V");
        put(9, "IX");
        put(10, "X");
        put(40, "XL");
        put(50, "L");
        put(90, "XC");
        put(100, "C");
        put(400, "CD");
        put(500, "D");
        put(900, "CM");
        put(1000, "M");
    }};

    private static final List<Integer> LIST = Arrays.asList(
            1000,
            900,
            500,
            400,
            100,
            90,
            50,
            40,
            10,
            9,
            5,
            4,
            1
    );

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        for (Integer i : LIST) {
            while (num - i >= 0) {
                result.append(ROMANS.get(i));
                num -= i;
            }
        }
        return result.toString();
    }
}
