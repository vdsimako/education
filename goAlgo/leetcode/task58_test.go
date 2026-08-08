package leetcode

import "testing"

func TestLengthOfLastWordExample1(t *testing.T) {
	got := lengthOfLastWord("Hello World")
	want := 5
	if got != want {
		t.Errorf("lengthOfLastWord(...) = %v, want %v", got, want)
	}
}

func TestLengthOfLastWordExample2(t *testing.T) {
	got := lengthOfLastWord("   fly me   to   the moon  ")
	want := 4
	if got != want {
		t.Errorf("lengthOfLastWord(...) = %v, want %v", got, want)
	}
}

func TestLengthOfLastWordExample3(t *testing.T) {
	got := lengthOfLastWord("luffy is still joyboy")
	want := 6
	if got != want {
		t.Errorf("lengthOfLastWord(...) = %v, want %v", got, want)
	}
}
