package leetcode

import "testing"

func TestUniquePathsExample1(t *testing.T) {
	got := uniquePaths(3, 7)
	want := 28
	if got != want {
		t.Errorf("uniquePaths(3, 7) = %v, want %v", got, want)
	}
}

func TestUniquePathsExample2(t *testing.T) {
	got := uniquePaths(3, 2)
	want := 3
	if got != want {
		t.Errorf("uniquePaths(3, 2) = %v, want %v", got, want)
	}
}

func TestUniquePathsSingleCell(t *testing.T) {
	got := uniquePaths(1, 1)
	want := 1
	if got != want {
		t.Errorf("uniquePaths(1, 1) = %v, want %v", got, want)
	}
}
