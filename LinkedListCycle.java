

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 */

public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		if (head == null) return false;
		ListNode fast = head;
		ListNode late = head;
		while(fast != null && late != null) {
			fast = fast.next;
			late = late.next;
			if (fast == null) {
				return false;
			} else {
				fast = fast.next; // fast在一个loop里跳两个
				if (fast == late) {
					return true;
				}
			}
		} 
		return false;
	}
}

// 01/12/15
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}