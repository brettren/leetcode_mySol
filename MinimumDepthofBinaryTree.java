

/** 
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node. 
 */

// 注意这里的leaf node，不能有child
public class MinimumDepthofBinaryTree {
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)  // 没有child
			return 1;
		else {  // 说明root至少有一个child
			int leftDepth = root.left != null ? minDepth(root.left)
					: Integer.MAX_VALUE;   // 注意默认为max value
			int rightDepth = root.right != null ? minDepth(root.right)
					: Integer.MAX_VALUE;
			return Math.min(leftDepth, rightDepth) + 1;  // 左子树和右子树的min depth再加上当前的level
		}
	}
}



// 03/20/15

// 这题要注意什么是leaf node，只有没有左右的child情况才能开始算
// 如果只有一边有子树，那就返回子树结果+1
// 如果两边都有子树，返回两边子树的min+1
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        if(root.left == null && root.right != null) return minDepth(root.right)+1;
        if(root.left != null && root.right == null) return minDepth(root.left)+1;
        return Math.min(minDepth(root.left), minDepth(root.right))+1;
    }
}