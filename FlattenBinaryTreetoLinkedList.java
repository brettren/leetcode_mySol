

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example,
 * Given
 *
 *        1
 *       / \
 *      2   5
 *     / \   \
 *    3   4   6
 * The flattened tree should look like:
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 */

// preorder 
// 把left tree连到root后面，再把right tree连到left tree后面
public class FlattenBinaryTreetoLinkedList {
	public void flatten(TreeNode root) {  // 这个过程就是把左子树连到root和右子树的中间
        if (root == null) return;
        if (root.left != null) {
            TreeNode rightMost = findRightMost(root.left);  // 左子树的rightmost
            TreeNode rightChild = root.right;  // 记下原来的right child
            root.right = root.left;
            root.left = null;
            rightMost.right = rightChild;
        }
        flatten(root.right);
    }
    
    private TreeNode findRightMost(TreeNode root) {  // 找到list的tail
        if (root == null || root.right == null) return root;
        return findRightMost(root.right);
    }
}


// 01/05/15
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left != null){
            root.left = null;
            TreeNode rightmost = helper(left);
            root.right = left;
            rightmost.right = right;
        }
        flatten(root.right);
    }
    
    private TreeNode helper(TreeNode root){
        if(root == null) return null;
        while(root.right != null){  // 因为找的是最右边的node，所以找到leaf node
            root = root.right;
        }
        return root;
    }
}

// 03/20/15
// 如果root的left有子树，就把左子树移到右子树来，并且找到rightmost，和原来的右子树相连
// 然后继续递归flatten(root.right)
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        if(root.left != null){
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode rightmost = helper(left);
            root.left = null;
            root.right = left;
            rightmost.right = right;
        }
        flatten(root.right);
    }
    
    private TreeNode helper(TreeNode root){
        if(root == null) return null;
        while(root.right != null){
            root = root.right;
        }
        return root;
    }
}