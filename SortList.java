

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 */

public class SortList {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next; // 找到list的mid
		}
		fast = slow.next; // 下半部分的head
		slow.next = null;
		slow = sortList(head);
		fast = sortList(fast);
		return merge(slow, fast);
	}

	private ListNode merge(ListNode slow, ListNode fast) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (slow != null && fast != null) {
			if (slow.val < fast.val) {
				cur.next = slow;
				slow = slow.next;
			} else {
				cur.next = fast;
				fast = fast.next;
			}
			cur = cur.next;
		}

		if (slow != null) {
			cur.next = slow;
		} else if (fast != null) {
			cur.next = fast;
		}
		return head.next;
	}
}


// 01/13/15
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = sortList(slow.next);
        slow.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }
    
    private ListNode merge(ListNode left, ListNode right){
        ListNode start = new ListNode(0);
        ListNode cur = start;
        while(left != null && right != null){
            if(left.val < right.val){
                cur.next = left;
                left = left.next;
            }
            else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if(left != null){
            cur.next = left;
        }
        else if(right != null){
            cur.next = right;
        }
        return start.next;
    }
}


// 03/10/15
// 递归，找到list的mid，断开，得到排序后的left和right，然后merge后返回
// 这里要注意找mid的时候 f = head.next； 如果list只有两个node，这样才能到底返回，不然会无限循环
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode s = head;
        ListNode f = head.next;
        while(f != null && f.next != null){
            s = s.next;
            f = f.next.next;
        }
        ListNode right = sortList(s.next);
        s.next = null;
        ListNode left = sortList(head);
        
        ListNode start = new ListNode(0);
        ListNode cur = start;
        while(left != null && right != null){
            if(left.val > right.val){
                cur.next = right;
                right = right.next;
                cur = cur.next;
            }
            else{
                cur.next = left;
                left = left.next;
                cur = cur.next;
            }
        }
        if(left != null){
            cur.next = left;
        }
        if(right != null){
            cur.next = right;
        }
        return start.next;
    }
}