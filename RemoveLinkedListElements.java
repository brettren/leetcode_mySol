// Remove all elements from a linked list of integers that have value val.

// Example
// Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
// Return: 1 --> 2 --> 3 --> 4 --> 5


// 记得最后cur.next = null 收尾

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode start = new ListNode(0);
        ListNode cur = start;
        while(head != null){
        	if(head.val != val){
        		cur.next = head;
        		cur = cur.next;
        	}
        	head = head.next;
        }
        cur.next = null;
        return start.next;
    }
}