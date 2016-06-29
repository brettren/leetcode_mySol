
// Given a non-empty binary search tree and a target value, find the value in the BST that 
// is closest to the target.

// Note:

// Given target value is a floating point.
// You are guaranteed to have only one unique value in the BST that is closest to the target.

public class Solution{
	public int closestValue(TreeNode root, double target) {
		if(root == null) return 0;
		int ret = root.val;
		while(root != null){
			if((double)root.val == target) return target;
			if(Math.abs(root.val - target) < Math.abs(ret - target)) ret = root.val;
			if((double)root.val < target){
				root = root.right;
			}
			else{
				root = root.left;
			}
		}
		return ret;
	}
}
