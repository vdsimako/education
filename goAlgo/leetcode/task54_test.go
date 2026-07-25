package leetcode

import (
	"reflect"
	"testing"
)

func TestSpiralOrderExample1(t *testing.T) {
	got := spiralOrder([][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})
	want := []int{1, 2, 3, 6, 9, 8, 7, 4, 5}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("spiralOrder(...) = %v, want %v", got, want)
	}
}

func TestSpiralOrderExample2(t *testing.T) {
	got := spiralOrder([][]int{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})
	want := []int{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("spiralOrder(...) = %v, want %v", got, want)
	}
}
