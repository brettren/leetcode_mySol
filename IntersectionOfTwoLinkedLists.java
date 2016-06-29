// Write a program to find the node at which the intersection of two singly linked lists begins.


// For example, the following two linked lists:

// A:          a1 → a2
//                    ↘
//                      c1 → c2 → c3
//                    ↗            
// B:     b1 → b2 → b3
// begin to intersect at node c1.


// Notes:

// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function returns.
// You may assume there are no cycles anywhere in the entire linked structure.
// Your code should preferably run in O(n) time and use only O(1) memory.

// 顺序遍历两个链表到尾结点的时候，我们不能保证在两个链表上同时到达尾结点。这是因为两个链表不一定长度一样。
// 但如果假设一个链表比另一个长l个结点，我们先在长的链表上遍历l个结点，之后再同步遍历，
// 这个时候我们就能保证同时到达最后一个结点了。由于两个链表从第一个公共结点考试到链表的尾结点，这一部分是重合的。
// 因此，它们肯定也是同时到达第一公共结点的。于是在遍历中，第一个相同的结点就是第一个公共的结点。

// 在这个思路中，我们先要分别遍历两个链表得到它们的长度，并求出两个长度之差。在长的链表上先遍历若干次之后，
// 再同步遍历两个链表，知道找到相同的结点，或者一直到链表结束。此时，如果第一个链表的长度为m，第二个链表的长度为n，
// 该方法的时间复杂度为O(m+n)。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;
        int sizeA = 0;
        int sizeB = 0;
        while(currA != null ){
        	currA = currA.next;
        	sizeA++;
        }
        while(currB != null){
        	currB = currB.next; 
        	sizeB++;       	
        }
        int diff = Math.abs(sizeA - sizeB);  // 得到两个链表size的差值
        currA = headA;
        currB = headB;
        if (sizeA > sizeB) {
        	for (int i = 0; i < diff; i++) {
        		currA = currA.next;
        	}
        }
        else{
        	for (int i = 0; i < diff; i++) {
        		currB = currB.next;
        	}
        }
        // 此时currA和currB离相交点的距离相同
        while(currA != null && currB != null){
        	if (currA == currB) {
        		return currA;
        	}
        	currA = currA.next;
        	currB = currB.next;
        }
        return null;
    }
}



// 01/13/15
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        if(headA == null || headB == null) return null;
        int sizeA = 0;
        int sizeB = 0;
        while(curA != null && curB != null){
            curA = curA.next;
            curB = curB.next;
            sizeA++;
            sizeB++;
        }
        while(curA != null){
            curA = curA.next;
            sizeA++;
        }
        while(curB != null){
            curB = curB.next;
            sizeB++;
        }
        int diff = Math.abs(sizeA - sizeB);
        curA = headA;
        curB = headB;
        if(sizeA > sizeB){
            while(diff > 0){
                curA = curA.next;
                diff--;
            }
        }
        else{
            while(diff > 0){
                curB = curB.next;
                diff--;
            }
        }
        while(curA != null && curB != null){
            if(curA == curB) return curA;
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}

// 01/28/15
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        int la = 0;
        int lb = 0;
        ListNode pa = headA;
        ListNode pb = headB;
        while(pa != null && pb != null){  // 一起到next
            pa = pa.next;
            la++;
            pb = pb.next;
            lb++;
        }
        while(pa != null){
            pa = pa.next;
            la++;
        }
        while(pb != null){
            pb = pb.next;
            lb++;
        }
        int diff = Math.abs(la-lb);
        pa = headA;
        pb = headB;
        if(la > lb){
            for(int i = 0; i < diff; i++){
                pa = pa.next;
            }
        }
        if(la < lb){
            for(int i = 0; i < diff; i++){
                pb = pb.next;
            }
        }
        while(pa != pb){
            pa = pa.next;
            pb = pb.next;
        }
        return pa;
    }
}


// 03/19/15
// 先从两端开始遍历list，得到两个length和diff，然后重新开始，让长的那个先next几步，直到diff为0
// 此时两条path的length相等，一起next，直到curA == curB
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        int la = 0, lb = 0;
        ListNode curA = headA, curB = headB;
        while(curA != null && curB != null){
            curA = curA.next;
            curB = curB.next;
            la++;
            lb++;
        }
        while(curA != null){
            curA = curA.next;
            la++;
        }
        while(curB != null){
            curB = curB.next;
            lb++;
        }
        curA = headA;
        curB = headB;
        while(la > lb){
            curA = curA.next;
            la--;
        }
        while(la < lb){
            curB = curB.next;
            lb--;
        }
        while(curA != curB){  // 注意先检查，可能一开始就是intersection了
            curA = curA.next;
            curB = curB.next;
        }
        return curA;  // 如果没有交点，那就是null
    }
}


