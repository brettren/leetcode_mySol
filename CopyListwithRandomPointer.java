

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */


/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

//这里的做法是用link来表示original和clone的影射

public class CopyListwithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return head;
		RandomListNode cur = head;
		while (cur != null) {
			RandomListNode newNode = new RandomListNode(cur.label);
			newNode.next = cur.next;
			cur.next = newNode;
			cur = newNode.next;
		}  // 也就是original和clone相互交叉link
		cur = head;
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;  // 把clone的random pointer也都指定
			}
			cur = cur.next.next;
		}
		cur = head.next; // clone的list head
		// 这里要注意原来的linked list不能被modified，所以要改回原来的link
		RandomListNode newHead = cur;
		RandomListNode old = head;
		while (old != null) {
			old.next = cur.next;  // original 恢复原来的next link
			if (old.next != null) {
				cur.next = old.next.next;
			}
			cur = cur.next;
			old = old.next;
		}
		return newHead;
	}
}

//另一种做法. 用hashmap存新的点和老的点的影射关系，但是hash只能用在每个key是unique的情况，如果有重复的结果就不正确
public class CopyListwithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode cur = head;
		RandomListNode next;
		RandomListNode random;
		RandomListNode clone;
		map.put(cur, new RandomListNode(cur.label));
		while(cur != null){
			next = cur.next;
			random = cur.random;
			if (next != null && !map.containsKey(next)) {
				map.put(next, new RandomListNode(next.label));
			}
			if (random != null && !map.containsKey(random)) {
				map.put(random, new RandomListNode(random.label));
			}
			clone = map.get(cur);
			clone.next = cur.next;	
			clone.random = cur.random;

			cur = next;
		}
		return map.get(head);
	}
}

// 01/12/15
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        map.put(head, new RandomListNode(head.label));
        RandomListNode cur = head;
        while(cur != null){
            RandomListNode clone = map.get(cur);
            RandomListNode next = cur.next;
            RandomListNode random = cur.random;
            RandomListNode cloneNext = null;
            RandomListNode cloneRandom = null;
            if(next != null){
                if(!map.containsKey(next)){
                    cloneNext = new RandomListNode(next.label);
                    map.put(next, cloneNext);
                }
                else{
                    cloneNext = map.get(next);
                }
            }
            if(random != null){
                if(!map.containsKey(random)){
                    cloneRandom = new RandomListNode(random.label);
                    map.put(random, cloneRandom);
                }
                else{
                    cloneRandom = map.get(random);
                }
            }
            clone.next = cloneNext;
            clone.random = cloneRandom;
            cur = cur.next;
        }
        return map.get(head);
    }
}

// 02/06/15
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        if(head == null) return null;
        RandomListNode cur = head;
        map.put(cur, new RandomListNode(cur.label));  // 先把head放入map，这样在每个cur都可以知道已经在map里了
        while(cur != null){
            RandomListNode next = cur.next;
            RandomListNode random = cur.random;
            RandomListNode clone = map.get(cur);
            RandomListNode clonenext = null;
            RandomListNode clonerandom = null;
            if(next != null){
                if(!map.containsKey(next)){
                    clonenext = new RandomListNode(next.label);
                    map.put(next, clonenext);
                }
                else{
                    clonenext = map.get(next);
                }
                clone.next = clonenext;
            }
            if(random != null){
                if(!map.containsKey(random)){
                    clonerandom = new RandomListNode(random.label);
                    map.put(random, clonerandom);
                }
                else{
                    clonerandom = map.get(random);
                }
                clone.random = clonerandom;
            }
            cur = cur.next;
        }
        return map.get(head);
    }
}


// 03/13/15
// 遍历list每个node，用map来维持original和clone的联系，当前的node cur一定已经在map里了，检查它的next和random是否在map里
// 根据情况如果next或random不在map里就分配新的clone，如果之前已经实例化clone了，就可以直接指向clone
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode cur = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        map.put(head, new RandomListNode(head.label));
        while(cur != null){
            RandomListNode clone = map.get(cur);
            
            RandomListNode next = cur.next;
            if(next != null){
                if(map.containsKey(next)){
                    clone.next = map.get(next);
                }                
                else{
                    map.put(next, new RandomListNode(next.label));
                    clone.next = map.get(next);
                }
            }
            RandomListNode random = cur.random;
            if(random != null){
                if(map.containsKey(random)){
                    clone.random = map.get(random);
                }                
                else{
                    map.put(random, new RandomListNode(random.label));
                    clone.random = map.get(random);
                }
            }
            cur = cur.next;
        }
        return map.get(head);
    }
}