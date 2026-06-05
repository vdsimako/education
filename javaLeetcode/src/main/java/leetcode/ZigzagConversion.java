package leetcode;

public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        StringBuilder[] strings = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            strings[i] = new StringBuilder();
        }
        int direction = 1;
        int row = 0;
        for (int i = 0; i < s.length(); i++) {
            strings[row].append(s.charAt(i));

            row += direction;

            if (row == numRows - 1 || row == 0) {
                direction *= -1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder str : strings) {
            result.append(str);
        }
        return result.toString();
    }
}
