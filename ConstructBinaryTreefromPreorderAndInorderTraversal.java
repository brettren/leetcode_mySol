/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * 
 * You may assume that duplicates do not exist in the tree.
 */
/*
 *             5
 *            /  \          
 *           2    7 
 *          / \  / \
 *         1  3 6   8     
 *中序     1235678
 *前序    5213768  所以root是前序第一个 val是5 
 */

// 最重要的是inorder tree里 root - leftmost = left subtree
public class ConstructBinaryTreefromPreorderAndInorderTraversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return buildTree(inorder, 0, inorder.length - 1, 
						 preorder, 0, preorder.length - 1);  // [0, length1)   [0, length2)
	}

	private TreeNode buildTree(int[] inorder, int is, int ie, 
							   int[] preorder,int ps, int pe) {			
		if (is > ie || ps > pe) 
			return null;
		int rootVal = preorder[ps];  // preorder是root，left，right的顺序
		TreeNode root = new TreeNode(rootVal);
		for (int i = is; i <= ie; i++) {  // 遍历只是为了在inorder里找到root
			if (inorder[i] == rootVal) {
				TreeNode left = buildTree(inorder, is, i - 1,   // [is, i - 1]
										  preorder, ps + 1, ps + i - is);  // 
				TreeNode right = buildTree(inorder, i + 1, ie, 
										  preorder, ps + i - is + 1, pe);
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length-1,
                      inorder, 0, inorder.length-1);
    }
    
    private TreeNode helper(int[] preorder, int ps, int pe,
                            int[] inorder, int is, int ie){
        if(ps > pe || is > ie) return null;
        int rootval = preorder[ps];
        TreeNode root = new TreeNode(rootval);
        int i = is;
        for( ; i <= ie; i++){
            if(rootval == inorder[i]) break;
        }
        root.left = helper(preorder, ps+1, ps+i-is,
                           inorder, is, i-1);
        root.right = helper(preorder, ps+i-is+1, pe,
                            inorder, i+1, ie);
        return root;
    }
}

// 03/15/15
// 因为preorder的第一个就是root，然后从inorder里去找这个root的位置，可以知道left的size
// 可以设置left和right子树的区间到递归程序，得到返回的子树root，和当前的root相连
// 最后主程序返回的就是总root
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int l = preorder.length;
        return helper(preorder, 0, l-1, inorder, 0, l-1);
    }
    
    private TreeNode helper(int[] preorder, int ps, int pe, int[] inorder, int is, int ie){
        if(ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(preorder[ps]);
        int i = is;
        for(; i <= ie; i++){
            if(preorder[ps] == inorder[i]){
                break;
            }
        }
        root.left = helper(preorder, ps+1, ps+i-is, inorder, is, i-1);
        root.right = helper(preorder, ps+i-is+1, pe, inorder, i+1, ie);
        return root;
    }
}