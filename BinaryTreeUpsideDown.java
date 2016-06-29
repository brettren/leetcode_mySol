// Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
// For example:
// Given a binary tree {1,2,3,4,5},
//     1
//    / \
//   2   3
//  / \
// 4   5
// return the root of the binary tree [4,5,2,#,#,3,1].
//    4
//   / \
//  5   2
//     / \
//    3   1  

// left -> root
// root -> right
// right -> left

//    parent
//    / \
// left right
//  / \
// 4   5

//这题有一个重要的限制就是，整个数的任何一个右孩子都不会再生枝节

// update的顺序是从下到上
public class Solution {
	public TreeNode UpsideDownBinaryTree(TreeNode root) {
	    return udtree(root, null);
	}
	 
	TreeNode udtree(TreeNode root, TreeNode parent) {
	    if(root==null) return parent; // base
	    TreeNode newRoot = udtree(root.left, root);  // 先update左子树,记下新的newroot
	    root.left = parent==null ? null : parent.right; // 1st node, both .left .right == null
	    root.right = parent;
	    return newRoot; // newRoot就是leftmost那个node，不断return回来到主程序
	}
}



// 03/24/15
// 其实就是原来的tree顺时针90度旋转，然后remove一些link，添加一些link
public class Solution {
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
    	if (root == null) return null;
        TreeNode node = root, parent = null, right = null;  
        while (node != null) {  
            TreeNode left = node.left;
            right = node.right;   // 先保存left和right，用于下个loop
            node.left = right;  
            node.right = parent;  
            parent = node;  
            node = left;  
        }  
        return parent;  
    }
}


