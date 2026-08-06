package leetcode

import (
	"reflect"
	"testing"
)

func TestInsertExample1(t *testing.T) {
	got := insert([][]int{{1, 3}, {6, 9}}, []int{2, 5})
	want := [][]int{{1, 5}, {6, 9}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("insert(...) = %v, want %v", got, want)
	}
}

func TestInsertExample2(t *testing.T) {
	got := insert([][]int{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, []int{4, 8})
	want := [][]int{{1, 2}, {3, 10}, {12, 16}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("insert(...) = %v, want %v", got, want)
	}
}

func TestInsertExample3(t *testing.T) {
	got := insert([][]int{}, []int{5, 7})
	want := [][]int{{5, 7}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("insert(...) = %v, want %v", got, want)
	}
}

func TestInsertExample4(t *testing.T) {
	got := insert([][]int{{1, 2}, {6, 7}}, []int{3, 5})
	want := [][]int{{1, 2}, {3, 5}, {6, 7}}
	if !reflect.DeepEqual(got, want) {
		t.Errorf("insert(...) = %v, want %v", got, want)
	}
}
