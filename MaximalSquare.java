// Given a 2D binary matrix filled with 0's and 1's, 
// find the largest square containing all 1's and return its area.

// For example, given the following matrix:

// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// Return 4.




// t[i][j] = min(t[i][j-1], t[i-1][j], t[i-1][j-1]) + 1.

// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 2 2
// 1 0 0 1 0


// DP， 这里t的作用是记录以t[i][j]当做一个square的右下角，这个square的边长
public class Solution {
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
	 
		int m = matrix.length;
		int n = matrix[0].length;
	 
		int[][] t = new int[m][n];
	 
		//top row
		for (int i = 0; i < m; i++) {
			t[i][0] = matrix[i][0] - '0';
		}
	 
		//left column
		for (int j = 0; j < n; j++) {
			t[0][j] = matrix[0][j] - '0';
		}
	 
		//cells inside
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == '1') {
					int min = Math.min(t[i - 1][j], t[i - 1][j - 1]);
					min = Math.min(min,t[i][j - 1]);
					t[i][j] = min + 1;
				} else {
					t[i][j] = 0;
				}
			}
		}
	 
		int max = 0;
		//get maximal length
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, t[i][j]);
			}
		}
	 
		return max * max;
	}
}