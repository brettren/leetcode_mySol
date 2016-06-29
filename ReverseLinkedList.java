// A linked list can be reversed either iteratively or recursively. Could you implement both?

// iterative

public class Solution {
    public ListNode reverseList(ListNode head) {
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


// recursive
// 把head.next 翻转后返回新的head，把当前的node移到新list的最后面
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newhead = reverseList(next);
        next.next = head;
        head.next = null;
        return newhead;
    }	
}