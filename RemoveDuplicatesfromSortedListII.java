/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3,
 * return 2->3.
 */
// 也就是说，一旦发现有两个相同的，就直接跳过这一段重复的number，直接看下一个number
public class RemoveDuplicatesfromSortedListII {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		ListNode start = new ListNode(0);
		start.next = head;
		ListNode slow = start;
		ListNode fast = head;
		while (fast.next != null) {
			if (slow.next.val != fast.next.val) {  // fast的next又是个新的number了
				if (slow.next == fast) {  // 说明中间的number没有重复
					slow = slow.next;
				} else {  // 否则的话中间的number是重复的，直接跳过重复的，把slow.next连到在下一个新的number
					slow.next = fast.next;
				}
			}
			fast = fast.next; // fast一直找到中间重复num sequence的最后一个
		}
		// 此时fast是list最后一个number
		if (slow.next.next != null) {  // 如果slow.next.next不是NULL，说明slow后面是重复的number
			slow.next = null;
		}
		return start.next;
	}
}

// 01/07/15
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode start = new ListNode(0);
        ListNode cur = start;
        cur.next = head;
        ListNode fast = cur.next;
        while(fast.next != null){
            if(cur.next.val == fast.next.val){
                fast = fast.next;
            }
            else{
                if(cur.next == fast){
                    cur = cur.next;
                    fast = fast.next;
                }
                else{
                    cur.next = fast.next;
                    fast = fast.next;
                }
            }
        }
        if(cur.next.next != null) cur.next = null;
        return start.next;
    }
}

// 03/13/15
// 比较slow.next 和 fast.next
// 如果rev.next.val == cur.next.val说明有dup的情况，cur继续往next知道dup的end
// else就说明找到dup的end，判断是否是dup的情况，如果是dup的部分就delete掉
// 最后要检查末尾是否有dup的情况
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode prev = start;
        ListNode cur = head;
        while(cur.next != null){
            if(prev.next.val == cur.next.val){
                cur = cur.next;
            }
            else{
                if(prev.next == cur){
                    prev = cur;
                    cur = cur.next;
                }
                else{
                    prev.next = cur.next;
                    cur = cur.next;
                }
            }
        }
        
        if(prev.next != cur){
            prev.next = null;
        }
        
        return start.next;
    }
}