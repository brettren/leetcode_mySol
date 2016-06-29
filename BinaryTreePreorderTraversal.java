

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,#,2,3},
 *  1
 *   \
 *    2
 *   /
 *  3
 * return [1,2,3].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 */

public class BinaryTreePreorderTraversal {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (root == null)
			return ret;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(root!=null || !stack.isEmpty()){
			if(root!=null){		
				ret.add(root.val);	                       
				stack.push(root);
				root=root.left;  // 一直找到leftmost
			}else{
				root=stack.pop();				
				root=root.right;
			}
		}		
		return ret;
	}
}

// 01/12/15
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode tmp = st.pop();
            ret.add(tmp.val);
            if(tmp.right != null) st.push(tmp.right);
            if(tmp.left != null) st.push(tmp.left);
        }
        return ret;
    }
}