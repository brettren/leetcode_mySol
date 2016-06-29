

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 */

public class RotateImage {
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		if (n == 0)
			return;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {  // 按照diagonal对称两边swap
				int t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;  
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {  // 按照vertical中轴线对称两边swap
				int t = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = t;
			}
		}
	}
}

// 03/21/15
// 先沿着对角线45度swap，再沿着中轴线swap
// 1 2 3   // 1  	//      1
// 			  2 			2
// 			  3 			3

public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n == 0) return;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n/2; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = t;
            }
        }
    }
}