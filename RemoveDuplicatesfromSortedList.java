

/** 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */

public class RemoveDuplicatesfromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		int cur = head.val;
		ListNode p = head.next;
		ListNode prev = head;
		while (p != null) {
			if (p.val == cur) {  // p是重复的，cur是prev的val
				prev.next = p.next;  // 跳过p，连下一个
				p = prev.next;
			} else {
				cur = p.val;
				prev = prev.next;
				p = p.next;
			}
		}
		return head;
	}
}


// 02/11/15
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode cur = head;
        while(cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next; // 不断删除重复的node，link下一个node
            }
            else{
                cur = cur.next;
            }
        }
        return head;
    }
}