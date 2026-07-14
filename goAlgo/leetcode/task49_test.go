package leetcode

import (
	"reflect"
	"sort"
	"strings"
	"testing"
)

func canonicalGroups(groups [][]string) [][]string {
	sorted := make([][]string, len(groups))
	for i, group := range groups {
		copyGroup := append([]string(nil), group...)
		sort.Strings(copyGroup)
		sorted[i] = copyGroup
	}
	sort.Slice(sorted, func(i, j int) bool {
		return strings.Join(sorted[i], ",") < strings.Join(sorted[j], ",")
	})
	return sorted
}

func TestGroupAnagramsExample1(t *testing.T) {
	strs := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
	expected := [][]string{{"bat"}, {"nat", "tan"}, {"ate", "eat", "tea"}}

	got := canonicalGroups(groupAnagrams(strs))
	want := canonicalGroups(expected)
	if !reflect.DeepEqual(got, want) {
		t.Errorf("GroupAnagrams(%v) = %v, want %v", strs, got, want)
	}
}

func TestGroupAnagramsExample2(t *testing.T) {
	strs := []string{""}
	expected := [][]string{{""}}

	got := canonicalGroups(groupAnagrams(strs))
	want := canonicalGroups(expected)
	if !reflect.DeepEqual(got, want) {
		t.Errorf("GroupAnagrams(%v) = %v, want %v", strs, got, want)
	}
}

func TestGroupAnagramsExample3(t *testing.T) {
	strs := []string{"a"}
	expected := [][]string{{"a"}}

	got := canonicalGroups(groupAnagrams(strs))
	want := canonicalGroups(expected)
	if !reflect.DeepEqual(got, want) {
		t.Errorf("GroupAnagrams(%v) = %v, want %v", strs, got, want)
	}
}

func TestGroupAnagramsNoAnagramsPresent(t *testing.T) {
	strs := []string{"abc", "def", "ghi"}
	expected := [][]string{{"abc"}, {"def"}, {"ghi"}}

	got := canonicalGroups(groupAnagrams(strs))
	want := canonicalGroups(expected)
	if !reflect.DeepEqual(got, want) {
		t.Errorf("GroupAnagrams(%v) = %v, want %v", strs, got, want)
	}
}
