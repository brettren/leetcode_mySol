

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

public class ConvertSortedArraytoBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] num) {
		return sortedArrayToBST(num, 0, num.length - 1);
	}

	public TreeNode sortedArrayToBST(int[] num, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;
			TreeNode left = sortedArrayToBST(num, start, mid - 1);
			TreeNode right = sortedArrayToBST(num, mid + 1, end);
			TreeNode node = new TreeNode(num[mid]);
			node.left = left;
			node.right = right;
			return node;
		}
		return null;
	}
}

// 01/05/15
public class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
        int l = num.length;
        if(l == 0) return null;
        return helper(num, 0, l-1);
    }
    
    private TreeNode helper(int[] num, int s, int e){
        if(s > e) return null;
        int mid = (s+e)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, s, mid-1);
        root.right = helper(num, mid+1, e);
        return root;
    }
}