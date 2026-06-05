package leetcode;

/*
38. Count and Say
link - https://leetcode.com/problems/count-and-say/description/
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the run-length encoding of countAndSay(n - 1).
Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".

Given a positive integer n, return the nth element of the count-and-say sequence.

Example 1:
Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = RLE of "1" = "11"
countAndSay(3) = RLE of "11" = "21"
countAndSay(4) = RLE of "21" = "1211"

Example 2:
Input: n = 1
Output: "1"
Explanation:
This is the base case.


Constraints:
1 <= n <= 30

*/
public class Task38 {
    public String countAndSay(int n) {
        String current = "1";
        if (n == 1) {
            return current;
        }
        for (int i = 0; i < n - 1; i++) {
            current = describe(current);
        }

        return current;
    }

    public String describe(String current) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= current.length() - 1; i++) {
            if (current.charAt(i - 1) != current.charAt(i)) {
                result.append(count).append(current.charAt(i - 1));
                count = 0;
            }
            count++;
        }
        result.append(count).append(current.charAt(current.length() - 1));
        return result.toString();
    }
}
