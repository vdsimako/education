package leetcode

import (
	"math"
	"testing"
)

const epsilon = 1e-5

func almostEqual(a, b float64) bool {
	return math.Abs(a-b) < epsilon
}

func TestMyPowExample1(t *testing.T) {
	got := myPow(2.00000, 10)
	want := 1024.00000
	if !almostEqual(got, want) {
		t.Errorf("myPow(2.00000, 10) = %v, want %v", got, want)
	}
}

func TestMyPowExample2(t *testing.T) {
	got := myPow(2.10000, 3)
	want := 9.26100
	if !almostEqual(got, want) {
		t.Errorf("myPow(2.10000, 3) = %v, want %v", got, want)
	}
}

func TestMyPowExample3(t *testing.T) {
	got := myPow(2.00000, -2)
	want := 0.25000
	if !almostEqual(got, want) {
		t.Errorf("myPow(2.00000, -2) = %v, want %v", got, want)
	}
}

func TestMyPowZeroExponent(t *testing.T) {
	got := myPow(2.00000, 0)
	want := 1.0
	if !almostEqual(got, want) {
		t.Errorf("myPow(2.00000, 0) = %v, want %v", got, want)
	}
}
