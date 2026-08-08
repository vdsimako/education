package leetcode

import (
	"reflect"
	"testing"
)

func buildList(values []int) *ListNode {
	dummy := &ListNode{}
	curr := dummy
	for _, v := range values {
		curr.Next = &ListNode{Val: v}
		curr = curr.Next
	}
	return dummy.Next
}

func TestRotateRightExample1(t *testing.T) {
	got := rotateRight(buildList([]int{1, 2, 3, 4, 5}), 2)
	want := buildList([]int{4, 5, 1, 2, 3})
	if !reflect.DeepEqual(got, want) {
		t.Errorf("rotateRight(...) = %v, want %v", got, want)
	}
}

func TestRotateRightExample2(t *testing.T) {
	got := rotateRight(buildList([]int{0, 1, 2}), 4)
	want := buildList([]int{2, 0, 1})
	if !reflect.DeepEqual(got, want) {
		t.Errorf("rotateRight(...) = %v, want %v", got, want)
	}
}

func TestRotateRightExample3(t *testing.T) {
	got := rotateRight(buildList([]int{0, 1}), 1)
	want := buildList([]int{1, 0})
	if !reflect.DeepEqual(got, want) {
		t.Errorf("rotateRight(...) = %v, want %v", got, want)
	}
}

func TestRotateRightExample4(t *testing.T) {
	got := rotateRight(buildList([]int{0, 1}), 2)
	want := buildList([]int{0, 1})
	if !reflect.DeepEqual(got, want) {
		t.Errorf("rotateRight(...) = %v, want %v", got, want)
	}
}

func TestRotateRightExample5(t *testing.T) {
	got := rotateRight(buildList([]int{}), 1)
	want := buildList([]int{})
	if !reflect.DeepEqual(got, want) {
		t.Errorf("rotateRight(...) = %v, want %v", got, want)
	}
}

func TestRotateRightExample6(t *testing.T) {
	got := rotateRight(buildList([]int{1}), 1)
	want := buildList([]int{1})
	if !reflect.DeepEqual(got, want) {
		t.Errorf("rotateRight(...) = %v, want %v", got, want)
	}
}
