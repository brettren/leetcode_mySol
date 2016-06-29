/**
 * Given a binary tree, find the maximum path sum.
 * 
 * The path may start and end at any node in the tree.
 * 
 * For example: Given the below binary tree,
 * 
 *   1 
 *  / \ 
 * 2   3
 * 
 * Return 6.
 * 
 */

public class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		int[] max = new int[1]; // 数组可以reference based传递
		max[0] = Integer.MIN_VALUE;
		maxPathSum(root, max);
		return max[0];
	}
 
	// bottom up 方法   只有left tree或right tree 存在大于0的path，才能和root相加

	// 但是似乎没有考虑到root < 0 的情况
	private int maxPathSum(TreeNode root, int[] max) {
		if (root.left == null && root.right == null) {
			max[0] = root.val > max[0] ? root.val : max[0];
			return root.val;
		} else if (root.left != null && root.right == null) { // 只有左子树
			int left = maxPathSum(root.left, max); // 左子树的max path value
			max[0] = left > 0 ? Math.max(left + root.val, max[0]) :  // 把root和左子树相连计算path
					            Math.max(root.val, max[0]);
			return left > 0 ? left + root.val : root.val;
		} else if (root.left == null && root.right != null) { // 只有右子树
			int right = maxPathSum(root.right, max);// 右子树的max path value
			max[0] = right > 0 ? Math.max(right + root.val, max[0]) : 
								 Math.max(root.val, max[0]);
			return right > 0 ? right + root.val : root.val;
		} else {  // 有左右子树
			int left = maxPathSum(root.left, max);
			int right = maxPathSum(root.right, max);
			int tmp = root.val;
			tmp += left > 0 ? left : 0;  // root+left
			tmp += right > 0 ? right : 0; // root+left+right
			max[0] = tmp > max[0] ? tmp : max[0];
			return Math.max(Math.max(left, right), 0) + root.val;// return的是以这个root的tree的max path value，所以只能在left和right选一边
		}
	}
}

// 01/08/15
public class Solution {
    public int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Divide
        int left = helper(root.left);
        int right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left, right) + root.val;
        singlePath = Math.max(singlePath, 0);  //保证singlePath >= 0

        max = Math.max(max, left + right + root.val);  // 所以这里left和right都 >= 0

        return singlePath;
    }
}

// 03/09/15
// 考虑两个max，一个全局max，一个local max需要返回; local max考虑root+left或root+right或只是root；
// 全局max需要在local max和 left+root+right两个之间选最大
public class Solution {
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        int[] ret = new int[1];
        ret[0] = Integer.MIN_VALUE;
        helper(root, ret);
        return ret[0];
    }
    
    public int helper(TreeNode root, int[] ret){
        if(root == null) return 0;
        int left = helper(root.left, ret);
        int right = helper(root.right, ret);
        int tmp = Math.max(root.val+left, root.val+right);
        tmp = Math.max(tmp, root.val);
        int max = tmp;
        tmp = Math.max(tmp, root.val+left+right);
        ret[0] = Math.max(ret[0], tmp);
        return max;
    }
}