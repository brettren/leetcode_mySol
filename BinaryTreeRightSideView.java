// Given a binary tree, imagine yourself standing on the right side of it, 
// return the values of the nodes you can see ordered from top to bottom.

// For example:
// Given the following binary tree,
//    1            <---
//  /   \
// 2     3         <---
//  \     \
//   5     4       <---
// You should return [1, 3, 4].

// BFS, 用一个list来存所有level的node，然后每个level取第一个val，也就是最右边的node val
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> ret = new ArrayList<>();
    	if(root == null) return ret;

        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
        	int size = q.size();
        	List<Integer> tmp = new ArrayList<>();
        	for (int i = 0; i < size; i++) {
        		TreeNode n = q.remove();
        		tmp.add(n.val);
        		if(n.right != null) q.add(n.right);
        		if(n.left != null) q.add(n.left);
        	}
        	list.add(tmp);
        }

        for (int i = 0; i < list.size(); i++) {
        	ret.add(list.get(i).get(0));
        }
        return ret;
    }
}