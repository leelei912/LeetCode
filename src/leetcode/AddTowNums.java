package leetcode;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class AddTowNums {
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int before = l1.val + l2.val;
		int carry = 0;
		if(before >= 10) {
			carry = before / 10;
			before = before % 10;
		}
		ListNode res = new ListNode(before);
		ListNode head = res;
		l1 = l1.next;
		l2 = l2.next;
		while(l1 != null || l2 != null || carry != 0) {
			if(l1 != null && l2 != null) {
				before = l1.val + l2.val + carry;
				carry = 0;
				if(before >= 10) {
					carry = before / 10;
					before = before % 10;
				}
				l1 = l1.next;
				l2 = l2.next;
				res.next = new ListNode(before);
			} else if(l1 != null) {
				before = l1.val + carry;
				carry = 0;
				if(before >= 10) {
					carry = before / 10;
					before = before % 10;
				}
				l1 = l1.next;
				res.next = new ListNode(before);
			} else if(l2 != null) {
				before = l2.val + carry;
				carry = 0;
				if(before >= 10) {
					carry = before / 10;
					before = before % 10;
				}
				l2 = l2.next;
				res.next = new ListNode(before);
			} else {
				res.next = new ListNode(carry);
				carry = 0;
			}
			res = res.next;
		}
		return head;
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(5);
		/*l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);*/
		ListNode l2 = new ListNode(5);
		/*l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);*/
		ListNode res = addTwoNumbers(l1, l2);
		while(res != null)
		{
			System.out.println(res.val);
			res = res.next;
		}
			
	}
}

/**
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring. 

 *
 */