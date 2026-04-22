public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int result = 0;
        int sign = 1;
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend < 0 && divisor > 0) {
            sign = -1;
        }
        if (dividend > 0 && divisor < 0) {
            sign = -1;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (dividend >= divisor) {
            int current = divisor;
            int multiple = 1;
            while (current + current <= dividend) {
                current += current;
                multiple += multiple;
            }
            dividend = dividend - current;
            result += multiple;
        }

        return result * sign;
    }
}
