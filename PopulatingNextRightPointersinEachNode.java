

/** 
 * Given a binary tree
 *
 *   struct TreeLinkNode {
 *     TreeLinkNode *left;
 *     TreeLinkNode *right;
 *     TreeLinkNode *next;
 *   }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Note:
 *
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * 
 * For example,
 * Given the following perfect binary tree,
 *        1
 *       /  \
 *      2    3
 *     / \  / \
 *    4  5  6  7
 * After calling your function, the tree should look like:
 *        1 -> NULL
 *       /  \
 *      2 -> 3 -> NULL
 *     / \  / \
 *    4->5->6->7 -> NULL
 */
//类似level order一样的指针next 但是一层的最后指向null

// BFS
public class PopulatingNextRightPointersinEachNode {
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		TreeLinkNode curLevel = root;  // 表示每个level的第一个node
		TreeLinkNode cur = root;  // 表示在当前level的哪一个node
		curLevel.next = null;  // top level
		while (true) {
			while (cur != null) {
				if (cur.left != null) {
					cur.left.next = cur.right;  // 把下一个level left和right连起来
					if (cur.next != null) {
						cur.right.next = cur.next.left;  // node.right -> next.node.left
					}
				}
				cur = cur.next;  // 换到当前level的下一个node
			} // 当前的level都串联好了

			if (curLevel.left != null) {
				curLevel = curLevel.left;  // 换到下一层level
				cur = curLevel;
			} else {  // 没有下一层level了
				break;
			}
		}
	}
}

// 01/06/15
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode head = root;
        TreeLinkNode cur = head;
        while(head != null){
            while(cur != null){
                if(cur.left != null){
                    cur.left.next = cur.right;
                    if(cur.next != null){
                        TreeLinkNode next = cur.next;
                        cur.right.next = next.left;
                    }   
                }
                cur = cur.next;
            }
            head = head.left;
            cur = head;
        }
    }
}

// 01/24/15
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode levelhead = root;
        TreeLinkNode cur = levelhead;
        while(levelhead.left != null){
            while(cur != null){
                cur.left.next = cur.right;
                if(cur.next != null){
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelhead = levelhead.left;
            cur = levelhead;
        }
    }
}

// 03/24/15
// cur遍历当前level的node，把他的child连起来，nexthead记录下个level的开头，不断trace left
// 只需要一个cur在上一层，一个nexthead在下一层
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode nexthead = root.left, cur = root;
        while(nexthead != null){  // 如果没有下一个level，就可以结束了
            while(cur != null){
                cur.left.next = cur.right;
                if(cur.next != null){
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            cur = nexthead;
            nexthead = nexthead.left;
        }
    }
}