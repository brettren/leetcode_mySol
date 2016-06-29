/**
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * 
 * The total number of unique paths is 2.
 *
 * Note: m and n will be at most 100.
 *
 */
public class UniquePathsII {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		if (m == 0)
			return 0;
		int n = obstacleGrid[0].length;
		int[][] map = new int[m][n];
		map[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
		for (int i = 1; i < m; i++) {
			map[i][0] = obstacleGrid[i][0] == 1 ? 0 : map[i - 1][0]; // 一旦遇到1，那么后面都是0，表示没有path能经过
		}
		for (int j = 1; j < n; j++) {
			map[0][j] = obstacleGrid[0][j] == 1 ? 0 : map[0][j - 1];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = obstacleGrid[i][j] == 1 ? 0 : map[i - 1][j]  // 如果这个node是obstacle，那不可到达；否则就看上面和左边的和
						+ map[i][j - 1];
			}
		}
		return map[m - 1][n - 1];
	}
}


// 01/08/15
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int w = obstacleGrid.length;
        if(w == 0) return 0;
        int l = obstacleGrid[0].length;
        int[][] ret = new int[w][l];
        if(obstacleGrid[0][0] == 1) return 0;
        ret[0][0] = 1;
        for(int i = 1; i < l; i++){
            if(obstacleGrid[0][i] == 0)
                ret[0][i] = ret[0][i-1];
            else
                ret[0][i] = 0;
        }
        for(int i = 1; i < w; i++){
            if(obstacleGrid[i][0] == 0)
                ret[i][0] = ret[i-1][0];
            else
                ret[i][0] = 0;
        }
        
        for(int i = 1; i < w; i++){
            for(int j = 1; j < l; j++){
                if(obstacleGrid[i][j] == 0){
                    ret[i][j] = ret[i-1][j] + ret[i][j-1];
                }
                else{
                    ret[i][j] = 0;
                }
            }
        }
        return ret[w-1][l-1];
    }
}

// 03/20/15
// 先初始化第0 row和第0 col，如果是obstacle == 1，那后面的就没有path
// 然后遍历二维数组，path的数量是 ret[i-1][j] + ret[i][j-1]，左边和上边的和
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int w = obstacleGrid.length;
        if(w == 0) return 0;
        int l = obstacleGrid[0].length;
        int[][] ret = new int[w][l];
        if(obstacleGrid[0][0] == 1) return 0;
        ret[0][0] = 1;
        for(int i = 1; i < l; i++){
            if(obstacleGrid[0][i] == 1) ret[0][i] = 0;
            else ret[0][i] = ret[0][i-1];
        }
        for(int i = 1; i < w; i++){
            if(obstacleGrid[i][0] == 1) ret[i][0] = 0;
            else ret[i][0] = ret[i-1][0];
        }
        for(int i = 1; i < w; i++){
            for(int j = 1; j < l; j++){
                if(obstacleGrid[i][j] == 1) ret[i][j] = 0;
                else ret[i][j] = ret[i-1][j] + ret[i][j-1];
            }    
        }
        return ret[w-1][l-1];
    }
}