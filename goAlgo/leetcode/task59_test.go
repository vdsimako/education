package leetcode

import (
	"reflect"
	"testing"
)

func TestGenerateMatrixExample1(t *testing.T) {
	got := generateMatrix(3)
	want := [][]int{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("generateMatrix(...) = %v, want %v", got, want)
	}
}

func TestGenerateMatrixExample2(t *testing.T) {
	got := generateMatrix(1)
	want := [][]int{{1}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("generateMatrix(...) = %v, want %v", got, want)
	}
}
