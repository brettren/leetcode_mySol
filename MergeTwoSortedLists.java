

/** 
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists. 
 */

public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);  // 空的node，下一个才是head
		ListNode cur = head;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		if (l1 != null) {  //如果只有一个list还有剩下的node，把这些全加到list后面
			cur.next = l1;
		} else {
			cur.next = l2;
		}
		return head.next;
	}
}


public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            }
            else{
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if(l1 == null) cur.next = l2;
        else cur.next = l1;
        return head.next;
    }
}

// 01/25/15
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode start = new ListNode(0);
        ListNode cur = start;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
            else{
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            }
        }
        if(l1 != null) {
            cur.next = l1; 
        }
        if(l2 != null) {
            cur.next = l2;
        }
        return start.next;
    }
}