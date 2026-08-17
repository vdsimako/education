package leetcode

import "testing"

func TestMinPathSumExample1(t *testing.T) {
	got := minPathSum([][]int{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}})
	want := 7
	if got != want {
		t.Errorf("minPathSum(...) = %v, want %v", got, want)
	}
}

func TestMinPathSumExample2(t *testing.T) {
	got := minPathSum([][]int{{1, 2, 3}, {4, 5, 6}})
	want := 12
	if got != want {
		t.Errorf("minPathSum(...) = %v, want %v", got, want)
	}
}

func TestMinPathSumSingleCell(t *testing.T) {
	got := minPathSum([][]int{{5}})
	want := 5
	if got != want {
		t.Errorf("minPathSum(...) = %v, want %v", got, want)
	}
}
