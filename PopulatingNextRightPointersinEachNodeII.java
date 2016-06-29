import java.util.ArrayList;
import java.util.List;



/** 
 * Populating Next Right Pointers in Each Node II
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *        1
 *       /  \
 *      2    3
 *     / \    \
 *    4   5    7
 * After calling your function, the tree should look like:
 *        1 -> NULL
 *       /  \
 *      2 -> 3 -> NULL
 *     / \    \
 *    4-> 5 -> 7 -> NULL
 */

// 当前level是处理好的，在当前loop处理下一层level的link
public class PopulatingNextRightPointersinEachNodeII {
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		TreeLinkNode cur = root;
		cur.next = null;
		List<TreeLinkNode> nextLevelNodes = new ArrayList<TreeLinkNode>(); // 把下一层的node都放进来,作为一个buffer
		while (true) {
			while (cur != null) {
				if (cur.left != null) {
					nextLevelNodes.add(cur.left);  // 注意这里只是放的pointer，只是copy的pointer，而不是clone内容
				}
				if (cur.right != null) {
					nextLevelNodes.add(cur.right);
				}
				cur = cur.next;  // 换到这个level的下一个node
			}
			for (int i = 0; i < nextLevelNodes.size() - 1; i++) {
				nextLevelNodes.get(i).next = nextLevelNodes.get(i + 1);  // 全部放进来后再一个个连起来
			}
			if (nextLevelNodes.size() > 0) {
				cur = nextLevelNodes.get(0);  // 换到下一个level的第一个
				nextLevelNodes.clear();  // 注意把这个list清空
			} else {
				break;
			}
		}
	}
}


// 01/10/15
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode parent = root;
        TreeLinkNode head = null;
        TreeLinkNode cur = null;
        while(parent != null){  // 这个while loop完成所有level的nodes的link
            head = null;
            cur = null;
            while(parent != null){  // 这个while loop完成一个level nodes的link
                if(parent.left != null) {
                    if(head == null){
                        head = parent.left;
                        cur = head;
                    }
                    else{
                        cur.next = parent.left;
                        cur = cur.next;
                    }
                }
                if(parent.right != null){
                    if(head == null){
                        head = parent.right;
                        cur = head;
                    }
                    else{
                        cur.next = parent.right;
                        cur = cur.next;
                    }
                }
                
                parent = parent.next;
            }
            parent = head;
        }
    }
}

// 03/21/15
// 这里用到三个指针，一个是当前level的parent，一直遍历这个level的list，还有next level的head和cur
// cur用来把下一个level的node连起来
// 一个循环遍历level，内部循环遍历level内的list
// 如果parent有left就把left连上，如果有right就继续把right连上，按照left right这个order
//                   parent
//                    /  \
//   head     cur
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode nexthead = null, cur = null, parent = root;
        while(parent != null){
            while(parent != null){  // 一个loop里先检查left，再检查right
                if(parent.left != null){  // 先检查parent的left
                    if(nexthead == null){  // 然后再看nexthead是不是null
                        nexthead = parent.left;
                        cur = nexthead;
                    }
                    else{
                        cur.next = parent.left;
                        cur = cur.next;
                    }
                }
                if(parent.right != null){  //再检查right
                    if(nexthead == null){
                        nexthead = parent.right;
                        cur = nexthead;
                    }
                    else{
                        cur.next = parent.right;
                        cur = cur.next;
                    }                    
                }
                parent = parent.next;
            }
            parent = nexthead;
            nexthead = null;
            cur = null;
        }
    }
}