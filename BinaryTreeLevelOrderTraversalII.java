

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 * (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *    3
 *   / \
 *  9  20
 *  /   \
 * 15    7
 * return its bottom-up level order traversal as:
 * [
 *  [15,7]
 *  [9,20],
 *  [3],
 * ]
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 */

public class BinaryTreeLevelOrderTraversalII {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        search(ret, 0, root);
        Collections.reverse(ret);
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

// 06/01/2015
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> tmp = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                TreeNode n = q.remove();
                tmp.add(n.val);
                if(n.left != null) q.add(n.left);
                if(n.right != null) q.add(n.right);
            }
            ret.add(tmp);
        }
        Collections.reverse(ret);
        return ret;
    }
}