class Solution:
    def convert(self, s: str, numRows: int):
        if numRows == 1:
            return s
        if numRows >= len(s):
            return s

        result = [""] * numRows
        direction = 1
        row = 0

        for i in range(len(s)):
            if direction == 1:
                result[row] += s[i]
                row += 1

            if direction == -1:
                result[row] += s[i]
                row -= 1

            if row == 0 or row == numRows - 1:
                direction *= -1


        return "".join(result)



if __name__ == "__main__":
    print(2 // 3)
    print(3 // 3)
    print(Solution().convert("ABCDEf", 2))