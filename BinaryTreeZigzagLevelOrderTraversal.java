//Given a binary tree, return the zigzag level order traversal of its
		// nodes' values. (ie, from left to right, then right to left for the next
		// level and alternate between).
		//
		// For example:
		// Given binary tree {3,9,20,#,#,15,7},
		//
		// 3
		// / \
		// 9 20
		// / \
		// 15 7
		//
		// return its zigzag level order traversal as:
		//
		// [
		// [3],
		// [20,9],
		// [15,7]
		// ]
		// level order 但是要一层正的输出 一层反着输出
// DFS
public class Solution {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        search(ret, 0, root);
        for (int i = 1; i < ret.size(); i+=2 ) {
        	Collections.reverse(ret.get(i));
        }
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


// BFS
// 01/05/15
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> tmp = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                tmp.add(cur.val);
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
            ret.add(tmp);
        }
        
        for(int i = 1; i < ret.size(); i+=2){
            Collections.reverse(ret.get(i));
        }
        
        return ret;
    }
}

// DFS
// 01/05/15
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        helper(ret, root, 0);
        for (int i = 1; i < ret.size(); i+=2 ) {
            Collections.reverse(ret.get(i));
        }
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, TreeNode root, int level){
        if(root == null) return;
        List<Integer> tmp;
        if(level < ret.size()){
            tmp = ret.get(level);
            tmp.add(root.val);
        }
        else{
            tmp = new ArrayList<Integer>();
            tmp.add(root.val);
            ret.add(tmp);
        }
        helper(ret, root.left, level + 1);
        helper(ret, root.right, level + 1);
    }
}

// 03/15/15
// 就是BFS，每次先记下queue当前的size，就是这个level的size，从queue取出一个node，添加value，然后把它的left和right放入queue
// 然后就是偶数的level需要reverse
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
        for(int i = 1; i < ret.size(); i+=2){
            Collections.reverse(ret.get(i));
        }
        return ret;
    }
}