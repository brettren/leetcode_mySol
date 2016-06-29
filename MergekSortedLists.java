

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/** 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 
 *
 */


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

public class MergekSortedLists {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null || lists.isEmpty())
			return null;

		Comparator<ListNode> comp = new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				if (o1.val > o2.val)
					return 1;
				return 0;
			}
		};
		// 这里用heap来存放每个list的head，这样就不用每个head去比较找min了，去取一个node的时间复杂度可以减少为lgN
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), comp);  // ascending order  min heap
		for (ListNode node : lists) {
			if (node != null)
				heap.add(node); // 把所有heads放入heap
		}
		ListNode head = new ListNode(0); 
		ListNode cur = head;
		while (!heap.isEmpty()) {
			cur.next = heap.poll(); // 继续取出heap的min root
			cur = cur.next;
			if (cur.next != null)  // 如果这个list还有下一个node
				heap.add(cur.next);  // 把这个linkedlist的下一个node放入heap   heap总是保存各个list的下一个未加入merged list的node
		}
		return head.next;
	}
}


// 01/03/15
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0) return null;
        
        Comparator<ListNode> cmp = new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;
            }
        };
        
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), cmp);
        ListNode head = new ListNode(0);
        ListNode current = head;
        for(ListNode n: lists){
            if(n != null) heap.add(n);
        }
        
        while(!heap.isEmpty()){
            current.next = heap.poll();
            current = current.next;
            if(current.next != null) heap.add(current.next);
        }
        
        return head.next;
    }
}

// 02/09/15
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0) return null;
        
        Comparator<ListNode> comp = new Comparator<ListNode>() {
            public int compare(ListNode n1, ListNode n2){
                if(n1.val < n2.val) return -1;
                else if(n1.val > n2.val) return 1;
                else return 0;
            }
        };
        
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), comp);
        
        for(ListNode n: lists){
            if(n != null){
                heap.add(n);
            }
        }
        ListNode start = new ListNode(0);
        ListNode cur = start;
        while(!heap.isEmpty()){  // heap里只要还有node
            cur.next = heap.remove();
            cur = cur.next;
            if(cur.next != null){
                heap.add(cur.next); // 取出一个同时放下一个进去
            }
        }
        return start.next;
    }
}

// 03/09/15
// 用heap，size为lists的size，把每个链表的第一个node放入heap，每次取出root都把这个node的next再放入heap
// 知道heap为空，不停地取min，再插入这个node.next
// 要注意Comparator是interface需要开头大写，还有插入heap时要检查是否是null
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists.size() == 0) return null;
        ListNode start = new ListNode(0);
        ListNode cur = start;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.size(), new Comparator<ListNode>(){
            public int compare(ListNode n1, ListNode n2){
                if(n1.val < n2.val) return -1;
                else if(n1.val > n2.val) return 1;
                else return 0;
            }
        });
        for(ListNode n: lists){
            if(n != null) heap.add(n);
        }
        while(!heap.isEmpty()){
            ListNode n = heap.poll();
            cur.next = n;
            cur = cur.next;
            if(n.next != null){
                heap.add(n.next);
            }
        }
        return start.next;
    }
}