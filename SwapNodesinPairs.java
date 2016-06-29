

/** 
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * 
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */


public class SwapNodesinPairs {
	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return null;
		else if (head.next == null) {
			return head;
		} else {
			ListNode nextList = swapPairs(head.next.next); // 一直递归到最后开始swap
			ListNode tmp = head.next; // 2nd node 变成 1st node
			head.next = nextList;  // 1st node 变成2nd node，next连的是3rd node
			tmp.next = head;  // 新的1st node，next是原head
			return tmp;
		}
	}
}

// 注意这题不允许swap value
// 01/04/15
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode nextlist = swapPairs(head.next.next);
        ListNode second = head.next;
        head.next = nextlist;
        second.next = head;
        return second;
    }
}

// 03/22/15
// 先记下当前pair的两个node，然后递归得到后面list的newhead，swap当前pair后和newhead相连
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode next = cur.next;
        cur.next = swapPairs(next.next);
        next.next = cur;
        return next;
    }
}