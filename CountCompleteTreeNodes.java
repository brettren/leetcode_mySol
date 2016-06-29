// Given a complete binary tree, count the number of nodes.

// Definition of a complete binary tree from Wikipedia:
// In a complete binary tree every level, except possibly the last, 
// is completely filled, and all nodes in the last level are as far left as possible. 
// It can have between 1 and 2h nodes inclusive at the last level h.


// 递归，先找sub tree的left most和right most，看height是否相同，相同说明最后一个level全满；
// 不相同就返回左右子树的node数再加1
public class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int ln = 0, rn = 0;
        TreeNode l = root, r = root;
        while(l != null) {
        	ln++;
        	l = l.left;
        }
        while(r != null) {
        	rn++;
        	r = r.right;
        }
        if(ln == rn) return (1 << ln) - 1;
        else return countNodes(root.left) + countNodes(root.right) + 1;
    }
}




	