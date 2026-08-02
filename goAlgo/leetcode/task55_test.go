package leetcode

import "testing"

func TestCanJumpExample1(t *testing.T) {
	got := canJump([]int{2, 3, 1, 1, 4})
	want := true
	if got != want {
		t.Errorf("canJump(...) = %v, want %v", got, want)
	}
}

func TestCanJumpExample2(t *testing.T) {
	got := canJump([]int{3, 2, 1, 0, 4})
	want := false
	if got != want {
		t.Errorf("canJump(...) = %v, want %v", got, want)
	}
}

func TestCanJumpExample3(t *testing.T) {
	got := canJump([]int{1, 1, 1, 0})
	want := true
	if got != want {
		t.Errorf("canJump(...) = %v, want %v", got, want)
	}
}
