

import java.util.ArrayList;

/** 
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \    / \
 *       7    2  5   1
 * return
 * [
 *  [5,4,11,2],
 *  [5,8,4,5]
 * ]
 */

public class PathSumII {
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root != null) {
			ArrayList<Integer> path = new ArrayList<Integer>();
			findPathSum(root, sum, result, path);
		}
		return result;
	}

	public void findPathSum(TreeNode root, int sum,
			ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path) {
		if (root == null)
			return;
		if (root.left == null && root.right == null && root.val == sum) {  // 必须是leaf node，一个path成立
			ArrayList<Integer> clone = new ArrayList<Integer>(path);
			clone.add(root.val);
			result.add(clone);
		} else {
			path.add(root.val);

			if (root.left != null) {
				findPathSum(root.left, sum - root.val, result, path); // search left
			}
			if (root.right != null) {
				findPathSum(root.right, sum - root.val, result, path); // search right
			}
			
			path.remove(path.size() - 1);
		}
	}
}


// 02/10/15
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        if(root == null) return ret;
        helper(ret, tmp, root, 0, sum);
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, List<Integer> tmp, TreeNode root, int prev, int sum){
        if(root == null) return;
        if(root.left == null && root.right == null){
            if(prev+root.val == sum){
                tmp.add(root.val);
                ret.add(new ArrayList<Integer>(tmp));
                tmp.remove(tmp.size()-1);
            }
            return;
        }
        tmp.add(root.val);
        helper(ret, tmp, root.left, prev+root.val, sum);
        helper(ret, tmp, root.right, prev+root.val, sum);
        tmp.remove(tmp.size()-1);
    }
}


// 03/15/15
// 递归，传递sum-root.val给递归程序，每个节点分为left和right两个path
// 当到达leaf node时候，判断是否sum == root.val，如果是tmp就是一组sol
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) return ret;
        List<Integer> tmp = new ArrayList<>();
        helper(ret, tmp, root, sum);
        return ret;
    }
    
    public void helper(List<List<Integer>> ret, List<Integer> tmp, TreeNode root, int sum){
        if(root.left == null && root.right == null){
            if(root.val == sum){
                tmp.add(root.val);
                ret.add(new ArrayList(tmp));
                tmp.remove(tmp.size()-1);
            }
            return;
        }
        tmp.add(root.val);
        if(root.left != null) helper(ret, tmp, root.left, sum-root.val);
        if(root.right!= null) helper(ret, tmp, root.right, sum-root.val);
        tmp.remove(tmp.size()-1);
    }
}

