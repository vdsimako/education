package leetcode;

/*
 * 43. Multiply Strings
 * https://leetcode.com/problems/multiply-strings/
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the
 * inputs to integer directly.
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * Constraints:
 * - 1 <= num1.length, num2.length <= 200
 * - num1 and num2 consist of digits only.
 * - Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class Task43 {
    private static final String ZERO = "0";

    public String multiply(String num1, String num2) {
        if (num1.equals(ZERO) || num2.equals(ZERO)) {
            return ZERO;
        }

        int[] result = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';

                int mul = digit1 * digit2;

                int p1 = i + j;
                int p2 = i + j + 1;

                int sum = mul + result[p2];

                result[p2] = sum % 10;
                result[p1] += sum / 10;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i : result) {

            if (stringBuilder.isEmpty() && i == 0) {
                continue;
            }

            stringBuilder.append(i);
        }

        return stringBuilder.toString();
    }
}
