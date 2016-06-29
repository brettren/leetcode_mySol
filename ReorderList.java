

/**
 * Given a singly linked list L: L0->L1->...->Ln-1->Ln,
 * reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->...
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
 *  {1,2,3,4,5} {1,5,2,4,3}
 */

public class ReorderList {
	public void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;
		ListNode fast = head;
		ListNode late = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			late = late.next;  // 找到list的mid node
		}
		ListNode ret = new ListNode(0);
		ListNode cur = ret;
		ListNode leftHalf = head;
		ListNode rightHalf;
		
		rightHalf = reverseList(late.next); //late.next是下半部分的开始, 如果node数是奇数5，那就分成3+2，如果是偶数6，那就是3+3
		late.next = null;  // 中间断开，形成两个list
		
		while (leftHalf != null && rightHalf != null) {
			cur.next = leftHalf; // 先连left
			leftHalf = leftHalf.next;
			cur = cur.next;

			cur.next = rightHalf;// 再连right
			rightHalf = rightHalf.next;
			cur = cur.next;
		}
		if (leftHalf != null) {
			cur.next = leftHalf;
		} else if (rightHalf != null) {
			cur.next = rightHalf;
		}
		head = ret.next;
	}

	private ListNode reverseList(ListNode head) {
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}

// 03/09/15
// 先用两个指针的方法找到middle，断开以后reverse后边一半，再交叉连起来
public class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode start = new ListNode(0);
        ListNode cur = start;
        
        ListNode right = reverse(slow.next);
        slow.next = null;
        ListNode left = head;
        
        while(left != null && right != null){
            cur.next = left;
            left = left.next; // 这里要小心，先让left到下一个位置，不然cur和left就重叠了
            cur = cur.next;
            
            cur.next = right;
            right = right.next;
            cur = cur.next;
        }
        if(left != null){
            cur.next = left;
        }
        if(right != null){
            cur.next = right;
        }
        head = start.next;
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