// Given a binary search tree, write a function kthSmallest to find the kth smallest
// element in it.

// Note: 
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

// Follow up:
// What if the BST is modified (insert/delete operations) often and you need to find 
// the kth smallest frequently? How would you optimize the kthSmallest routine?

// inorder iteration, 找到第k个
// p表示下一个search node
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
    	if(root == null) return -1;
        Stack<TreeNode> st = new Stack<>();
        TreeNode p = root;
        int count = 0;
        while(!st.isEmpty() || p != null){
        	if(p != null){
        		st.push(p);
        		p = p.left;
        	}
        	else{
        		TreeNode n = st.pop();
        		count++;
        		if(count == k) return n.val;
        		p = n.right;
        	}
        }
        return -1;
    }
}









