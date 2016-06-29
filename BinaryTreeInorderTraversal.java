

import java.util.ArrayList;
import java.util.Stack;

/**
 * For example: Given binary tree {1,#,2,3},
 * 1
 *  \
 *   2
 *  /
 * 3
 * return [1,3,2].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreeInorderTraversal {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> stack=new Stack<TreeNode>();
		TreeNode curr=root;
		while(curr!=null || !stack.isEmpty()){
			if(curr!=null){			                       
				stack.push(curr);
				curr=curr.left;  // 一直找到leftmost
			}else{
				curr=stack.pop();
				result.add(curr.val);  // 回到上一个parent，search right
				curr=curr.right;
			}
		}		
		return result;	
	}
}


// 03/21/15
// 用cur来trace下一个要check的node，先是沿着left path到最左端，然后回到上一个root，search right

// 还有个方法是用set来记录visited的node
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !st.isEmpty()){
            if(cur != null){
                st.push(cur);  // 这里是cur先探索到下一个node，再根据是否是null来放入stack
                cur = cur.left;
            }
            else{  // 说明已经把left path都search到底了, 或者原来的right也是null，需要回到上面的level
                cur = st.pop();
                ret.add(cur.val);
                cur = cur.right;
            }
        }
        return ret;
    }
}



// 05/29/15
// recursion
public class MaximumDepthOfBinaryTree {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<>();
		helper(ret, root);
		return ret;
	}

	public void helper(List<Integer> ret, TreeNode root){
		if (root == null) return;
		helper(ret, root.left);
		ret.add(root.val);
		helper(ret, root.right);
	}
}