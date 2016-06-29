

/** 
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *   1
 *  / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = 12 + 13 = 25.
 */

public class SumRoottoLeafNumbers {
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return sum(root, 0);
		}
	}

	public int sum(TreeNode root, int prev) {
		if (root.left == null && root.right == null) {  // leaf node
			return 10 * prev + root.val;  // 返回的是这条path的值
		} else if (root.left != null && root.right != null) {  // 有左右子树
			return sum(root.left, 10 * prev + root.val)
				 + sum(root.right, 10 * prev + root.val);  // 左边的和右边的相加，返回两条path的值
		} else if (root.left != null) {  // 只有左子树
			return sum(root.left, 10 * prev + root.val);
		} else {  // 只有右子树
			return sum(root.right, 10 * prev + root.val);
		}
	}
}


// 01/04/15
public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        return helper(root, 0);
    }
    
    private int helper(TreeNode root, int prev){  //prev是root上面带下来的 这里是不断乘以10到下面的level
        if(root.left == null && root.right == null) return prev*10 + root.val;  // 到leaf node，返回一个path的值
        if(root.left != null && root.right == null) return helper(root.left, prev*10 + root.val);
        if(root.left == null && root.right != null) return helper(root.right, prev*10 + root.val);
        //if(root.left != null && root.right != null) 
        return helper(root.left, prev*10 + root.val) + helper(root.right, prev*10 + root.val);
    }
}

// 03/20/15
// 因为是从root到leaf，所以一个path的结果只有到了leaf node才知道，所以需要一直递归到leaf
// 判断是leaf node就是看没有左右子树
public class Solution {
    public int sumNumbers(TreeNode root) {
        int[] ret = new int[1];
        if(root == null) return 0;
        helper(ret, root, 0);
        return ret[0];
    }
    
    public void helper(int[] ret, TreeNode root, int prev){
        if(root == null) return;
        if(root.left == null && root.right == null){
            ret[0] += prev*10+root.val;  // 到leaf node，返回一个path的值
            return;
        }
        if(root.left != null) helper(ret, root.left, prev*10+root.val);
        if(root.right != null) helper(ret, root.right, prev*10+root.val);
    }
}