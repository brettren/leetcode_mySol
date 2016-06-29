

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * 
 * You may assume that duplicates do not exist in the tree.
 * 
 */

// postorder 的顺序是 left，right，root
public class ConstructBinaryTreefromInorderandPostorderTraversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return buildTree(inorder, 0, inorder.length - 1, 
						 postorder, 0, postorder.length - 1);
	}

	public TreeNode buildTree(int[] inorder, int is, int ie, 
							  int[] postorder,int ps, int pe) {
		if (is > ie || ps > pe)
			return null;
		int rootVal = postorder[pe];  // 在postorder里最后一位才是root, 只要找到了root，就能立刻找到left和right的区间
		TreeNode root = new TreeNode(rootVal);
		for (int i = is; i <= ie; i++) {  // 遍历只是为了在inorder里找到root
			if (inorder[i] == rootVal) {
				TreeNode left = buildTree(inorder, is, i - 1,   // [is, i - 1]
										  postorder, ps, ps + i - is - 1);  // [ps, ps + i - is - 1]
				TreeNode right = buildTree(inorder, i + 1, ie, 
										  postorder, ps + i - is, pe - 1);
				root.left = left;
				root.right = right;
				break;
			}
		}
		return root;
	}
}


// 01/05/15
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length - 1,
                      postorder, 0, postorder.length - 1);
    }
    
    private TreeNode helper(int[] inorder, int is, int ie,
                            int[] postorder, int ps, int pe){
        if(is > ie || ps > pe) return null;
        int rootval = postorder[pe];
        TreeNode root = new TreeNode(rootval);
        int i = is;
        for( ; i < ie; i++){
            if(inorder[i] == rootval) break;
        }
        root.left = helper(inorder, is, i-1,
                           postorder, ps, ps+i-1-is);
        root.right = helper(inorder, i+1, ie,
                            postorder, ps+i-is, pe-1);
        return root;
    }
}

// 01/29/15
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int l = inorder.length;
        return helper(inorder, 0, l-1, postorder, 0, l-1);
    }
    
    private TreeNode helper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe){
        if(is > ie || ps > pe) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int i = is;
        for(; i <= ie; i++){
            if(inorder[i] == postorder[pe]){
                break;
            }
        }
        root.left = helper(inorder, is, i-1, postorder, ps, ps+i-is-1);
        root.right = helper(inorder, i+1, ie, postorder, ps+i-is, pe-1);
        return root;
    }
}