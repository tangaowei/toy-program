package main

import "fmt"

type ListNode struct {
	Val int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	carry := 0
	head := new(ListNode)
	prev := head
	for {
		if l1 == nil && l2 == nil && carry == 0 {
			break;
		}
		if l1 != nil {
			carry += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			carry += l2.Val
			l2 = l2.Next
		}
		curNode := new(ListNode)
		curNode.Val = carry % 10
		curNode.Next = nil
		prev.Next = curNode
		prev = prev.Next
		carry = carry / 10
	}
	return head.Next
}

func createNode(values []int) *ListNode {
	head := new(ListNode)
	prev := head
	for i := 0; i < len(values); i++ {
		cur := new(ListNode)
		cur.Val = values[i]
		cur.Next = nil
		prev.Next = cur
		prev = cur
	}
	return head.Next
}

func printNode(list *ListNode) {
	for list != nil {
		fmt.Printf("%d ", list.Val)
		list = list.Next
	}
	fmt.Println("")
}

func main() {
	l1 := createNode([]int{2,4,3})
	l2 := createNode([]int{5,6,4})
	suml := addTwoNumbers(l1, l2)
	printNode(suml)
}
