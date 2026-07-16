package leetcode

// 50. Pow(x, n)
// https://leetcode.com/problems/powx-n/
//
// Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
//
// Example 1:
// Input: x = 2.00000, n = 10
// Output: 1024.00000
//
// Example 2:
// Input: x = 2.10000, n = 3
// Output: 9.26100
//
// Example 3:
// Input: x = 2.00000, n = -2
// Output: 0.25000
// Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
//
// Constraints:
// - -100.0 < x < 100.0
// - -2^31 <= n <= 2^31 - 1
// - n is an integer.
// - Either x is not zero or n > 0.
// - -10^4 <= x^n <= 10^4
func myPow(x float64, n int) float64 {
	if n < 0 {
		x = 1 / x
		n = -n
	}

	return Power(1.0, x, n)
}

func Power(result float64, x float64, n int) float64 {
	if n == 0 {
		return result
	}

	if n%2 == 1 {
		result *= x
		n--
	} else {
		x *= x
		n /= 2
	}

	return Power(result, x, n)
}
