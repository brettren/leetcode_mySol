// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

// For example:
// Given binary tree {3,9,20,#,#,15,7},
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its level order traversal as:
// [
//   [3],
//   [9,20],
//   [15,7]
// ]

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// DFS
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        search(ret, 0, root);
        return ret;
    }


    public static void search(ArrayList<ArrayList<Integer>> ret, int level, TreeNode root){
        if (root == null) {
            return;
        }
        
        ArrayList<Integer> list = null;
        if (ret.size() == level) { // Level not contained in list  新的level
            list = new ArrayList<Integer>();
            /* Levels are always traversed in order. So, if this is the first time we've visited level i,
             * we must have seen levels 0 through i - 1. We can therefore safely add the level at the end. */
            ret.add(list);  
        } else {
            list = ret.get(level);  //找到level对应的list
        }
        list.add(root.val);
        
        search(ret, level+1, root.left);
        search(ret, level+1, root.right);
    }
}


// BFS  01/03/15
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> tmp = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                TreeNode current = q.poll();
                tmp.add(current.val);
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);
            }
            ret.add(tmp);
        }
        return ret;
    }
}