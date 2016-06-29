

/** 
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * 
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */

public class ReverseNodesinkGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null)
			return null;
		int t = 0;
		ListNode nextHead = head;
		while (nextHead != null && t < k - 1) {
			nextHead = nextHead.next;
			t++;
		}  // 最后t = k-1，也是sublist的最后一个node
		if (nextHead == null || t < k - 1) {
			return head;  // 说明sublist的size < k
		} else {
			ListNode nextPart = reverseKGroup(nextHead.next, k); // reverse下一组size of k 的sublist，返回下一组sublist的head
			return reverseList(head, k, nextPart);
		}
	}

	private ListNode reverseList(ListNode head, int k, ListNode nextPart) {
		ListNode cur = head, prev = null, next = null;
		for (int i = 0; i < k; i++) {
			next = cur.next;
			if (i != 0) {
				cur.next = prev;
			} else {
				cur.next = nextPart; // 原来的head变成tail，后面连的是下一个sublist的head
			}
			prev = cur;
			cur = next;
		}
		return prev;
	}
}



// 01/10/15
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        ListNode start = new ListNode(0);
        start.next = reverse(head, k);
        return start.next;
    }
    
    private ListNode reverse(ListNode head, int k){
        if(head == null) return null;
        ListNode cur = head;
        for(int i = 0; i < k-1 && cur != null; i++){
            cur = cur.next;
        }
        if(cur == null) return head;
        ListNode nextlist = reverse(cur.next, k);
        cur.next = null;
        ListNode newStart = reverse(head);
        head.next = nextlist;
        return newStart;
    }
    
    private ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode next = null;
        ListNode prev = null;
        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

// 03/13/15
// 递归的方法，先找到长度为k的list，断开以后，reverse后面的返回新的head，
// reverse当前k长度的list，和后面新的list连起来，返回这个连起来list的开头
// 如果当前程序的sub list长度小于k，那就保持原样，返回初始的head
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        ListNode cur = head;
        for(int i = 0; i < k-1; i++){
            cur = cur.next;
            if(cur == null) return head;
        }
        ListNode right = reverseKGroup(cur.next, k);
        cur.next = null;
        ListNode newhead = reverse(head);
        head.next = right; // reverse后的新tail就是原来的head，和后面的sub list相连
        return newhead;
    }
    
    public ListNode reverse(ListNode head){
        ListNode prev = null, next = null;
        while(head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}