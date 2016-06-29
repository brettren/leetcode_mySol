

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 */
//sum这个矩阵里每一格都存左上走到这步所需的最少sum。
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int[][] path = new int[grid.length][grid[0].length];
		//1.先initial 两个边  sum[0][col] = sum[0][col - 1] + grid[0][col];
		path[0][0] = grid[0][0];
		for (int i = 1; i < grid.length; i++) {
			path[i][0] = path[i - 1][0] + grid[i][0];  // 加上上面的
		}
		for (int j = 1; j < grid[0].length; j++) {
			path[0][j] = path[0][j - 1] + grid[0][j];  // 加上左边的
		}
		for (int i = 1; i < grid.length; i++)
			for (int j = 1; j < grid[0].length; j++) {
				path[i][j] = Math.min(path[i - 1][j], path[i][j - 1])  // 取左边和上边的min再加上这个点的value
						+ grid[i][j];
			}
		return path[grid.length - 1][grid[0].length - 1];
	}
}


// 03/21/15
// 初始化row0和col0，再遍历数组，取上面和左边的min，加上当前位置的value
public class Solution {
    public int minPathSum(int[][] grid) {
        int w = grid.length;
        int l = grid[0].length;
        int[][] ret = new int[w][l];
        ret[0][0] = grid[0][0];
        for(int i = 1; i < l; i++){
            ret[0][i] = ret[0][i-1] + grid[0][i]; 
        }
        for(int i = 1; i < w; i++){
            ret[i][0] = ret[i-1][0] + grid[i][0];
        }
        
        for(int i = 1; i < w; i++){
            for(int j = 1; j < l; j++){
                ret[i][j] = Math.min(ret[i-1][j], ret[i][j-1]);
                ret[i][j] += grid[i][j]; 
            }
        }
        return ret[w-1][l-1];
    }
}