/** 
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example:
 * 
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 *
 * return 1->4->3->2->5->NULL. 
 */

public class ReverseLinkedListII {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode start = new ListNode(0);
		start.next = head;
		ListNode low = start;
		ListNode leftend = null;
		ListNode rightstart = null;
		for (int i = 0; i < m - 1; i++) {  // m-1个loop
			low = low.next;  
		}
		leftend = low;  // 找到前面list的tail  第m-1个node		
		low = low.next;  // 中间list的head
		ListNode high = low;
		for (int i = m; i < n; i++) {
			high = high.next;  // 中间list的tail
		}
		rightstart = high.next; // 找到后面list的head
		high.next = null;  // 中间需要reverse的一段提取出来
		leftend.next = reverseList(low);  // 和前面list相连
		low.next = rightstart; // reverse之后原来的low是现在list的tail
		return start.next;
	}

	private ListNode reverseList(ListNode low) {
		ListNode cur = low;
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



// 01/10/15
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur = start;
        ListNode leftend, rightstart;
        ListNode midstart, midend;
        for(int i = 0; i < m-1; i++){
            cur = cur.next;
        }
        leftend = cur;
        midstart = leftend.next;
        for(int i = 0; i <= n-m; i++){
            cur = cur.next;
        }
        midend= cur;
        rightstart = midend.next;
        leftend.next = null;
        midend.next = null;
        leftend.next = reverse(midstart);
        midstart.next = rightstart;
        return start.next;
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

// 01/29/15
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur = start;
        for(int i = 1; i < m; i++){
            cur = cur.next;
        }
        ListNode leftend = cur;
        ListNode midstart = cur.next;
        for(int i = m; i <= n; i++){  // 如果是2，3 那就是跳2 steps
            cur = cur.next;
        }
        ListNode midend = cur;
        ListNode rightstart = cur.next;
        midend.next = null;
        cur = reverse(midstart);
        leftend.next = cur;
        midstart.next = rightstart;
        return start.next;
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

// 06/16/15
public class Solution {  
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur = start;
        for (int i = 1; i < m; i++) {
            cur = cur.next;
        }
        ListNode firstEnd = cur;
        ListNode midStart = cur.next;
        for (int i = m; i <= n; i++) {
            cur = cur.next;
        }
        ListNode midEnd = cur;
        ListNode secondStart = cur.next;
        midEnd.next = null;
        firstEnd.next = reverse(midStart);
        midStart.next = secondStart;
        return head;
    }

    public ListNode reverse(head){
        ListNode next = null, prev = null;
        while(head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}