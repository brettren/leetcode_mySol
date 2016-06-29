// Given a singly linked list, determine if it is a palindrome.

// Follow up:
// Could you do it in O(n) time and O(1) space?


// 先用slow和fast指针找到middle，然后reverse后半部分，再两个list compare
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){
        	slow = slow.next;
        	fast = fast.next.next;
        }
        ListNode second;
        if(fast == null){  // odd
        	second = reverse(slow);
        }
        else{
        	second = reverse(slow.next);
        }
        ListNode first = head;
        while(first != null && second != null){
        	if(first.val != second.val) return false;
        	first = first.next;
       		second = second.next; 	
        }
        return true;
    }
    
    public ListNode reverse(ListNode head){
    	ListNode next = null, prev = null;
    	while(head != null){
    		next = head.next;
    		head.next = prev;
    		prev = head;
    		head = next;
    	}
    	return prev;
    }
}