

import java.util.ArrayList;

//Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
//
//For example,
//Given n = 3,
//You should return the following matrix:
//
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]

public class SpiralMatrix {
	public int[][] generateMatrix(int n) {
		int[][] list = new int[n][n];
		if (n == 0)
			return list;
		int beginRow = 0, endRow = n - 1;  // row
		int beginCol = 0, endCol = n - 1;  // col
		int val = 1;
		while (true) {
			// 横着读取第一行（最外层上边）
			for (int i = beginCol; i <= endCol; i++) {
				list[beginRow][i] = val++;
			}
			if (++beginRow > endRow)  // 向下一个row
				break;
			// 从第二行开始读最外层右边
			for (int i = beginRow; i <= endRow; i++) {
				list[i][endCol] = val++;
			}
			if (beginCol > --endCol)  // 向左一个col
				break;
			// 最外层下边从右(倒数第二右)到左 输入
			for (int i = endCol; i >= beginCol; i--) {
				list[endRow][i] = val++;
			}
			if (beginRow > --endRow)  // 向上一个row
				break;
			for (int i = endRow; i >= beginRow; i--) {
				list[i][beginCol] = val++;
			}
			if (++beginCol > endCol)  // 向右一个col
				break;
		}
		return list;
	}
}

// 01/11/15
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        if(n == 0) return ret; // return的是int[0][0]
        int left = 0, right = n-1;
        int upper = 0, bottom = n-1;
        int j = 1;
        while(true){
            for(int i = left; i <= right; i++){
                ret[upper][i] = j++;
            }
            upper++;
            if(upper > bottom) break;
            
            for(int i = upper; i <= bottom; i++){
                ret[i][right] = j++;
            }
            right--;
            if(left > right) break;
            
            for(int i = right; i >= left; i--){
                ret[bottom][i] = j++;
            }
            bottom--;
            if(upper > bottom) break;
            
            for(int i = bottom; i >= upper; i--){
                ret[i][left] = j++;
            }
            left++;
            if(left > right) break;
        }
        return ret;
    }
}