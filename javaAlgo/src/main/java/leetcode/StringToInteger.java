package leetcode;

public class StringToInteger {
    public int myAtoi(String s) {
        long result = 0;

        int sign = 1;

        boolean flag = false;

        s = s.stripLeading();

        for (char c : s.toCharArray()) {
            if (c == '+' && !flag) {
                flag = true;
                continue;
            }

            if (c == '-' && !flag) {
                flag = true;
                sign = -1;
                continue;
            }
            if (!Character.isDigit(c)) {
                return (int) (sign * result);
            }

            flag = true;

            result = result * 10 + c - '0';

            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (sign == -1 && sign * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) (sign * result);
    }
}
