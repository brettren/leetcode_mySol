

/** 
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * 
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */

public class RemoveNthNodeFromEndofList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = new ListNode(0);
        ListNode fast = root;
        ListNode slow = root;
        root.next = head;
        for (int i = 0; i < n; i++) {  // 一共是n个loop
            fast = fast.next;
        }  // fast最后停在左数第n个，slow在第0个
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // slow.next就是倒数第n个，remove掉
        return root.next;
    }
}

// 03/20/15
// 先用一个fast指针移到从头开始的第n个，然后slow指针从头开始和fast指针同步往后移动，直到fast到结尾
// 此时slow指针的下一个就是需要移除的倒数第n个node
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode s = start;
        ListNode e = start;
        for(int i = 0; i < n; i++){
            e = e.next;
        }
        while(e.next != null){
            s = s.next;
            e = e.next;
        }
        s.next = s.next.next;
        return start.next;
    }
}