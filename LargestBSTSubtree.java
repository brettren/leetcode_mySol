// Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
// where largest means subtree with largest number of nodes in it.

public class Solution{
	public int largestBSTSubtree(TreeNode root){
		return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public int helper(TreeNode root, int min, int max){
		if(root == null) return 0;
		if(root <= min || root >= max) return -1;
		int left = largestBSTSubtree(root.left, min, root.val);
		int right = largestBSTSubtree(root.right, root.val, max);
		if(left == -1 || right == -1) return -1;
		return left+right+1;
	}


}