/**
 * You are given two linked lists representing two non-negative numbers. 
 * 
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)  342 + 465 = 807
 * 
 * Output: 7 -> 0 -> 8
 */

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode head = new ListNode(0);
		ListNode cur = head;
		int carry = 0;
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			cur.next = new ListNode(sum);

			cur = cur.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		if (l1 != null) {
			if (carry != 0) {
				cur.next = addTwoNumbers(l1, new ListNode(carry)); // 把carry当做一个新的node
			} else {
				cur.next = l1;
			}
		} else if (l2 != null) {
			if (carry != 0) {
				cur.next = addTwoNumbers(l2, new ListNode(carry));
			} else {
				cur.next = l2;
			}
		} else if (carry != 0) {
			cur.next = new ListNode(carry);
		}

		return head.next;
	}
}

// 01/03/15
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        }
            
        ListNode head = new ListNode(0);
        ListNode point = head;
        int carry = 0;
        while(l1 != null && l2!=null){
            int sum = carry + l1.val + l2.val;
            point.next = new ListNode(sum % 10);
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            point = point.next;
        }
        
        while(l1 != null) {
            int sum =  carry + l1.val;
            point.next = new ListNode(sum % 10);
            carry = sum /10;
            l1 = l1.next;
            point = point.next;
        }
        
        while(l2 != null) {
            int sum =  carry + l2.val;
            point.next = new ListNode(sum % 10);
            carry = sum /10;
            l2 = l2.next;
            point = point.next;
        }
        
        if (carry != 0) {
            point.next = new ListNode(carry);
        }
        return head.next;
    }
}

// 03/11/15
// 注意每次加上一个digit，cur和l1和l2都要跳到next, 记下carry给下一个loop
// 最后如果carry == 1，还要再连一个node(1)
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        int carry = 0;
        ListNode start = new ListNode(0);
        ListNode cur = start;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + carry;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            carry = sum/10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int sum = l1.val + carry;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            carry = sum/10;
            l1 = l1.next;
        }
        while(l2 != null){
            int sum = l2.val + carry;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            carry = sum/10;
            l2 = l2.next;
        }
        if(carry != 0){
            cur.next = new ListNode(carry);
        }
        return start.next;
    }
}