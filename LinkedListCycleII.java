/**
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 * 
 * Follow up: Can you solve it without using extra space?
 * 
 */
public class LinkedListCycleII {
	public ListNode detectCycle(ListNode head) {
		if (head == null)
			return head;  // 先排除head为null的情况
		ListNode fast = head, slow = head;
		do {
			if (fast.next != null && fast.next.next != null) {
				fast = fast.next.next;
				slow = slow.next;
			} else {
				return null;
			}
		} while (fast != slow);
		slow = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
}

// 01/12/15
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;  // 这里考虑只有一个node不能形成cycle
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;   // 小心这里不是比较val，而是直接比较指针
        }
        if(fast == null || fast.next == null) return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

// 03/21/15
// 如果有cycle，双指针不同速度next，当slow到cycle开始端length为k，那么fast就在cycle开始后的k位置，fast落后slow cycle_size-k
// 因为fast赶上slow是1 node/step, 所以当 slow == fast的时候，slow 又经过 size-k个step，此处离cycle开始端为k距离
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode s = head.next;
        ListNode f = head.next.next;
        while(f != null && f.next != null){
            if(s == f) break;
            s = s.next;
            f = f.next.next;
        }
        if(s != f) return null; // 没有cycle
        s = head;
        while(s != f){
            s = s.next;
            f = f.next;
        }
        return s;
    }
}