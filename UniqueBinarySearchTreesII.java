/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */
//1~n  要全部储存在bst里 有几种肯能的树？
//用dfs的思想  当root为1时候 root为2时候 root为3时候循环 一直到n 这就是for
//然后当root为1时候 左边不能放 右边放2~n   root为2时候  左边放1 右边放3～n
//总之 当 root为 i时候 左边放1～i-1  右边放1+1～n　所以这里就是dfs
//循环就是1～n
import java.util.ArrayList;

public class UniqueBinarySearchTreesII {
	public ArrayList<TreeNode> generateTrees(int n) {
		return buildBST(1, n);
	}

	private ArrayList<TreeNode> buildBST(int min, int max) {  // 这里返回的list，每个node表示一个tree的root
		ArrayList<TreeNode> ret = new ArrayList<TreeNode>();  // 表示的是[min,max]范围有几种不同的BST
		if (min > max) {
			ret.add(null);  // 这里null一定要加入ret
			return ret;
		}
		if (min == max) {
			ret.add(new TreeNode(min));  // 只有一个node需要添加
			return ret;
		}
		for (int i = min; i <= max; i++) {
			// 可以保证lefttree里的node都小于i，表示在min到i-1范围不同结构的trees list
			ArrayList<TreeNode> leftTrees = buildBST(min, i - 1);
			ArrayList<TreeNode> rightTrees = buildBST(i + 1, max); // 可以保证rightTree里的node都大于i
			for (TreeNode l : leftTrees) {
				for (TreeNode r : rightTrees) {  // 总的结构可能有 leftTrees的个数 * rightTrees的个数
					TreeNode root = new TreeNode(i);
					root.left = l;
					root.right = r;
					ret.add(root);  // subtree的root，一种tree结构完成
				}
			}
		}
		return ret;  // 所有min到max可能的trees的root list
	}
}

// 03/19/15
// 递归程序，一个loop循环以每个值i作root，然后返回左边[s, i-1]的所有子树，和右边[i+1, e]的所有子树, 连起来就是一个sol
// helper(s, e)返回的是以[s,e]区间范围的所有可能 BST
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    private List<TreeNode> helper(int s, int e){
        List<TreeNode> ret = new ArrayList<TreeNode>();
        if(s > e) {
            ret.add(null);
            return ret;
        }
        if(s == e){
            ret.add(new TreeNode(s));
            return ret;
        }
        for(int i = s; i <= e; i++){  // 每个以i为root的tree一共有 left*right 的数量
            List<TreeNode> left = helper(s, i-1);
            List<TreeNode> right = helper(i+1, e);
            for(TreeNode l: left){
                for(TreeNode r: right){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ret.add(root);  // build one tree, add to ret
                }
            }
        }
        return ret;
    }
}