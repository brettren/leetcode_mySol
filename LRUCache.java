

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It
 * should support the following operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. set(key, value) - Set or insert the
 * value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new
 * item.
 * 
 */

public class LRUCache {

	public class CacheItem<K, V> {  // double edge link list，当删除某个item时，可以快速修改prev的next
  
		private K key;
		private V value;
		private CacheItem<K, V> prev;
		private CacheItem<K, V> next;

		public CacheItem<K, V> getPrev() {
			return prev;
		}

		public void setPrev(CacheItem<K, V> prev) {
			this.prev = prev;
		}

		public CacheItem<K, V> getNext() {
			return next;
		}

		public void setNext(CacheItem<K, V> next) {
			this.next = next;
		}

		public CacheItem(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	public class BidirectionalList<K, V> {  // 双向link list
		private CacheItem<K, V> head = new CacheItem<K, V>(null, null);  // head.next才是list真正的开始
		private CacheItem<K, V> tail = head;

		public void insertTail(CacheItem<K, V> node) {
			tail.next = node;
			node.prev = tail;
			node.next = null;
			tail = node;
		}

		public void delete(CacheItem<K, V> node) {  // 给出这个node的位置，delete
			if (head.next == null)
				return;
			if (node == head.next) {
				deleteHead();
			} else if (node == tail) {
				deleteTail();
			} else {
				node.prev.next = node.next;
				node.next.prev = node.prev;
				node.next = null;
				node.prev = null;
			}
		}

		public CacheItem<K, V> deleteTail() {
			if (head.next == null)
				return null;
			CacheItem<K, V> ret = tail;
			tail = tail.prev;
			tail.next = null;
			ret.prev = null;
			ret.next = null;
			return ret;
		}

		public CacheItem<K, V> deleteHead() {
			if (head.next == null)
				return null;
			if (head.next == tail) {
				tail = tail.prev;
			}
			CacheItem<K, V> ret = head.next;
			head.next = ret.next;
			if (ret.next != null) {
				ret.next.prev = head;
			}
			ret.next = null;
			ret.prev = null;
			return ret;
		}
	}

	private Map<Integer, CacheItem<Integer, Integer>> cacheMap = new HashMap<Integer, CacheItem<Integer, Integer>>();
	//用map来快速定位
	private BidirectionalList<Integer, Integer> cacheList = new BidirectionalList<Integer, Integer>();
	private int cacheSize = 0;
	private int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		CacheItem<Integer, Integer> itm = cacheMap.get(key);
		if (itm == null)
			return -1;
		cacheList.delete(itm);
		cacheList.insertTail(itm);
		return itm.value.intValue();
	}

	public void set(int key, int value) {
		if (cacheMap.containsKey(key)) {  
			updateItem(key, value);
		} else if (cacheSize == capacity && removeLeastUsed()) {  // 如果map里不存在，那就需要新建一个，head就是LRU
			cacheSize--;
			addItem(key, value);
		} else {
			addItem(key, value);
		}
	}

	private void addItem(int key, int value) {
		CacheItem<Integer, Integer> newCacheItem = new CacheItem<Integer, Integer>(
				key, value);
		cacheList.insertTail(newCacheItem);
		cacheMap.put(key, newCacheItem);
		cacheSize++;
	}

	private void updateItem(int key, int value) {
		CacheItem<Integer, Integer> exitedKey = cacheMap.get(key);
		exitedKey.value = value;
		cacheList.delete(exitedKey); // 这里delete只是让exitedKey这个node从list里separate出来，而不是完全删掉这个node！！！！！
		cacheList.insertTail(exitedKey); // exitedKey成为most recently used
	}

	private boolean removeLeastUsed() {
		CacheItem<Integer, Integer> removedKey = cacheList.deleteHead();
		cacheMap.remove(removedKey.key);  // 这里不需要真的delete掉这个item，只需要在map里remove就可以
		return removedKey == null ? false : true; // true表示remove的head的确存在
	}
}



// 02/12/15
public class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> map;
    
    public class Node {
        private int key;
        private int value;
        private Node next;
        private Node prev;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node n = map.get(key);
        n.next.prev = n.prev;
        n.prev.next = n.next;
        
        moveToTail(n);
        return n.value;
    }
    
    public void set(int key, int value) {
        if(!map.containsKey(key)){
            Node n = new Node(key, value);
            moveToTail(n);
            map.put(key, n);
            if(map.size() == capacity){
                map.remove(head.next.key);  // 记得要先把LRU那个先remove
                head.next = head.next.next;
                head.next.prev = head;
            }
        }
        else{
            Node n = map.get(key);
            n.prev.next = n.next;
            n.next.prev = n.prev;
            
            n = new Node(key, value);
            map.put(key, n);
            moveToTail(n);
        }
    }
    
    public void moveToTail(Node n){
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
        n.prev.next = n;
    }
}

// 03/03/15
// doubled linked list keep prev and next, 2 pointer head and tail.
// need Map to keep key and value
// 要注意的是及时更新map，当新的node插入，或者LRU被删除，还有更新已有的node
public class LRUCache {
    public class Node{
        private int key;
        private int val;
        private Node prev;
        private Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
        public int getVal(){
            return val;
        }
    }
    
    private int cap;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map = new HashMap<>();
    
    public LRUCache(int capacity) {
        this.cap = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node n = map.get(key);
        n.prev.next = n.next;
        n.next.prev = n.prev; // remove n
        insertToTail(n);  // lru is at tail
        return n.getVal();
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n = new Node(key, value);
            insertToTail(n);
            map.put(key, n);
        }
        else{
            if(map.size() >= this.cap){
                map.remove(head.next.key);
                head.next.next.prev = head;
                head.next = head.next.next;
            }
            Node n = new Node(key, value);
            insertToTail(n);
            map.put(key, n);
        }
    }
    
    public void insertToTail(Node n){
        n.prev = tail.prev;
        n.next = tail;
        tail.prev.next = n;
        tail.prev = n;
    }
}