/** 
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */

// 分两个list  
// 1->2->2
// 4->3->5
// list1.next = list2.head


// 下面的方法比较繁琐

public class PartitionList {
	public ListNode partition(ListNode head, int x) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode slow = start;
        while (slow.next != null) {
            if (slow.next.val < x) {
                slow = slow.next;
            } else {
                break;
            }
        }
        ListNode fast = slow;
        while (fast.next != null) {
            if (fast.next.val >= x) {
                fast = fast.next;
            } else {
                ListNode target = fast.next;
                fast.next = target.next;  // 取出target这个node
                target.next = slow.next;
                slow.next = target; // target连到slow后面
                slow = slow.next;  // slow始终保持小于x的list的最后一个node
            }
        }
        return start.next;
    }
}

public class PartitionList {
    public ListNode partition(ListNode head, int x) { 
        if (head == null) {
            return head;
        }      
        ListNode left_start = new ListNode(0);
        ListNode right_start = new ListNode(0);
        ListNode left_p = left_start;
        ListNode right_p = right_start;
        while(head != null){
            if(head.val < x){
                left_p.next = head;
                left_p = left_p.next;
            }
            else{
                right_p.next = head;
                right_p = right_p.next;
            }
            head = head.next;
        }
        right_p.next = null; // 记得要把right收尾赋null
        left_p.next = right_start.next;
        return left_start.next;
    }
}   


// 01/06/15
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
        ListNode leftS = new ListNode(0);
        ListNode rightS = new ListNode(0);
        ListNode leftE = leftS;
        ListNode rightE = rightS;
        while(head != null){
            if(head.val < x){
                leftE.next = head;
                leftE = leftE.next;
                head = head.next;
            }
            else{
                rightE.next = head;
                rightE = rightE.next;
                head = head.next;
            }
        }
        rightE.next = null; // 记得要把right收尾赋null
        leftE.next = rightS.next;
        return leftS.next;
    }
}


// 03/15/15
// 遍历原来的数组，新建两个list，一个连小的，一个连大的
// 最后再把小的末端连到大的初始端，大的末端连null断开
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
        ListNode s1 = new ListNode(0);
        ListNode s2 = new ListNode(0);
        ListNode p1 = s1;
        ListNode p2 = s2;
        while(head != null){
            if(head.val < x){
                p1.next = head;
                p1 = p1.next;
                head = head.next;
            }
            else{
                p2.next = head;
                p2 = p2.next;
                head = head.next;
            }
        }
        p1.next = s2.next;
        p2.next = null;
        return s1.next;
    }
}