

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,#,2,3},
 *   1
 *    \
 *     2
 *    /
 *   3
 * return [3,2,1].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * Solution:
 * 
 * 1. Root - Right - Left
 * 2. Then reverse the sequence
 * 3. Left - Right - Root
 *
 */

public class BinaryTreePostorderTraversal {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
    	ArrayList<Integer> ret = new ArrayList<Integer>();
		if (root == null)
			return ret;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(root!=null || !stack.isEmpty()){
			if(root!=null){	
				ret.add(root.val);						                       
				stack.push(root);
				root=root.right;  // 一直找到leftmost
			}else{
				root=stack.pop();							
				root=root.left;
			}
		}		
		Collections.reverse(ret);
		return ret;
    }
}


// 01/26/15
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = root;
        while(!st.isEmpty() || cur != null){
            if(cur != null){
                ret.add(cur.val);
                st.push(cur.left);
                cur = cur.right;
            }
            else{
                cur = st.pop();
            }
        }
        Collections.reverse(ret);
        return ret;
    }
}



// 03/21/15
// 按照root right left的顺序放入list
// 记下root的val，然后放left到stack，然后right放stack

// 还有个方法和reverse相同原理，就是先把element放入另一个stack，然后一个个pop就是逆序了
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode tmp = st.pop();
            ret.add(tmp.val);
            if(tmp.left != null) st.push(tmp.left);
            if(tmp.right != null) st.push(tmp.right);
        }
        Collections.reverse(ret);  // 注意list的reverse用collections
        return ret;
    }
}
