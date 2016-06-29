/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.
 * 
 */

//recursion解法leetcode超时
//因为题目告诉你M N不会大于100 我们就吧到这个最多100x100的矩阵给预处理了
public class UniquePaths {
	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		int[][] map = new int[m][n];
		for (int i = 0; i < m; i++) {
			map[i][0] = 1;  // col 0
		}
		for (int j = 0; j < n; j++) {
			map[0][j] = 1;  // row 0
		} 
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = map[i - 1][j] + map[i][j - 1];  // 每个node表示走到这里有几个path
			}
		}
		return map[m - 1][n - 1];
	}
}


// 01/08/15
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] ret = new int[m][n];
        for(int i = 0; i < n; i++){
            ret[0][i] = 1;
        }
        for(int i = 0; i < m; i++){
            ret[i][0] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                ret[i][j] = ret[i-1][j] + ret[i][j-1];
            }
        }
        return ret[m-1][n-1];
    }
}