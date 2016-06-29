/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    
    private Stack<TreeNode> st = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode t = st.pop();
        pushAll(t.right);
        return t.val;
    }
    
    public void pushAll(TreeNode root){
        while(root!= null){
            st.push(root);
            root = root.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */


// 03/20/15
// 用stack来存沿着left方向的所有node
// 当pop一个的时候检查它是否有right，有就push到stack里
// 因为是inorder，所以stack pop能够保证这个order
public class BSTIterator {

    private Stack<TreeNode> st;
    
    public BSTIterator(TreeNode root) {
        st = new Stack<TreeNode>();
        putAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode n = st.pop();
        if(n.right != null) putAll(n.right);
        return n.val;
    }
    
    public void putAll(TreeNode root){
        while(root != null){
            st.push(root);
            root = root.left;
        }
    }
}