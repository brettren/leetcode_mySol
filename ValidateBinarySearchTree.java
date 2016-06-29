// Given a binary tree, determine if it is a valid binary search tree (BST).

// Assume a BST is defined as follows:

// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 这个inorder sol要求不能有dup
// 这是错误的sol
public class Solution {
	public static int lastVisit=Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
		if(root==null){	return true;}
		if(!isValidBST(root.left)){return false;}
		if(root.val <= lastVisit){return false;}
		lastVisit=root.val;
		if(!isValidBST(root.right)){return false;}
		return true;        
    }
}

// 这个sol结果AC通过，正确
public class Solution {
    public boolean isValidBST(TreeNode root) {  
        if (root == null || (root.left == null && root.right == null)) {  
            return true;  
        }     
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);  //因为某个node的val可能是integer的max或是min，所以只能用Long来定个上下限，不然就overflow了
    }  
      
    private boolean isValid(TreeNode root, long min, long max) {  
        if(root == null){  
            return true;  
        }  
        if ((long)root.val >= max || (long)root.val <= min) {  
            return false;  
        }  
        return isValid(root.left, (long)min,(long)root.val) && isValid(root.right, (long)root.val, (long)max);  
    }   
}

// 03/10/15
// 每次分给递归程序一个区间，root.val 在(min, max)之外就是false
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean helper(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return helper(root.left, min, (long)root.val) && helper(root.right, (long)root.val, max);
    }
}