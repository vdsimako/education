package leetcode

import "testing"

func TestMaxSubArrayExample1(t *testing.T) {
	got := maxSubArray([]int{-2, 1, -3, 4, -1, 2, 1, -5, 4})
	want := 6
	if got != want {
		t.Errorf("maxSubArray(...) = %v, want %v", got, want)
	}
}

func TestMaxSubArrayExample2(t *testing.T) {
	got := maxSubArray([]int{1})
	want := 1
	if got != want {
		t.Errorf("maxSubArray(...) = %v, want %v", got, want)
	}
}

func TestMaxSubArrayExample3(t *testing.T) {
	got := maxSubArray([]int{5, 4, -1, 7, 8})
	want := 23
	if got != want {
		t.Errorf("maxSubArray(...) = %v, want %v", got, want)
	}
}
