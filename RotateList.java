/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * For example:
 * 
 * Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
 * 
 */
// 首先从head开始跑，直到最后一个节点，这时可以得出链表长度len。然后将尾指针指向头指针，
// 将整个圈连起来，接着往前跑len – k%len，从这里断开，就是要求的结果了。
public class RotateList {
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null)
			return head;
		int length = 1;
		ListNode last = head;
		while (last.next != null) {
			last = last.next;
			length++; //得出链表长度len
		}
		n = n % length;  
		if (n == 0)
			return head;  // 如果是倒数第0个，那就直接返回head
		int steps = length - n; // 找到新的head的位置
		last.next = head;  // 首尾相连形成circle
		while (steps > 0) {
			last = last.next;
			steps--;
		}
		ListNode ret = last.next;
		last.next = null;  // 这里断开
		return ret;
	}
}


// 03/10/15
// 注意要先求模得到n，如果n==0，那就直接返回。然后找到第l-n个node，断开，首尾相连，返回新的head
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if(head == null) return null;
        ListNode cur = head;
        int l = 1;
        while(cur.next != null){
            cur = cur.next;
            l++;
        }
        n %= l;  //得到rotate的余数
        if(n == 0) return head;
        cur.next = head;
        for(int i = 0; i < l-n; i++){
            cur = cur.next;
        }
        ListNode newStart = cur.next;
        cur.next = null;
        return newStart;
    }
}
