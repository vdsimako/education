package leetcode

// 49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/
//
// Given an array of strings strs, group the anagrams together. You can
// return the answer in any order.
//
// An Anagram is a word or phrase formed by rearranging the letters of a
// different word or phrase, typically using all the original letters
// exactly once.
//
// Example 1:
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// Example 2:
// Input: strs = [""]
// Output: [[""]]
//
// Example 3:
// Input: strs = ["a"]
// Output: [["a"]]
//
// Constraints:
// - 1 <= strs.length <= 10^4
// - 0 <= strs[i].length <= 100
// - strs[i] consists of lowercase English letters.
func groupAnagrams(strs []string) [][]string {
	res := make(map[[26]int][]string)
	for _, str := range strs {
		var count [26]int
		for _, c := range str {
			count[c-'a']++
		}

		res[count] = append(res[count], str)
	}

	result := make([][]string, 0, len(res))
	for _, group := range res {
		result = append(result, group)
	}
	return result
}
