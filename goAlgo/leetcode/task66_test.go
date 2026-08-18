package leetcode

import (
	"reflect"
	"testing"
)

func TestPlusOneExample1(t *testing.T) {
	got := plusOne([]int{1, 2, 3})
	want := []int{1, 2, 4}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("plusOne(...) = %v, want %v", got, want)
	}
}

func TestPlusOneExample2(t *testing.T) {
	got := plusOne([]int{4, 3, 2, 1})
	want := []int{4, 3, 2, 2}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("plusOne(...) = %v, want %v", got, want)
	}
}

func TestPlusOneExample3(t *testing.T) {
	got := plusOne([]int{9})
	want := []int{1, 0}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("plusOne(...) = %v, want %v", got, want)
	}
}

func TestPlusOneExample4(t *testing.T) {
	got := plusOne([]int{1, 2, 8})
	want := []int{1, 2, 9}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("plusOne(...) = %v, want %v", got, want)
	}
}

func TestPlusOneAllNines(t *testing.T) {
	got := plusOne([]int{9, 9, 9})
	want := []int{1, 0, 0, 0}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("plusOne(...) = %v, want %v", got, want)
	}
}
