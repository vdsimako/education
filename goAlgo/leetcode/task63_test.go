package leetcode

import "testing"

func TestUniquePathsWithObstaclesExample1(t *testing.T) {
	got := uniquePathsWithObstacles([][]int{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}})
	want := 2
	if got != want {
		t.Errorf("uniquePathsWithObstacles(...) = %v, want %v", got, want)
	}
}

func TestUniquePathsWithObstaclesExample2(t *testing.T) {
	got := uniquePathsWithObstacles([][]int{{0, 1}, {0, 0}})
	want := 1
	if got != want {
		t.Errorf("uniquePathsWithObstacles(...) = %v, want %v", got, want)
	}
}

func TestUniquePathsWithObstaclesStartBlocked(t *testing.T) {
	got := uniquePathsWithObstacles([][]int{{1, 0}})
	want := 0
	if got != want {
		t.Errorf("uniquePathsWithObstacles(...) = %v, want %v", got, want)
	}
}

func TestUniquePathsWithObstaclesExample3(t *testing.T) {
	got := uniquePathsWithObstacles([][]int{{0, 1, 0}, {0, 0, 0}, {0, 0, 0}})
	want := 3
	if got != want {
		t.Errorf("uniquePathsWithObstacles(...) = %v, want %v", got, want)
	}
}

func TestUniquePathsWithObstaclesExample4(t *testing.T) {
	got := uniquePathsWithObstacles([][]int{{0, 1}, {0, 0}, {0, 0}})
	want := 2
	if got != want {
		t.Errorf("uniquePathsWithObstacles(...) = %v, want %v", got, want)
	}
}
