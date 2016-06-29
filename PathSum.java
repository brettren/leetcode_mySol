

/** 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
 * such that adding up all the values along the path equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \      \
 *       7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root.left == null && root.right == null && root.val == sum)  // 没有child了
			return true;
		boolean l = root.left != null ? hasPathSum(root.left, sum - root.val)
				: false;
		boolean r = root.right != null ? hasPathSum(root.right, sum - root.val)
				: false;
		return l || r;
	}
}

// 01/06/15
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && sum == root.val) return true;
        if(root.left != null && hasPathSum(root.left, sum - root.val)) return true;
        if(root.right != null && hasPathSum(root.right, sum - root.val)) return true;
        return false;
    }
}

// 03/20/15
// 如果是leaf node，看剩下的sum == root.val
// 如果有子树，就左右两边都search一下
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            if(sum == root.val) return true;  // 要注意是sum 和 root.val比较
            else return false;
        }
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}