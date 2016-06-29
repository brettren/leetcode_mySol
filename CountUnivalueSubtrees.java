// Given a binary tree, count the number of uni-value subtrees.

// A Uni-value subtree means all nodes of the subtree have the same value.

// For example:

// Given binary tree,


//     5
//    / \
//   1   5
//  / \   \
// 5   5   5

// return 4.

// recursion返回一个subtree是否是uni tree
// 先判断左右subtree是否是true，然后判断他们val是否一致，然后判断和root.val是否一致，全部一致才return true

public class Solution {
	public static int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
    	if(root == null) return 0;
    	helper(root);
    	return count;
    }

    public boolean helper(TreeNode root){
    	if(root == null) return true;
    	boolean left = helper(root.left);
    	boolean right = helper(root.right);
    	if(left & right == false) return false;
    	if(root.left != null && root.right != null){
    		if(root.left.val != root.right.val) return false;
    		if(root.left.val != root.val) return false;
    		count++;
    		return true;
    	}
    	if(root.left != null){
    		if(root.left.val != root.val) return false;
    		count++;
    		return true;
    	}
    	if(root.right != null){
    		if(root.right.val != root.val) return false;
    		count++;
    		return true;
    	}
    	count++;
    	return true;
    }
}
