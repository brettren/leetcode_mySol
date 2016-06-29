
/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 */

public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
		return determine(root) >= 0 ? true : false;
	}

	private int determine(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			int leftDepth = determine(root.left);
			int rightDepth = determine(root.right);
			if (leftDepth < 0 || rightDepth < 0
					|| Math.abs(leftDepth - rightDepth) > 1)
				return -1;
			return Math.max(leftDepth, rightDepth) + 1;
		}
	}
}


// 01/08/15
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(helper(root) >= 0) return true;
        else return false;
    }
    
    private int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        if(left < 0 || right < 0) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;  //加上当前root这个level
    }
}

// 03/21/15
// 返回left 和 right 子树的depth，比较是否diff大于1，如果是就置为false
// 也可以置为-1表示已经发现不是balanced
// 判断balanced就是每个结点的左右子树depth不能大于1
public class Solution {
    public boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        helper(root);
        return flag;
    }
    
    public int helper(TreeNode root){
        if(root == null) return 0;
        int l = helper(root.left);
        int r = helper(root.right);
        if(Math.abs(l-r) > 1) {
            flag = false;
        }
        return Math.max(l, r)+1;
    }
}