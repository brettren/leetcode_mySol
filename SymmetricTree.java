import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 *     1
 *    / \
 *   2   2
 *   \   \
 *   3    3
 * Note:
 * 
 * Bonus points if you could solve it both recursively and iteratively.
 */

public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root.left);
		s2.push(root.right);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNode n1 = s1.pop();
			TreeNode n2 = s2.pop();
			if (n1 == null && n2 == null) {
				continue;
			} else if (n1 == null || n2 == null) {
				return false;
			} else if (n1.val != n2.val) {
				return false;
			} else {
				s1.push(n1.left); // 如果是null，就把null放入stack
				s1.push(n1.right);
				s2.push(n2.right);
				s2.push(n2.left);
			}
		}
		return true;
	}
}



// 03/21/15
// 建立两个stack，把root的left和right分别放入
// 每次pop出来比较，如果都是null说明到底了，continue；如果有一个是null另一个不是，说明不是symmetry
// 如果都不是null但是val不同，也不是symmetry
// 当前node如果对称，那就把left和right放入stack
// 总体上是preorder的比较顺序
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root.left);
        s2.push(root.right);
        while(!s1.isEmpty() && !s2.isEmpty()){
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();
            if(n1 == null && n2 == null) continue;
            if(n1 != null && n2 == null) return false;
            if(n1 == null && n2 != null) return false;
            if(n1.val != n2.val) return false;
            s1.push(n1.left);
            s1.push(n1.right);
            s2.push(n2.right);
            s2.push(n2.left);  // 这里把null当做一个空位，因为空位也需要对称 ！！！
        }
        return true;
    }
}

// 递归版本sol
// preorder的比较顺序，先比较root，然后是left和right，只要有一次unmatched就返回false
// 先排除n1和n2有一个为null的情况，然后再递归比较他们的left和right
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null) return true;
        if(n1 != null && n2 == null) return false;
        if(n1 == null && n2 != null) return false;
        if(n1.val != n2.val) return false;
        return helper(n1.left, n2.right) && helper(n1.right, n2.left);
    }
}