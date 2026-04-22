public class ReverseInteger {
    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return sign * (int) result;
    }
}
