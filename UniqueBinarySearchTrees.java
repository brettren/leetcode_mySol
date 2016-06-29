

/** 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * 
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */

public class UniqueBinarySearchTrees {
	public int numTrees(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		int[] record = new int[n + 1];
		record[0] = 1;
		record[1] = 1;
		record[2] = 2;
		for (int i = 3; i <= n; i++) {  // n=3
			int tmp = 0;		
			for (int k = 0; k < i; k++) {
				tmp += (record[k] * record[i - k - 1]); // 左子树有k个nodes的结构可能 * 右子树有i-k-1个nodes的结构可能
			}
			record[i] = tmp;
		}
		return record[n];
	}
}

// recursion方法
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if(n==1 || n==0){
            return 1;    
        }
        else{
	        int num = 0;
	        // 一共n个，root是1个(可能是1:n中的一个)，左边分到比root小的i-1个，右边分到比root大的n-i个，左边的组合数*右边的组合数
	        for(int i=1; i<=n; i++){ //i是root的值
	            num += (numTrees(i-1) * numTrees(n-i)); //left*right
	        }   
	        return num;
        }
    }
}

// 01/11/15
public class Solution {
    public int numTrees(int n) {
        if(n == 0) return 1;
        int[] ret = new int[n+1];
        ret[0] = 1;
        ret[1] = 1;
        for(int i = 2; i <= n; i++){
            int tmp = 0;
            for(int j = 0; j < i; j++){
                tmp += ret[j] * ret[i-j-1];
            }
            ret[i] = tmp;
        }
        return ret[n];
    }
}

// 03/24/15
// n = 3，那么左右子树的可能就是0,2; 1,1; 2,0
// 根据前面的结果可以知道有n个node有几种tree，这里就可以遍历各种情况得到left no. * right no.
// 选j为root，那么[1,j-1]为left tree，[j+1, i]为right tree
// tree的数量只和node数量有关
public class Solution {
    public int numTrees(int n) {
        if(n == 0) return 0;
        int[] ret = new int[n+1];
        ret[0] = 1;  //表示0个node有1种tree
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= i-1; j++){
                ret[i] += ret[j]*ret[i-j-1];  // 左边是j个node的tree，除掉root，右边是i-j-1个node
            }
        }
        return ret[n];
    }
}



