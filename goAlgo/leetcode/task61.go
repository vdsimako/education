package leetcode

// 61. Rotate List
// https://leetcode.com/problems/rotate-list/
//
// Given the head of a linked list, rotate the list to the right by k places.
//
// Example 1:
// Input: head = [1,2,3,4,5], k = 2
// Output: [4,5,1,2,3]
//
// Example 2:
// Input: head = [0,1,2], k = 4
// Output: [2,0,1]
//
// Constraints:
// - The number of nodes in the list is in the range [0, 500].
// - -100 <= Node.val <= 100
// - 0 <= k <= 2^31 - 1

type ListNode struct {
	Val  int
	Next *ListNode
}

func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil {
		return head
	}
	var length = 0
	var curr = head
	for {
		length++
		if curr.Next == nil {
			break
		}
		curr = curr.Next
	}
	var steps = k % length
	if steps == 0 {
		return head
	}
	curr.Next = head
	var tail = head
	for range length - steps - 1 {
		tail = tail.Next
	}
	var res = tail.Next
	tail.Next = nil
	return res
}
