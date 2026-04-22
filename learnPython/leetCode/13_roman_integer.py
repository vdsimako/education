class Solution:
    def romanToInt(self, s: str) -> int:
        values = {
            'M': 1000,
            'CM': 900,
            'D': 500,
            'CD': 400,
            'C': 100,
            'XC': 90,
            'L': 50,
            'XL': 40,
            'X': 10,
            'V': 5,
            'I': 1
        }

        i = 0
        result = 0
        for k, v in values.items():
            while s.startswith(k, i):
                result += v
                i += len(k)

        return result


if __name__ == "__main__":
    print(Solution().romanToInt("MMCMXCIV"))
