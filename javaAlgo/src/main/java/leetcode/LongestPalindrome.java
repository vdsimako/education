package leetcode;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(s, i, i);
            if (oddLength > end - start + 1) {
                int dist = (oddLength / 2) ;
                start = i - dist;
                end = i + dist;
            }

            int evenLength = expand(s, i, i + 1);
            if (evenLength > end - start + 1) {
                int dist = (evenLength / 2) - 1;
                start = i - dist;
                end = i + dist + 1;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s,
                       int left,
                       int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
