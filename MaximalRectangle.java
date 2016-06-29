/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 */
//这题目要联系 Largest Rectangle in Histogram 一起看
//把原来的1010矩阵变成直方图矩阵
//比方说这个矩阵有5行 我们第一列的时候 最大的矩阵就是  Largest Rectangle in Histogram题种0010;  
//然后第二行插入的时候就变成了  Largest Rectangle in Histogram0120; 第三行变成 0231; 
//当第四行插入的时候 matrix[3][1]和[3][3]是0  所以 此时histogram 被更新成 1040 
//最后一行跟新成0151  每新建好一行都可以调用 Largest Rectangle in Histogram来测算当前最大矩阵面积
//然后动态更新

public class MaximalRectangle {
	public int maximalRectangle(char[][] matrix) {
		int rows = matrix.length;
		if (rows == 0)
			return 0;
		int maxArea = 0;
		int cols = matrix[0].length;
		int[][] map = new int[rows][cols];
		for (int j = 0; j < cols; j++) {
			map[0][j] = matrix[0][j] == '0' ? 0 : 1;  // copy row 0
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = matrix[i][j] == '0' ? map[i - 1][j]  // 根据上一个row来定值
						: map[i - 1][j] + 1;
			}
		}
//	char[][] matrix = {{'0', '0', '1', '0'},
//	                   {'0', '1', '1', '0'},
//	                   {'0', '1', '1', '1'},
//	                   {'1', '0', '1', '0'},
//	                   {'0', '1', '1', '1'},};

// map[0] = {0, 0, 1, 0}
// map[1] = {0, 1, 2, 0}
// map[2] = {0, 2, 3, 1}
// map[3] = {1, 2, 4, 1}
// map[4] = {1, 3, 5, 2}

		int[] col = new int[cols];
		for (int i = 0; i < rows; i++) {
			for (int j = i; j < rows; j++) {
				for (int k = 0; k < cols; k++) {
					col[k] = map[j][k] - (i == 0 ? 0 : map[i - 1][k]);  // 表示两个row i和j之间的col区域内的值
				}
				int count = 0;
				for (int k = 0; k < cols; k++) {
					if (col[k] == j - i + 1) {  // 表示row i和row j中间部分都是1
						maxArea = Math.max(maxArea, ++count * (j - i + 1));  // 先++count，把这个col算上
					} else {  // 如果这个col不都是1，那就不能算上了
						// maxArea = Math.max(maxArea, count * (j - i + 1));  这部分可以删了
						count = 0;
					}
				}
			}
		}
		return maxArea;
	}
}


// 03/10/15
// 先建立一个map，每个格子记录一个col累积到这个格子时'1'的个数
// 然后用两个指针表示row index，从上到下遍历，在两个loop的内部先用col[]数组来统计两个row之间每个col的1的个数
// 如果[j, i]区间内1的个数等于j-i+1，说明col的区间内全是1，count++, 同时不断更新max的返回值 count*(j-i+1)

// j ------------

// i ------------

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int w = matrix.length;
        if(w == 0) return 0;
        int l = matrix[0].length;
        int[][] map = new int[w][l];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                int d = matrix[i][j] - '0';
                map[i][j] = d;
                if(i != 0){
                    map[i][j] += map[i-1][j];
                }
            }
        }
        
        int[] col = new int[l];
        int ret = 0;
        for(int i = 0; i < w; i++){
            for(int j = 0; j <= i; j++){
                for(int k = 0; k < l; k++){
                    col[k] = (j == 0)? map[i][k] : map[i][k] - map[j-1][k];
                }
                int count = 0;
                for(int k = 0; k < l; k++){
                    if(col[k] == i-j+1){
                        count++;
                        ret = Math.max(ret, count*(i-j+1));
                    }
                    else{
                        count = 0;
                    }
                }
            }
        }
        return ret;
    }
}