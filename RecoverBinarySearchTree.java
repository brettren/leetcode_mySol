

import java.util.ArrayList;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Note:
 * 
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */

public class RecoverBinarySearchTree {
	TreeNode first = null,second = null,pre = null;  
	public void recoverTree(TreeNode root) {  
    	findNode(root);  
    	swap(first, second);  
    }  

    private void findNode(TreeNode root){  
        if(root == null){  
            return;  
        }  
        findNode(root.left);  
        if(pre != null && root.val < pre.val){  
        	// 因为第一个肯定是大的放到前面来了，第二个就是小的放后面来了
            if(first == null){  
                first = pre;  //第一次找到descending，first存较大的值
            }  
            second = root;  //第一次第二次找到descending，second都存较小的值
        }  
        pre = root;  
        findNode(root.right);  
    }  
    private void swap(TreeNode node1,TreeNode node2){  
        int tem = node1.val;  
        node1.val = node2.val;  
        node2.val = tem;  
    } 
}



// 01/10/15
public class Solution {
    public TreeNode prev = null;
    public TreeNode first = null;
    public TreeNode second = null;
    
    public void recoverTree(TreeNode root) {
        helper(root);
        swap(first, second);
    }
    
    private void helper(TreeNode root){
        if(root == null) return;
        helper(root.left);
        if(prev != null && prev.val >= root.val){
            if(first == null){
                first = prev;
                second = root;  // 考虑到可能是相邻两个node swap, 这样那只会检查出一次descending的情况
            }
            else{
                second = root;
            }
        }
        prev = root;
        helper(root.right);
    }
    
    private void swap(TreeNode first, TreeNode second){
        int t = first.val;
        first.val = second.val;
        second.val =t;
    }
}

// 03/12/15
// 用prev记录上一个node，先search left到底，记下第一个prev
// 当第一次出现乱序时，记下prev和root，如果是邻近的两个swap，只能找到1次乱序的情况
// 否则能找到第二次乱序，此时记下prev
// 顺序是left root right
public class Solution {
    public TreeNode first = null, second = null, prev = null;
    public void recoverTree(TreeNode root) {
        helper(root);
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }
    
    private void helper(TreeNode root){
        if(root == null) return;
        helper(root.left);
        if(prev == null){
            prev = root;
        }
        else{
            if(prev.val > root.val){
                if(first == null){
                    first = prev;
                    second = root;
                }
                else{
                    second = root;
                }
            }
            prev = root;
        }
        helper(root.right);
    }
}