package leetcode

import (
	"reflect"
	"testing"
)

func TestMergeExample1(t *testing.T) {
	got := merge([][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}})
	want := [][]int{{1, 6}, {8, 10}, {15, 18}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("merge(...) = %v, want %v", got, want)
	}
}

func TestMergeExample2(t *testing.T) {
	got := merge([][]int{{1, 4}, {4, 5}})
	want := [][]int{{1, 5}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("merge(...) = %v, want %v", got, want)
	}
}

func TestMergeExample3(t *testing.T) {
	got := merge([][]int{{1, 4}, {0, 4}})
	want := [][]int{{0, 4}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("merge(...) = %v, want %v", got, want)
	}
}

func TestMergeExample4(t *testing.T) {
	got := merge([][]int{{1, 10}, {1, 4}})
	want := [][]int{{1, 10}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("merge(...) = %v, want %v", got, want)
	}
}
